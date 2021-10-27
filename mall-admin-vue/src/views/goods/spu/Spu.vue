<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="queryParam.nameOrSn" class="filter-item" placeholder="商品名称/商品货号" clearable />
      </el-col>
      <el-col :span="6">
        <el-cascader :props="categoryCascaderProps" clearable style="width: 100%" placeholder="请选择商品分类" @change="handleCategoryCascaderChange" />
      </el-col>
      <el-col :span="6">
        <el-select v-model="queryParam.brandId" placeholder="请选择品牌" filterable clearable style="width: 100%">
          <el-option
            v-for="brand in brands"
            :key="brand.id"
            :label="brand.name"
            :value="brand.id"
          />
        </el-select></el-col>
      <el-col :span="6">
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
      <el-table-column align="center" label="商品Id" prop="id" width="160" />
      <el-table-column align="center" label="商品图片" prop="image" width="140">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.image"
            fit="contain"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品名称" prop="name">
        <template slot-scope="scope">
          <div>{{ scope.row.name }}</div>
          <div>品牌：{{ scope.row.brandName }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="价格/货号" prop="sn" width="160">
        <template slot-scope="scope">
          <span>SN：{{ scope.row.sn }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否上架" prop="isMarketable" width="80">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isMarketable"
            :active-value="true"
            :inactive-value="false"
            disabled
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="审核状态" prop="auditStatus" width="80">
        <template slot-scope="scope">
          <span>{{ ({0:'未审核', 1: '通过', 2: '未通过'})[scope.row.auditStatus] }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">日志</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">审核详情</el-dropdown-item>
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
import { CudMixin } from '@/mixins/cud-mixin'

import { getAll } from '@/api/common-api'

export default {
  name: 'Spu',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/spus',

      title: 'SPU',
      categoryCascaderProps: {
        lazy: true,
        lazyLoad(node, resolve) {
          const { level } = node
          getAll('/goods/categories', { parentId: node.value })
            .then(response => resolve(response.data.map(category => ({
              value: category.id,
              label: category.name,
              leaf: level >= 2
            }))))
        },
        emitPath: true,
        checkStrictly: true
      },
      brands: []
    }
  },
  created() {
    getAll('/goods/brands').then(response => { this.brands = response.data })
  },
  methods: {
    handleCategoryCascaderChange(value) {
      const [category1Id, category2Id, category3Id] = value
      this.queryParam = { ...this.queryParam, category1Id, category2Id, category3Id }
    }
  }
}
</script>

