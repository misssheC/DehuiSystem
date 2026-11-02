<template>
  <el-drawer title="反倾销税信息详情" v-model="visible" direction="rtl" size="60%" append-to-body :before-close="handleClose" class="detail-drawer">
    <div v-loading="loading" class="drawer-content">
      <h4 class="section-header">基本信息</h4>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">案件编号：</label>
            <span class="info-value plaintext">
              {{ info.caseNo }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">涉及产品：</label>
            <span class="info-value plaintext">
              {{ info.enDesc }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">中文品名：</label>
            <span class="info-value plaintext">
              {{ info.cnDesc }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">税率：</label>
            <span class="info-value plaintext">
              {{ info.taxRate }}
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">政令发布日期：</label>
            <span class="info-value plaintext">
              {{ parseTime(info.issueDate, '{y}-{m}-{d}') }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">涉及海关编码：</label>
            <span class="info-value plaintext">
              {{ info.hsCode }}
            </span>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup name="AntiDumpingViewDrawer">
import { getAntiDumping } from '@/api/cle/AntiDumping'


const visible = ref(false)
const loading = ref(false)
const info = reactive({})

const open = async (id) => {
  visible.value = true
  loading.value = true
  try {
    const res = await getAntiDumping(id)
    Object.assign(info, res.data || {})
  } catch (error) {
    console.error('获取反倾销税信息信息失败:', error)
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
