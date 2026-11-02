<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="海关编码" prop="hsCode">
        <el-input
          v-model="queryParams.hsCode"
          placeholder="请输入海关编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建日期" style="width: 308px">
        <el-date-picker
          v-model="daterangeCreateTime"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['cle:tax:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cle:tax:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cle:tax:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cle:tax:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taxList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="海关编码" align="center" prop="hsCode" />
      <el-table-column label="反倾销" align="center" prop="antiDumping" />
      <el-table-column label="CPSC" align="center" prop="cpsc" />
      <el-table-column label="是否需要认证" align="center" prop="needCert" />
      <el-table-column label="总税率" align="center" prop="totalTaxRate" />
      <el-table-column label="中文描述" align="center" prop="cnDesc" />
      <el-table-column label="官方英文描述" align="center" prop="officialDesc" />
      <el-table-column label="基础税率" align="center" prop="baseTaxRate" />
      <el-table-column label="301加征税CODE" align="center" prop="section301Code" />
      <el-table-column label="301加征税" align="center" prop="section301Tax" />
      <el-table-column label="FL301/232附加税CODE" align="center" prop="fl301232Code" />
      <el-table-column label="FL301/232附加税" align="center" prop="fl301232Tax" />
      <el-table-column label="单位1" align="center" prop="unit1" />
      <el-table-column label="单位2" align="center" prop="unit2" />
      <el-table-column label="Cents/kg" align="center" prop="centsPerKg" />
      <el-table-column label="生效日期" align="center" prop="effectiveDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="失效日期" align="center" prop="expirationDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="PGA1" align="center" prop="pga1" />
      <el-table-column label="PGA2" align="center" prop="pga2" />
      <el-table-column label="PGA3" align="center" prop="pga3" />
      <el-table-column label="PGA4" align="center" prop="pga4" />
      <el-table-column label="PGA5" align="center" prop="pga5" />
      <el-table-column label="PGA6" align="center" prop="pga6" />
      <el-table-column label="创建日期" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleViewData(scope.row)" v-hasPermi="['cle:tax:query']">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cle:tax:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cle:tax:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 税率查询详情抽屉 -->
    <tax-view-drawer ref="taxViewRef" />
    <!-- 添加或修改税率查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="taxRef" :model="form" :rules="rules" label-width="100px">
        <el-row>
        </el-row>
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

<script setup name="Tax">
import { listTax, getTax, delTax, addTax, updateTax } from "@/api/cle/tax"
import TaxViewDrawer from "./view"

const { proxy } = getCurrentInstance()

const taxList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeCreateTime = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    hsCode: undefined,
    cnDesc: undefined,
    createTime: undefined
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询税率查询列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeCreateTime.value && '' != daterangeCreateTime.value) {
    queryParams.value.params["beginCreateTime"] = daterangeCreateTime.value[0]
    queryParams.value.params["endCreateTime"] = daterangeCreateTime.value[1]
  }
  listTax(queryParams.value).then(response => {
    taxList.value = response.rows
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
    hsCode: null,
    antiDumping: null,
    cpsc: null,
    needCert: null,
    totalTaxRate: null,
    cnDesc: null,
    officialDesc: null,
    baseTaxRate: null,
    section301Code: null,
    section301Tax: null,
    fl301232Code: null,
    fl301232Tax: null,
    unit1: null,
    unit2: null,
    centsPerKg: null,
    effectiveDate: null,
    expirationDate: null,
    pga1: null,
    pga2: null,
    pga3: null,
    pga4: null,
    pga5: null,
    pga6: null,
    createTime: null
  }
  proxy.resetForm("taxRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeCreateTime.value = []
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
  title.value = "添加税率查询"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getTax(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改税率查询"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["taxRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateTax(form.value).then(() => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addTax(form.value).then(() => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除税率查询编号为"' + _ids + '"的数据项？').then(function() {
    return delTax(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 详情按钮操作 */
function handleViewData(row) {
  proxy.$refs["taxViewRef"].open(row.id)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cle/tax/export', {
    ...queryParams.value
  }, `tax_${new Date().getTime()}.xlsx`)
}

getList()
</script>
