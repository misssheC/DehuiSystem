<template>
  <div class="app-container">
    <!-- 搜索表单 (精简版，保留最常用的条件) -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="提单号" prop="billNo">
        <el-input
          v-model="queryParams.billNo"
          placeholder="请输入提单号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="柜号" prop="containerNo">
        <el-input
          v-model="queryParams.containerNo"
          placeholder="请输入柜号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户" prop="customer">
        <el-input
          v-model="queryParams.customer"
          placeholder="请输入客户"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ETD" style="width: 308px">
        <el-date-picker
          v-model="daterangeEtd"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <!-- 如果你允许在"我的运单"中新增，可以保留此按钮，并修改权限标识 -->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['cle:waybill:my:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cle:waybill:my:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据表格：仅保留概要字段 -->
    <el-table v-loading="loading" :data="waybillList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="提单号" align="center" prop="billNo" />
      <el-table-column label="柜号" align="center" prop="containerNo" />
      <el-table-column label="货柜状态" align="center" prop="containerStatus">
        <template #default="scope">
          <dict-tag :options="container_status" :value="scope.row.containerStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="客户" align="center" prop="customer" />
      <el-table-column label="船司" align="center" prop="carrier" />
      <el-table-column label="柜型" align="center" prop="containerType" />
      <el-table-column label="件数" align="center" prop="packageQty" />
      <el-table-column label="ETD" align="center" prop="etd" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.etd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="ETA" align="center" prop="eta" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eta, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="清关状态" align="center" prop="customsStatus" />
      
      <!-- 操作列：只保留详情 -->
      <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width" fixed="right">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleViewData(scope.row)" v-hasPermi="['cle:waybill:my:query']">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cle:waybill:my:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cle:waybill:my:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 详情抽屉 (直接复用原来的组件) -->
    <waybill-view-drawer ref="waybillViewRef" />
    
    <!-- 如果你允许在"我的运单"中新增，保留此表单；否则可以删除 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="waybillRef" :model="form" :rules="rules" label-width="100px">
        <!-- 为了篇幅，这里你可以把原代码的 form 表单复制过来，字段按需保留 -->
        <el-form-item label="提单号" prop="billNo">
          <el-input v-model="form.billNo" placeholder="请输入提单号" />
        </el-form-item>
        <el-form-item label="柜号" prop="containerNo">
          <el-input v-model="form.containerNo" placeholder="请输入柜号" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MyWaybill">
// 1. 注意这里引入的是 My 专属接口
import { listMyWaybill, getMyWaybill, addWaybill, updateWaybill, delWaybill } from "@/api/cle/waybill"
import WaybillViewDrawer from "./view"

const { proxy } = getCurrentInstance()
// 字典数据按需保留，如果你的概览页需要显示哪些状态，就保留哪些
const { container_status } = useDict('container_status')

const waybillList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeEtd = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    billNo: undefined,
    containerNo: undefined,
    customer: undefined,
    etd: undefined,
  },
  rules: {}
})

const { queryParams, form, rules } = toRefs(data)

/** 查询我的运单列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeEtd.value && '' != daterangeEtd.value) {
    queryParams.value.params["beginEtd"] = daterangeEtd.value[0]
    queryParams.value.params["endEtd"] = daterangeEtd.value[1]
  }
  
  // 2. 调用专属的 listMyWaybill，后端会自动根据当前用户过滤
  listMyWaybill(queryParams.value).then(response => {
    waybillList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 表单重置 */
function reset() {
  form.value = {
    id: null,
    billNo: null,
    containerNo: null,
    containerStatus: null,
    customer: null,
    // ... 其它字段按需保留
  }
  proxy.resetForm("waybillRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeEtd.value = []
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加我的运单"
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["waybillRef"].validate(valid => {
    if (valid) {
      addWaybill(form.value).then(() => {
        proxy.$modal.msgSuccess("新增成功")
        open.value = false
        getList()
      })
    }
  })
}

/** 详情按钮操作 */
function handleViewData(row) {
  proxy.$refs["waybillViewRef"].open(row.id)
}

/** 导出按钮操作 */
function handleExport() {
  // 如果你需要导出"我的"数据，这里需要后端配合写一个专属的导出接口
  proxy.download('cle/waybill/my/export', {
    ...queryParams.value
  }, `my_waybill_${new Date().getTime()}.xlsx`)
}
function handleUpdate(row) {
  reset()
  const _id = row.id
  // 这里调用的是你原有的通用详情接口即可
  getMyWaybill(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改我的运单"
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除该运单数据项？').then(function() {
    return delWaybill(_ids) // 调用通用删除接口
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

getList()
</script>