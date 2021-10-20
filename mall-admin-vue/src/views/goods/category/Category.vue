<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="12">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item v-for="pc in parentCategories" :key="pc.id"><a @click="handleShowBreadcrumbSubCategories(pc)">{{ pc.name }}</a></el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
      <el-col :span="12" align="right">
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
      <el-table-column align="center" label="分类ID" prop="id" />
      <el-table-column align="center" label="名称" prop="name">
        <template slot-scope="scope">
          <el-button v-if="parentCategories.length<3" type="text" @click="handleShowSubCategories(scope.row)">{{ scope.row.name }}</el-button>
          <span v-if="parentCategories.length===3">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="级别">
        <template>
          <span>{{ parentCategories.length }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品数量" />
      <el-table-column align="center" label="数量单位" />
      <el-table-column align="center" label="上级分类Id" prop="parentId" />
      <el-table-column align="center" label="是否显示" prop="isShow">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isShow"
            :active-value="true"
            :inactive-value="false"
            disabled
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否导航" prop="isMenu">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isMenu"
            :active-value="true"
            :inactive-value="false"
            disabled
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="模板" prop="templateName" />
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
      :page-sizes="[5, 10, 20]"
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

          <el-form-item label="上级分类Id" prop="parentId">
            <el-input v-model="entityData.parentId" />
          </el-form-item>
          <el-form-item label="模板Id" prop="templateId">
            <el-input v-model="entityData.templateId" />
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="是否显示" prop="isShow">
            <el-input v-model="entityData.isShow" />
          </el-form-item>
          <el-form-item label="是否导航" prop="isMenu">
            <el-input v-model="entityData.isMenu" />
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
  name: 'Category',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/categories',
      parentCategories: [{ id: 0, name: '顶级分类' }],
      defaultData: {
        parentId: '',
        templateId: '',
        name: '',
        isShow: '',
        isMenu: '',
        sequence: ''
      },
      title: '商品分类',
      rules: {
        name: [
        ]
      }
    }
  },
  created() {
  },
  methods: {
    handleShowSubCategories(category) {
      this.page.currentPage = 1
      this.queryParam.parentId = category.id
      this.parentCategories = [...this.parentCategories, { id: category.id, name: category.name }]
      this.fetchData()
    },
    handleShowBreadcrumbSubCategories(category) {
      this.page.currentPage = 1
      this.queryParam.parentId = category.id

      const categories = []

      for (const index in this.parentCategories) {
        const pc = this.parentCategories[index]
        categories.push(pc)
        if (pc.id === category.id) break
      }
      this.parentCategories = categories
      this.fetchData()
    }
  }
}
</script>

