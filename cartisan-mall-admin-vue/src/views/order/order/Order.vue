<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6" />
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
      </el-col>
    </el-row>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="全部订单" name="0" />
      <el-tab-pane label="待付款" name="1" />
      <el-tab-pane label="待发货" name="2" />
      <el-tab-pane label="已发货" name="3" />
      <el-tab-pane label="已完成" name="4" />
      <el-tab-pane label="已关闭" name="5" />
    </el-tabs>
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
      <el-table-column align="center" label="订单ID" prop="id" />
      <el-table-column align="center" label="下单时间" prop="placed" />
      <el-table-column align="center" label="用户名称" prop="username" />
      <el-table-column align="center" label="总金额" prop="totalAmount" />
      <el-table-column align="center" label="支付方式" prop="payType" />
      <el-table-column align="center" label="订单来源" prop="sourceType" />
      <el-table-column align="center" label="订单状态" prop="orderStatus" />
      <el-table-column align="center" label="支付状态" prop="payStatus" />
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            查看订单
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">订单发货</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">订单跟踪</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">关闭订单</el-dropdown-item>
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
  </div>
</template>

<script>
import { PaginationMixin } from '@/mixins/pagination-mixin'

export default {
  name: 'Order',
  mixins: [PaginationMixin],
  data() {
    return {
      apiBaseUrl: '/order/orders',
      activeName: '0',
      title: '订单'
    }
  },
  created() {
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

