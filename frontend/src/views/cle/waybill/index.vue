<template>
  <div class="app-container">
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
      <el-form-item label="货柜状态" prop="containerStatus">
        <el-select v-model="queryParams.containerStatus" placeholder="请选择货柜状态" clearable>
          <el-option
            v-for="dict in container_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户" prop="customer">
        <el-input
          v-model="queryParams.customer"
          placeholder="请输入客户"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="给柜日期" prop="cutoffDate">
        <el-date-picker clearable
          v-model="queryParams.cutoffDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择给柜日期">
        </el-date-picker>
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
      <el-form-item label="ETA" style="width: 308px">
        <el-date-picker
          v-model="daterangeEta"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="DO日期" prop="doDate">
        <el-date-picker clearable
          v-model="queryParams.doDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择DO日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="计价方式" prop="priceType">
        <el-select v-model="queryParams.priceType" placeholder="请选择计价方式" clearable>
          <el-option
            v-for="dict in price_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="业务类型" prop="businessType">
        <el-select v-model="queryParams.businessType" placeholder="请选择业务类型" clearable>
          <el-option
            v-for="dict in business_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="录入方式" prop="inputType">
        <el-select v-model="queryParams.inputType" placeholder="请选择录入方式" clearable>
          <el-option
            v-for="dict in input_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="ISF匹配状态" prop="isf">
        <el-select v-model="queryParams.isf" placeholder="请选择ISF匹配状态" clearable>
          <el-option
            v-for="dict in isf_match_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['cle:waybill:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cle:waybill:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cle:waybill:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cle:waybill:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="waybillList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
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
      <el-table-column label="重量(KG)" align="center" prop="weight" />
      <el-table-column label="方数(CBM)" align="center" prop="volume" />
      <el-table-column label="给柜日期" align="center" prop="cutoffDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.cutoffDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="ETD" align="center" prop="etd" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.etd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="航程时间(Transit)" align="center" prop="transitDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.transitDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="ETA" align="center" prop="eta" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eta, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="清关状态" align="center" prop="customsStatus" />
      <el-table-column label="是否已发邮件资料" align="center" prop="isSendEmail" />
      <el-table-column label="清关时间" align="center" prop="customsDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.customsDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户备注" align="center" prop="customerRemark" />
      <el-table-column label="航线" align="center" prop="route" />
      <el-table-column label="起运港/起飞点" align="center" prop="pol" />
      <el-table-column label="中转港" align="center" prop="transPort" />
      <el-table-column label="目的港/落地地点" align="center" prop="pod" />
      <el-table-column label="船名航次" align="center" prop="vesselVoyage" />
      <el-table-column label="中转时间" align="center" prop="transitTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.transitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="DO日期" align="center" prop="doDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.doDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="放行时间" align="center" prop="releaseTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.releaseTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="运输方式" align="center" prop="transportType" />
      <el-table-column label="计价方式" align="center" prop="priceType">
        <template #default="scope">
          <dict-tag :options="price_type" :value="scope.row.priceType"/>
        </template>
      </el-table-column>
      <el-table-column label="业务类型" align="center" prop="businessType">
        <template #default="scope">
          <dict-tag :options="business_type" :value="scope.row.businessType"/>
        </template>
      </el-table-column>
      <el-table-column label="录入方式" align="center" prop="inputType">
        <template #default="scope">
          <dict-tag :options="input_type" :value="scope.row.inputType"/>
        </template>
      </el-table-column>
      <el-table-column label="进口商" align="center" prop="importer" />
      <el-table-column label="7501状态" align="center" prop="Status7501" />
      <el-table-column label="ISF匹配状态" align="center" prop="isf">
        <template #default="scope">
          <dict-tag :options="isf_match_status" :value="scope.row.isf"/>
        </template>
      </el-table-column>
      <el-table-column label="是否我方申报ISF" align="center" prop="isMyisf" />
      <el-table-column label="查验代码" align="center" prop="checkCode" />
      <el-table-column label="查验进展" align="center" prop="checkProgress" />
      <el-table-column label="布控方式" align="center" prop="controlMode" />
      <el-table-column label="业务员" align="center" prop="salesman" />
      <el-table-column label="操作人" align="center" prop="operator" />
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="操作员" align="center" prop="operateUser" />
      <el-table-column label="内部备注" align="center" prop="innerRemark" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" width="120" class-name="small-padding fixed-width" fixed="right">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleViewData(scope.row)" v-hasPermi="['cle:waybill:query']">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cle:waybill:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cle:waybill:remove']">删除</el-button>
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

    <!-- 我的运单详情抽屉 -->
    <waybill-view-drawer ref="waybillViewRef" />
    <!-- 添加或修改我的运单对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="waybillRef" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="提单号" prop="billNo">
              <el-input v-model="form.billNo" placeholder="请输入提单号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="柜号" prop="containerNo">
              <el-input v-model="form.containerNo" placeholder="请输入柜号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="货柜状态" prop="containerStatus">
              <el-select v-model="form.containerStatus" placeholder="请选择货柜状态">
                <el-option
                  v-for="dict in container_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="客户" prop="customer">
              <el-input v-model="form.customer" placeholder="请输入客户" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="船司" prop="carrier">
              <el-input v-model="form.carrier" placeholder="请输入船司" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="件数" prop="packageQty">
              <el-input v-model="form.packageQty" placeholder="请输入件数" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="重量(KG)" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入重量(KG)" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="方数(CBM)" prop="volume">
              <el-input v-model="form.volume" placeholder="请输入方数(CBM)" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="给柜日期" prop="cutoffDate">
              <el-date-picker clearable
                v-model="form.cutoffDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择给柜日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="ETD" prop="etd">
              <el-date-picker clearable
                v-model="form.etd"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择ETD">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="航程时间(Transit)" prop="transitDate">
              <el-date-picker clearable
                v-model="form.transitDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择航程时间(Transit)">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="ETA" prop="eta">
              <el-date-picker clearable
                v-model="form.eta"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择ETA">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否已发邮件资料" prop="isSendEmail">
              <el-input v-model="form.isSendEmail" placeholder="请输入是否已发邮件资料" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="清关时间" prop="customsDate">
              <el-date-picker clearable
                v-model="form.customsDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择清关时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="客户备注" prop="customerRemark">
              <el-input v-model="form.customerRemark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="航线" prop="route">
              <el-input v-model="form.route" placeholder="请输入航线" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="起运港/起飞点" prop="pol">
              <el-input v-model="form.pol" placeholder="请输入起运港/起飞点" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="中转港" prop="transPort">
              <el-input v-model="form.transPort" placeholder="请输入中转港" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="目的港/落地地点" prop="pod">
              <el-input v-model="form.pod" placeholder="请输入目的港/落地地点" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="船名航次" prop="vesselVoyage">
              <el-input v-model="form.vesselVoyage" placeholder="请输入船名航次" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="中转时间" prop="transitTime">
              <el-date-picker clearable
                v-model="form.transitTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择中转时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="DO日期" prop="doDate">
              <el-date-picker clearable
                v-model="form.doDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择DO日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="放行时间" prop="releaseTime">
              <el-date-picker clearable
                v-model="form.releaseTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择放行时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="计价方式" prop="priceType">
              <el-select v-model="form.priceType" placeholder="请选择计价方式">
                <el-option
                  v-for="dict in price_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="业务类型" prop="businessType">
              <el-select v-model="form.businessType" placeholder="请选择业务类型">
                <el-option
                  v-for="dict in business_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="录入方式" prop="inputType">
              <el-select v-model="form.inputType" placeholder="请选择录入方式">
                <el-option
                  v-for="dict in input_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="进口商" prop="importer">
              <el-input v-model="form.importer" placeholder="请输入进口商" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="ISF匹配状态" prop="isf">
              <el-select v-model="form.isf" placeholder="请选择ISF匹配状态">
                <el-option
                  v-for="dict in isf_match_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="是否我方申报ISF" prop="isMyisf">
              <el-input v-model="form.isMyisf" placeholder="请输入是否我方申报ISF" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="查验代码" prop="checkCode">
              <el-input v-model="form.checkCode" placeholder="请输入查验代码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="查验进展" prop="checkProgress">
              <el-input v-model="form.checkProgress" placeholder="请输入查验进展" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="布控方式" prop="controlMode">
              <el-input v-model="form.controlMode" placeholder="请输入布控方式" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="业务员" prop="salesman">
              <el-input v-model="form.salesman" placeholder="请输入业务员" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="操作人" prop="operator">
              <el-input v-model="form.operator" placeholder="请输入操作人" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="供应商" prop="supplier">
              <el-input v-model="form.supplier" placeholder="请输入供应商" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="操作员" prop="operateUser">
              <el-input v-model="form.operateUser" placeholder="请输入操作员" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="内部备注" prop="innerRemark">
              <el-input v-model="form.innerRemark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="删除标志" prop="delFlag">
              <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
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

<script setup name="Waybill">
import { listWaybill, getWaybill, delWaybill, addWaybill, updateWaybill } from "@/api/cle/waybill"
import WaybillViewDrawer from "./view"

const { proxy } = getCurrentInstance()
const { input_type, isf_match_status, price_type, container_status, business_type } = useDict('input_type', 'isf_match_status', 'price_type', 'container_status', 'business_type')

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
const daterangeEta = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    billNo: undefined,
    containerNo: undefined,
    containerStatus: undefined,
    customer: undefined,
    cutoffDate: undefined,
    etd: undefined,
    eta: undefined,
    doDate: undefined,
    priceType: undefined,
    businessType: undefined,
    inputType: undefined,
    isf: undefined,
  },
  rules: {
  }
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
  if (null != daterangeEta.value && '' != daterangeEta.value) {
    queryParams.value.params["beginEta"] = daterangeEta.value[0]
    queryParams.value.params["endEta"] = daterangeEta.value[1]
  }
  listWaybill(queryParams.value).then(response => {
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
    carrier: null,
    containerType: null,
    packageQty: null,
    weight: null,
    volume: null,
    cutoffDate: null,
    etd: null,
    transitDate: null,
    eta: null,
    customsStatus: null,
    isSendEmail: null,
    customsDate: null,
    customerRemark: null,
    route: null,
    pol: null,
    transPort: null,
    pod: null,
    vesselVoyage: null,
    transitTime: null,
    doDate: null,
    releaseTime: null,
    transportType: null,
    priceType: null,
    businessType: null,
    inputType: null,
    importer: null,
    Status7501: null,
    isf: null,
    isMyisf: null,
    checkCode: null,
    checkProgress: null,
    controlMode: null,
    salesman: null,
    operator: null,
    supplier: null,
    operateUser: null,
    innerRemark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
    delFlag: null
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
  daterangeEta.value = []
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

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getWaybill(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改我的运单"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["waybillRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateWaybill(form.value).then(() => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addWaybill(form.value).then(() => {
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
  proxy.$modal.confirm('是否确认删除我的运单编号为"' + _ids + '"的数据项？').then(function() {
    return delWaybill(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 详情按钮操作 */
function handleViewData(row) {
  proxy.$refs["waybillViewRef"].open(row.id)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cle/waybill/export', {
    ...queryParams.value
  }, `waybill_${new Date().getTime()}.xlsx`)
}

getList()
</script>
