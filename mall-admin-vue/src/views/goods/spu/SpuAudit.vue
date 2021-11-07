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
      <el-table-column align="center" label="审核Id" prop="id" />
      <el-table-column align="center" label="SPUId" prop="spuId" />
      <el-table-column align="center" label="审核结果" prop="result" />
      <el-table-column align="center" label="反馈详情" prop="feedback" />
      <el-table-column align="center" label="审核人员" prop="auditor" />
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

          <el-form-item label="SPUId" prop="spuId">
            <el-input v-model="entityData.spuId" />
          </el-form-item>
          <el-form-item label="审核结果" prop="result">
            <el-input v-model="entityData.result" />
          </el-form-item>
          <el-form-item label="反馈详情" prop="feedback">
            <el-input v-model="entityData.feedback" />
          </el-form-item>
          <el-form-item label="审核人员" prop="auditor">
            <el-input v-model="entityData.auditor" />
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
  name: 'SpuAudit',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/spuAudits',

      defaultData: {

        spuId: '',
        result: '',
        feedback: '',
        auditor: ''
      },
      title: '商品审核详情',
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

