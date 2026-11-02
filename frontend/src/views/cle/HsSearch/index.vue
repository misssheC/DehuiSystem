<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="中文品名" prop="cnName">
        <el-input
          v-model="queryParams.cnName"
          placeholder="请输入中文品名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="英文品名" prop="enName">
        <el-input
          v-model="queryParams.enName"
          placeholder="请输入英文品名"
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

      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="HsSearchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="中文品名" align="center" prop="cnName" />
      <el-table-column label="英文品名" align="center" prop="enName" />
      <el-table-column label="通用CODE" align="center" prop="commonCode" />
      <el-table-column label="海关编码" align="center" prop="hsCode" />
      <el-table-column label="反倾销" align="center" prop="antiDumping" />
      <el-table-column label="CPSC" align="center" prop="cpsc" />
      <el-table-column label="是否需要认证" align="center" prop="needCert" />
      <el-table-column label="Article Description" align="center" prop="articleDescription" />
      <el-table-column label="总税率" align="center" prop="totalTaxRate" />
      <el-table-column label="基础税率" align="center" prop="baseTaxRate" />
      <el-table-column label="301加征税CODE" align="center" prop="section301Code" />
      <el-table-column label="301加征税" align="center" prop="section301Tax" />
      <el-table-column label="FL301/232附加税CODE" align="center" prop="fl301232Code" />
      <el-table-column label="FL301/232附加税" align="center" prop="fl301232Tax" />
      <el-table-column label="Cents/kg" align="center" prop="centsPerKg" />
      <el-table-column label="PGA 1" align="center" prop="pga1" />
      <el-table-column label="PGA 2" align="center" prop="pga2" />
      <el-table-column label="PGA 3" align="center" prop="pga3" />
      <el-table-column label="PGA 4" align="center" prop="pga4" />
      <el-table-column label="PGA 5" align="center" prop="pga5" />
      <el-table-column label="PGA 6" align="center" prop="pga6" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cle:HsSearch:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cle:HsSearch:remove']">删除</el-button>
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

    <!-- 添加或修改HS查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="HsSearchRef" :model="form" :rules="rules" label-width="100px">
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

<script setup name="HsSearch">
import { listHsSearch, getHsSearch, delHsSearch, addHsSearch, updateHsSearch } from "@/api/cle/HsSearch"

const { proxy } = getCurrentInstance()

const HsSearchList = ref([])
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
    cnName: undefined,
    enName: undefined,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询HS查询列表 */
function getList() {
  loading.value = true
  listHsSearch(queryParams.value).then(response => {
    HsSearchList.value = response.rows
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
    cnName: null,
    enName: null,
    commonCode: null,
    hsCode: null,
    antiDumping: null,
    cpsc: null,
    needCert: null,
    articleDescription: null,
    totalTaxRate: null,
    baseTaxRate: null,
    section301Code: null,
    section301Tax: null,
    fl301232Code: null,
    fl301232Tax: null,
    centsPerKg: null,
    pga1: null,
    pga2: null,
    pga3: null,
    pga4: null,
    pga5: null,
    pga6: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("HsSearchRef")
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
  title.value = "添加HS查询"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getHsSearch(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改HS查询"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["HsSearchRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateHsSearch(form.value).then(() => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addHsSearch(form.value).then(() => {
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
  proxy.$modal.confirm('是否确认删除HS查询编号为"' + _ids + '"的数据项？').then(function() {
    return delHsSearch(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cle/HsSearch/export', {
    ...queryParams.value
  }, `HsSearch_${new Date().getTime()}.xlsx`)
}

getList()
</script>
