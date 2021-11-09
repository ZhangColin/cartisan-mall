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
      <el-table-column align="center" label="订单明细Id" prop="id" />
      <el-table-column align="center" label="订单Id" prop="orderId" />
      <el-table-column align="center" label="SPUId" prop="spuId" />
      <el-table-column align="center" label="SKUId" prop="skuId" />
      <el-table-column align="center" label="商品名称" prop="name" />
      <el-table-column align="center" label="单价（分）" prop="price" />
      <el-table-column align="center" label="数量" prop="quantity" />
      <el-table-column align="center" label="重量（克）" prop="weight" />
      <el-table-column align="center" label="图片" prop="image" />
      <el-table-column align="center" label="是否退货" prop="isReturn" />
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
          <el-form-item label="SPUId" prop="spuId">
            <el-input v-model="entityData.spuId" />
          </el-form-item>
          <el-form-item label="SKUId" prop="skuId">
            <el-input v-model="entityData.skuId" />
          </el-form-item>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="单价（分）" prop="price">
            <el-input v-model="entityData.price" />
          </el-form-item>
          <el-form-item label="数量" prop="quantity">
            <el-input v-model="entityData.quantity" />
          </el-form-item>
          <el-form-item label="重量（克）" prop="weight">
            <el-input v-model="entityData.weight" />
          </el-form-item>
          <el-form-item label="图片" prop="image">
            <el-input v-model="entityData.image" />
          </el-form-item>
          <el-form-item label="是否退货" prop="isReturn">
            <el-input v-model="entityData.isReturn" />
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
  name: 'OrderItem',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/orderItems',

      defaultData: {

        orderId: '',
        spuId: '',
        skuId: '',
        name: '',
        price: '',
        quantity: '',
        weight: '',
        image: '',
        isReturn: ''
      },
      title: '订单明细',
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

