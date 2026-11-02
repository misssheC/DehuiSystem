<template>
  <el-drawer title="税率查询详情" v-model="visible" direction="rtl" size="60%" append-to-body :before-close="handleClose" class="detail-drawer">
    <div v-loading="loading" class="drawer-content">
      <h4 class="section-header">基本信息</h4>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">海关编码：</label>
            <span class="info-value plaintext">
              {{ info.hsCode }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">反倾销：</label>
            <span class="info-value plaintext">
              {{ info.antiDumping }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">CPSC：</label>
            <span class="info-value plaintext">
              {{ info.cpsc }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">是否需要认证：</label>
            <span class="info-value plaintext">
              {{ info.needCert }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">总税率：</label>
            <span class="info-value plaintext">
              {{ info.totalTaxRate }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">中文描述：</label>
            <span class="info-value plaintext">
              {{ info.cnDesc }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">官方英文描述：</label>
            <span class="info-value plaintext">
              {{ info.officialDesc }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">基础税率：</label>
            <span class="info-value plaintext">
              {{ info.baseTaxRate }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">301加征税CODE：</label>
            <span class="info-value plaintext">
              {{ info.section301Code }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">301加征税：</label>
            <span class="info-value plaintext">
              {{ info.section301Tax }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">FL301/232附加税CODE：</label>
            <span class="info-value plaintext">
              {{ info.fl301232Code }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">FL301/232附加税：</label>
            <span class="info-value plaintext">
              {{ info.fl301232Tax }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">单位1：</label>
            <span class="info-value plaintext">
              {{ info.unit1 }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">单位2：</label>
            <span class="info-value plaintext">
              {{ info.unit2 }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">Cents/kg：</label>
            <span class="info-value plaintext">
              {{ info.centsPerKg }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">生效日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.effectiveDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">失效日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.expirationDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA1：</label>
            <span class="info-value plaintext">
              {{ info.pga1 }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA2：</label>
            <span class="info-value plaintext">
              {{ info.pga2 }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA3：</label>
            <span class="info-value plaintext">
              {{ info.pga3 }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA4：</label>
            <span class="info-value plaintext">
              {{ info.pga4 }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA5：</label>
            <span class="info-value plaintext">
              {{ info.pga5 }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">PGA6：</label>
            <span class="info-value plaintext">
              {{ info.pga6 }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">创建日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.createTime, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup name="TaxViewDrawer">
import { getTax } from '@/api/cle/tax'


const visible = ref(false)
const loading = ref(false)
const info = reactive({})

const open = async (id) => {
  visible.value = true
  loading.value = true
  try {
    const res = await getTax(id)
    Object.assign(info, res.data || {})
  } catch (error) {
    console.error('获取税率查询信息失败:', error)
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
