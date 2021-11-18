<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>
    <el-table
      v-loading="loading"
      :data="dataSource"
      row-key="id"
      class="table-container"
      element-loading-text="加载中..."
      stripe
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="退货原因ID" prop="id" />
      <el-table-column align="center" label="原因" prop="cause" />
      <el-table-column align="center" label="是否启用" prop="enable">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.enable"
            :active-value="true"
            :inactive-value="false"
            disabled
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序" prop="sequence" />
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
      :title="drawerTitle"
      :visible.sync="drawerVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">

          <el-form-item label="原因" prop="cause">
            <el-input v-model="entityData.cause" />
          </el-form-item>
          <el-form-item label="是否启用" prop="enable">
            <el-switch
              v-model="entityData.enable"
              :active-value="true"
              :inactive-value="false"
            />
          </el-form-item>
          <el-form-item label="排序" prop="sequence">
            <el-input-number v-model="entityData.sequence" />
          </el-form-item>
        </el-form>
        <div class="drawer__footer">
          <el-button @click="drawerVisible=false">取消</el-button>
          <el-button type="primary" @click="handleConfirm()">确定</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { CudMixin } from '@/mixins/cud-mixin'
import { ListMixin } from '@/mixins/list-mixin'

export default {
  name: 'ReturnCause',
  mixins: [ListMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/order/returnCauses',
      useSearch: false,
      defaultData: {
        cause: '',
        enable: true,
        sequence: 0
      },
      title: '退货原因',
      rules: {
        name: [
        ]
      }
    }
  },
  created() {
  },
  methods: {
  }
}
</script>

