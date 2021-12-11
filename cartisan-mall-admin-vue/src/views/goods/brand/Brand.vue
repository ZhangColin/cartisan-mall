<template>
  <div class="app-container">
    <el-form :inline="true" @keyup.enter.native="handleSearch">
      <el-form-item>
        <el-input v-model="queryParam.name" placeholder="请输入名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-input v-model="queryParam.firstLetter" maxlength="1" placeholder="请输入首字母" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
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
      <el-table-column align="center" label="品牌ID" prop="id" />
      <el-table-column align="center" label="品牌名" prop="name" />
      <el-table-column align="center" label="首字母" prop="firstLetter" />
      <el-table-column align="center" label="LOGO" prop="logo">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 40px"
            :src="scope.row.logo"
            fit="contain"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序" prop="sequence" />
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleCategoryRelation(scope.row)">关联分类</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.row)">删除</el-dropdown-item>
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
    <brand-form ref="brandForm" @addSuccess="fetchData" @editSuccess="fetchData" />
    <BrandCategory ref="brandCategory" />
  </div>
</template>

<script>
import { PaginationMixin } from '@/mixins/pagination-mixin'
import { DeleteMixin } from '@/mixins/delete-mixin'

import BrandCategory from '@/views/goods/brand/BrandCategory'
import BrandForm from '@/views/goods/brand/BrandForm'

export default {
  name: 'Brand',
  components: { BrandCategory, BrandForm },
  mixins: [PaginationMixin, DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/brands',
      title: '品牌'
    }
  },
  created() {
  },
  methods: {
    handleAdd() {
      this.$refs.brandForm.add()
    },
    handleEdit(row) {
      this.$refs.brandForm.edit(row)
    },
    handleCategoryRelation(brand) {
      this.$refs.brandCategory.show(brand.id)
    }
  }
}
</script>
