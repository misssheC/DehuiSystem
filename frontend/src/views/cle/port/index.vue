<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="港头名称" prop="portName">
        <el-input
          v-model="queryParams.portName"
          placeholder="请输入港头名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="港口类型" prop="portType">
        <el-select v-model="queryParams.portType" placeholder="请选择港口类型" clearable>
          <el-option
            v-for="dict in port_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="航线名称" prop="routeType">
        <el-select v-model="queryParams.routeType" placeholder="请选择航线名称" clearable>
          <el-option
            v-for="dict in route_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否清关" prop="clearCustomsFlag">
        <el-select v-model="queryParams.clearCustomsFlag" placeholder="请选择是否清关" clearable>
          <el-option
            v-for="dict in is_clear"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="装货港代码" prop="loadPortCode">
        <el-input
          v-model="queryParams.loadPortCode"
          placeholder="请输入装货港代码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卸货港代码" prop="unloadPortCode">
        <el-input
          v-model="queryParams.unloadPortCode"
          placeholder="请输入卸货港代码"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['cle:port:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cle:port:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cle:port:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cle:port:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="portList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="港头名称" align="center" prop="portName" />
      <el-table-column label="港口类型" align="center" prop="portType">
        <template #default="scope">
          <dict-tag :options="port_type" :value="scope.row.portType"/>
        </template>
      </el-table-column>
      <el-table-column label="航线名称" align="center" prop="routeType">
        <template #default="scope">
          <dict-tag :options="route_type" :value="scope.row.routeType"/>
        </template>
      </el-table-column>
      <el-table-column label="是否清关" align="center" prop="clearCustomsFlag">
        <template #default="scope">
          <dict-tag :options="is_clear" :value="scope.row.clearCustomsFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="装货港代码" align="center" prop="loadPortCode" />
      <el-table-column label="卸货港代码" align="center" prop="unloadPortCode" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleViewData(scope.row)" v-hasPermi="['cle:port:query']">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cle:port:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cle:port:remove']">删除</el-button>
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

    <!-- 码头管理详情抽屉 -->
    <port-view-drawer ref="portViewRef" />
    <!-- 添加或修改码头管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="portRef" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="港头名称" prop="portName">
              <el-input v-model="form.portName" placeholder="请输入港头名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="港口类型" prop="portType">
              <el-select v-model="form.portType" placeholder="请选择港口类型">
                <el-option
                  v-for="dict in port_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="航线名称" prop="routeType">
              <el-select v-model="form.routeType" placeholder="请选择航线名称">
                <el-option
                  v-for="dict in route_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否清关" prop="clearCustomsFlag">
              <el-select v-model="form.clearCustomsFlag" placeholder="请选择是否清关">
                <el-option
                  v-for="dict in is_clear"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="装货港代码" prop="loadPortCode">
              <el-input v-model="form.loadPortCode" placeholder="请输入装货港代码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="卸货港代码" prop="unloadPortCode">
              <el-input v-model="form.unloadPortCode" placeholder="请输入卸货港代码" />
            </el-form-item>
          </el-col>
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

<script setup name="Port">
import { listPort, getPort, delPort, addPort, updatePort } from "@/api/cle/port"
import PortViewDrawer from "./view"

const { proxy } = getCurrentInstance()
const { is_clear, port_type, route_type } = useDict('is_clear', 'port_type', 'route_type')

const portList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    portName: undefined,
    portType: undefined,
    routeType: undefined,
    clearCustomsFlag: undefined,
    loadPortCode: undefined,
    unloadPortCode: undefined,
  },
  rules: {
    portName: [
      { required: true, message: "港头名称不能为空", trigger: "blur" }
    ],
    portType: [
      { required: true, message: "港口类型不能为空", trigger: "change" }
    ],
    routeType: [
      { required: true, message: "航线名称不能为空", trigger: "change" }
    ],
    clearCustomsFlag: [
      { required: true, message: "是否清关不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询码头管理列表 */
function getList() {
  loading.value = true
  listPort(queryParams.value).then(response => {
    portList.value = response.rows
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
    portName: null,
    portType: null,
    routeType: null,
    clearCustomsFlag: null,
    loadPortCode: null,
    unloadPortCode: null,
    createDept: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    delFlag: null
  }
  proxy.resetForm("portRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
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
  title.value = "添加码头管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getPort(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改码头管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["portRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePort(form.value).then(() => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPort(form.value).then(() => {
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
  proxy.$modal.confirm('是否确认删除码头管理编号为"' + _ids + '"的数据项？').then(function() {
    return delPort(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 详情按钮操作 */
function handleViewData(row) {
  proxy.$refs["portViewRef"].open(row.id)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cle/port/export', {
    ...queryParams.value
  }, `port_${new Date().getTime()}.xlsx`)
}

getList()
</script>
