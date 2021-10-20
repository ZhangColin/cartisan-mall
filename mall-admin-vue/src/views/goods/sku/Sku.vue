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
      <el-table-column align="center" label="SKUid" prop="id" />
      <el-table-column align="center" label="SPUId" prop="spuId" />
      <el-table-column align="center" label="商品条码" prop="sn" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="规格" prop="specification" />
      <el-table-column align="center" label="价格（分）" prop="price" />
      <el-table-column align="center" label="重量（克）" prop="weight" />
      <el-table-column align="center" label="库存数量" prop="quantity" />
      <el-table-column align="center" label="库存预警数量" prop="alertQuantity" />
      <el-table-column align="center" label="商品图片" prop="image" />
      <el-table-column align="center" label="商品图片列表" prop="images" />
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
          <el-form-item label="商品条码" prop="sn">
            <el-input v-model="entityData.sn" />
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="规格" prop="specification">
            <el-input v-model="entityData.specification" />
          </el-form-item>
          <el-form-item label="价格（分）" prop="price">
            <el-input v-model="entityData.price" />
          </el-form-item>
          <el-form-item label="重量（克）" prop="weight">
            <el-input v-model="entityData.weight" />
          </el-form-item>
          <el-form-item label="库存数量" prop="quantity">
            <el-input v-model="entityData.quantity" />
          </el-form-item>
          <el-form-item label="库存预警数量" prop="alertQuantity">
            <el-input v-model="entityData.alertQuantity" />
          </el-form-item>
          <el-form-item label="商品图片" prop="image">
            <el-input v-model="entityData.image" />
          </el-form-item>
          <el-form-item label="商品图片列表" prop="images">
            <el-input v-model="entityData.images" />
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
  name: 'Sku',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/skus',

      defaultData: {

        spuId: '',
        sn: '',
        name: '',
        specification: '',
        price: '',
        weight: '',
        quantity: '',
        alertQuantity: '',
        image: '',
        images: ''
      },
      title: 'SKU',
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

