<template>
  <el-drawer title="码头管理详情" v-model="visible" direction="rtl" size="60%" append-to-body :before-close="handleClose" class="detail-drawer">
    <div v-loading="loading" class="drawer-content">
      <h4 class="section-header">基本信息</h4>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">港头名称：</label>
            <span class="info-value plaintext">
              {{ info.portName }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">港口类型：</label>
            <span class="info-value plaintext">
              <dict-tag :options="port_type" :value="info.portType" />
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">航线名称：</label>
            <span class="info-value plaintext">
              <dict-tag :options="route_type" :value="info.routeType" />
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">是否清关：</label>
            <span class="info-value plaintext">
              <dict-tag :options="is_clear" :value="info.clearCustomsFlag" />
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mb8">
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">装货港代码：</label>
            <span class="info-value plaintext">
              {{ info.loadPortCode }}
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="info-item">
            <label class="info-label">卸货港代码：</label>
            <span class="info-value plaintext">
              {{ info.unloadPortCode }}
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
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup name="PortViewDrawer">
import { getPort } from '@/api/cle/port'

const { is_clear, port_type, route_type } = useDict('is_clear', 'port_type', 'route_type')

const visible = ref(false)
const loading = ref(false)
const info = reactive({})

const open = async (id) => {
  visible.value = true
  loading.value = true
  try {
    const res = await getPort(id)
    Object.assign(info, res.data || {})
  } catch (error) {
    console.error('获取码头管理信息失败:', error)
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
