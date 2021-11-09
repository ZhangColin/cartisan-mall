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
      <el-table-column align="center" label="总金额" prop="totalAmount" />
      <el-table-column align="center" label="优惠金额" prop="discountAmount" />
      <el-table-column align="center" label="运费" prop="freight" />
      <el-table-column align="center" label="实付金额" prop="actualAmount" />
      <el-table-column align="center" label="支付类型（0:货到付款 1：在线支付）" prop="payType" />
      <el-table-column align="center" label="下单时间" prop="placed" />
      <el-table-column align="center" label="支付时间" prop="paid" />
      <el-table-column align="center" label="发货时间" prop="delivered" />
      <el-table-column align="center" label="交易完成时间" prop="completed" />
      <el-table-column align="center" label="交易关闭时间" prop="closed" />
      <el-table-column align="center" label="物流名称" prop="shippingName" />
      <el-table-column align="center" label="物流单号" prop="shippingCode" />
      <el-table-column align="center" label="用户名称" prop="username" />
      <el-table-column align="center" label="买家留言" prop="buyerMessage" />
      <el-table-column align="center" label="是否评价" prop="buyerRate" />
      <el-table-column align="center" label="收货人" prop="receiverContact" />
      <el-table-column align="center" label="收货人手机" prop="receiverMobile" />
      <el-table-column align="center" label="收货人地址" prop="receiverAddress" />
      <el-table-column align="center" label="订单来源（1:Web 2：APP 3：微信公众号 4：微信小程序 5：H5）" prop="sourceType" />
      <el-table-column align="center" label="订单状态" prop="orderStatus" />
      <el-table-column align="center" label="支付状态" prop="payStatus" />
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

          <el-form-item label="总金额" prop="totalAmount">
            <el-input v-model="entityData.totalAmount" />
          </el-form-item>
          <el-form-item label="优惠金额" prop="discountAmount">
            <el-input v-model="entityData.discountAmount" />
          </el-form-item>
          <el-form-item label="运费" prop="freight">
            <el-input v-model="entityData.freight" />
          </el-form-item>
          <el-form-item label="实付金额" prop="actualAmount">
            <el-input v-model="entityData.actualAmount" />
          </el-form-item>
          <el-form-item label="支付类型（0:货到付款 1：在线支付）" prop="payType">
            <el-input v-model="entityData.payType" />
          </el-form-item>
          <el-form-item label="下单时间" prop="placed">
            <el-input v-model="entityData.placed" />
          </el-form-item>
          <el-form-item label="支付时间" prop="paid">
            <el-input v-model="entityData.paid" />
          </el-form-item>
          <el-form-item label="发货时间" prop="delivered">
            <el-input v-model="entityData.delivered" />
          </el-form-item>
          <el-form-item label="交易完成时间" prop="completed">
            <el-input v-model="entityData.completed" />
          </el-form-item>
          <el-form-item label="交易关闭时间" prop="closed">
            <el-input v-model="entityData.closed" />
          </el-form-item>
          <el-form-item label="物流名称" prop="shippingName">
            <el-input v-model="entityData.shippingName" />
          </el-form-item>
          <el-form-item label="物流单号" prop="shippingCode">
            <el-input v-model="entityData.shippingCode" />
          </el-form-item>
          <el-form-item label="用户名称" prop="username">
            <el-input v-model="entityData.username" />
          </el-form-item>
          <el-form-item label="买家留言" prop="buyerMessage">
            <el-input v-model="entityData.buyerMessage" />
          </el-form-item>
          <el-form-item label="是否评价" prop="buyerRate">
            <el-input v-model="entityData.buyerRate" />
          </el-form-item>
          <el-form-item label="收货人" prop="receiverContact">
            <el-input v-model="entityData.receiverContact" />
          </el-form-item>
          <el-form-item label="收货人手机" prop="receiverMobile">
            <el-input v-model="entityData.receiverMobile" />
          </el-form-item>
          <el-form-item label="收货人地址" prop="receiverAddress">
            <el-input v-model="entityData.receiverAddress" />
          </el-form-item>
          <el-form-item label="订单来源（1:Web 2：APP 3：微信公众号 4：微信小程序 5：H5）" prop="sourceType">
            <el-input v-model="entityData.sourceType" />
          </el-form-item>
          <el-form-item label="订单状态" prop="orderStatus">
            <el-input v-model="entityData.orderStatus" />
          </el-form-item>
          <el-form-item label="支付状态" prop="payStatus">
            <el-input v-model="entityData.payStatus" />
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
  name: 'Order',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/orders',

      defaultData: {

        totalAmount: '',
        discountAmount: '',
        freight: '',
        actualAmount: '',
        payType: '',
        placed: '',
        paid: '',
        delivered: '',
        completed: '',
        closed: '',
        shippingName: '',
        shippingCode: '',
        username: '',
        buyerMessage: '',
        buyerRate: '',
        receiverContact: '',
        receiverMobile: '',
        receiverAddress: '',
        sourceType: '',
        orderStatus: '',
        payStatus: ''
      },
      title: '订单',
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

