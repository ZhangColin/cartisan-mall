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
      <el-table-column align="center" label="规格Id" prop="id" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="选项" prop="options">
        <template slot-scope="scope">
          <span>{{ scope.row.options.join(',') }}</span>
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
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="选项" prop="options">
            <el-input-tag v-model="entityData.options" />
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
import { ListMixin } from '@/mixins/list-mixin'
import { CudMixin } from '@/mixins/cud-mixin'

export default {
  name: 'Specification',
  mixins: [ListMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/specifications',
      useSearch: false,
      queryParam: { templateId: this.$route.query.templateId },
      defaultData: {
        templateId: this.$route.query.templateId,
        name: '',
        options: [],
        sequence: 0
      },
      title: '规格',
      rules: {
        name: [{ required: true, message: '请输入参数名称', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.$route.meta.title = this.$route.query.templateName + ' - 规格'
  },
  methods: {
  }
}
</script>

