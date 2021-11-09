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
      <el-table-column align="center" label="订单Id" prop="id" />
      <el-table-column align="center" label="订单Id" prop="orderId" />
      <el-table-column align="center" label="申请时间" prop="applied" />
      <el-table-column align="center" label="用户Id" prop="userId" />
      <el-table-column align="center" label="用户名称" prop="username" />
      <el-table-column align="center" label="联系人" prop="contract" />
      <el-table-column align="center" label="联系人手机" prop="contractMobile" />
      <el-table-column align="center" label="类型（1:退货 2：退款）" prop="type" />
      <el-table-column align="center" label="退款金额" prop="refundAmount" />
      <el-table-column align="center" label="是否退运费" prop="refundFreight" />
      <el-table-column align="center" label="状态（0:申请 1：同意 2：驳回）" prop="status" />
      <el-table-column align="center" label="处理时间" prop="processed" />
      <el-table-column align="center" label="退货退款原因" prop="returnCause" />
      <el-table-column align="center" label="凭证图片" prop="evidence" />
      <el-table-column align="center" label="问题描述" prop="description" />
      <el-table-column align="center" label="处理备注" prop="remark" />
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

          <el-form-item label="订单Id" prop="orderId">
            <el-input v-model="entityData.orderId" />
          </el-form-item>
          <el-form-item label="申请时间" prop="applied">
            <el-input v-model="entityData.applied" />
          </el-form-item>
          <el-form-item label="用户Id" prop="userId">
            <el-input v-model="entityData.userId" />
          </el-form-item>
          <el-form-item label="用户名称" prop="username">
            <el-input v-model="entityData.username" />
          </el-form-item>
          <el-form-item label="联系人" prop="contract">
            <el-input v-model="entityData.contract" />
          </el-form-item>
          <el-form-item label="联系人手机" prop="contractMobile">
            <el-input v-model="entityData.contractMobile" />
          </el-form-item>
          <el-form-item label="类型（1:退货 2：退款）" prop="type">
            <el-input v-model="entityData.type" />
          </el-form-item>
          <el-form-item label="退款金额" prop="refundAmount">
            <el-input v-model="entityData.refundAmount" />
          </el-form-item>
          <el-form-item label="是否退运费" prop="refundFreight">
            <el-input v-model="entityData.refundFreight" />
          </el-form-item>
          <el-form-item label="状态（0:申请 1：同意 2：驳回）" prop="status">
            <el-input v-model="entityData.status" />
          </el-form-item>
          <el-form-item label="处理时间" prop="processed">
            <el-input v-model="entityData.processed" />
          </el-form-item>
          <el-form-item label="退货退款原因" prop="returnCause">
            <el-input v-model="entityData.returnCause" />
          </el-form-item>
          <el-form-item label="凭证图片" prop="evidence">
            <el-input v-model="entityData.evidence" />
          </el-form-item>
          <el-form-item label="问题描述" prop="description">
            <el-input v-model="entityData.description" />
          </el-form-item>
          <el-form-item label="处理备注" prop="remark">
            <el-input v-model="entityData.remark" />
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
  name: 'ReturnOrder',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/returnOrders',

      defaultData: {

        orderId: '',
        applied: '',
        userId: '',
        username: '',
        contract: '',
        contractMobile: '',
        type: '',
        refundAmount: '',
        refundFreight: '',
        status: '',
        processed: '',
        returnCause: '',
        evidence: '',
        description: '',
        remark: ''
      },
      title: '退货退款订单',
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

