<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6" />
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
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
      <el-table-column align="center" label="模板Id" prop="templateId" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="选项" prop="options" />
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
    <el-pagination
      :current-page.sync="page.currentPage"
      :page-sizes="[5, 10, 20, 50, 100]"
      :page-size="page.pageSize"
      :total="page.total"
      class="pagination-container"
      background
      align="right"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <el-drawer
      :title="drawerTitle"
      :visible.sync="drawerVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">

          <el-form-item label="模板Id" prop="templateId">
            <el-input v-model="entityData.templateId" />
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="选项" prop="options">
            <el-input v-model="entityData.options" />
          </el-form-item>
          <el-form-item label="排序" prop="sequence">
            <el-input v-model="entityData.sequence" />
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
import { PaginationMixin } from '@/mixins/pagination-mixin'
import { CudMixin } from '@/mixins/cud-mixin'

export default {
  name: 'Specification',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/specifications',

      defaultData: {

        templateId: '',
        name: '',
        options: '',
        sequence: ''
      },
      title: '规格',
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

