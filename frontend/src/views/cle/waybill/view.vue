<template>
  <el-drawer title="我的运单详情" v-model="visible" direction="rtl" size="60%" append-to-body :before-close="handleClose" class="detail-drawer">
    <div v-loading="loading" class="drawer-content">
      <h4 class="section-header">基本信息</h4>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">提单号：</label>
            <span class="info-value plaintext">
              {{ info.billNo }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">柜号：</label>
            <span class="info-value plaintext">
              {{ info.containerNo }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">货柜状态：</label>
            <span class="info-value plaintext">
              <dict-tag :options="container_status" :value="info.containerStatus" />
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">客户：</label>
            <span class="info-value plaintext">
              {{ info.customer }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">船司：</label>
            <span class="info-value plaintext">
              {{ info.carrier }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">柜型：</label>
            <span class="info-value plaintext">
              {{ info.containerType }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">件数：</label>
            <span class="info-value plaintext">
              {{ info.packageQty }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">重量(KG)：</label>
            <span class="info-value plaintext">
              {{ info.weight }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">方数(CBM)：</label>
            <span class="info-value plaintext">
              {{ info.volume }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">给柜日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.cutoffDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">ETD：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.etd, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">航程时间(Transit)：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.transitDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">ETA：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.eta, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">清关状态：</label>
            <span class="info-value plaintext">
              {{ info.customsStatus }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">是否已发邮件资料：</label>
            <span class="info-value plaintext">
              {{ info.isSendEmail }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">清关时间：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.customsDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">客户备注：</label>
            <span class="info-value plaintext">
              {{ info.customerRemark }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">航线：</label>
            <span class="info-value plaintext">
              {{ info.route }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">起运港/起飞点：</label>
            <span class="info-value plaintext">
              {{ info.pol }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">中转港：</label>
            <span class="info-value plaintext">
              {{ info.transPort }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">目的港/落地地点：</label>
            <span class="info-value plaintext">
              {{ info.pod }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">船名航次：</label>
            <span class="info-value plaintext">
              {{ info.vesselVoyage }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">中转时间：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.transitTime, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">DO日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.doDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">放行时间：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.releaseTime, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">运输方式：</label>
            <span class="info-value plaintext">
              {{ info.transportType }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">计价方式：</label>
            <span class="info-value plaintext">
              <dict-tag :options="price_type" :value="info.priceType" />
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">业务类型：</label>
            <span class="info-value plaintext">
              <dict-tag :options="business_type" :value="info.businessType" />
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">录入方式：</label>
            <span class="info-value plaintext">
              <dict-tag :options="input_type" :value="info.inputType" />
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">进口商：</label>
            <span class="info-value plaintext">
              {{ info.importer }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">7501状态：</label>
            <span class="info-value plaintext">
              {{ info.Status7501 }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">ISF匹配状态：</label>
            <span class="info-value plaintext">
              <dict-tag :options="isf_match_status" :value="info.isf" />
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">是否我方申报ISF：</label>
            <span class="info-value plaintext">
              {{ info.isMyisf }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">查验代码：</label>
            <span class="info-value plaintext">
              {{ info.checkCode }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">查验进展：</label>
            <span class="info-value plaintext">
              {{ info.checkProgress }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">布控方式：</label>
            <span class="info-value plaintext">
              {{ info.controlMode }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">业务员：</label>
            <span class="info-value plaintext">
              {{ info.salesman }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">操作人：</label>
            <span class="info-value plaintext">
              {{ info.operator }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">供应商：</label>
            <span class="info-value plaintext">
              {{ info.supplier }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">操作员：</label>
            <span class="info-value plaintext">
              {{ info.operateUser }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">内部备注：</label>
            <span class="info-value plaintext">
              {{ info.innerRemark }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">创建者：</label>
            <span class="info-value plaintext">
              {{ info.createBy }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">创建时间：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.createTime, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">更新者：</label>
            <span class="info-value plaintext">
              {{ info.updateBy }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">更新时间：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.updateTime, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">备注：</label>
            <span class="info-value plaintext">
              {{ info.remark }}
            </span>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup name="WaybillViewDrawer">
import { getWaybill } from '@/api/cle/waybill'

const { input_type, isf_match_status, price_type, container_status, business_type } = useDict('input_type', 'isf_match_status', 'price_type', 'container_status', 'business_type')

const visible = ref(false)
const loading = ref(false)
const info = reactive({})

const open = async (id) => {
  visible.value = true
  loading.value = true
  try {
    const res = await getWaybill(id)
    Object.assign(info, res.data || {})
  } catch (error) {
    console.error('获取我的运单信息失败:', error)
  } finally {
    loading.value = false
  }
}

function handleClose() {
  visible.value = false
  Object.keys(info).forEach(key => delete info[key])
}

defineExpose({ open })
</script>
