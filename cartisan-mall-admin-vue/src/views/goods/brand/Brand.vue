<template>
  <div class="app-container">
    <el-form :inline="true" @keyup.enter.native="handleSearch">
      <el-form-item>
        <el-input v-model="queryParam.name" class="filter-item" placeholder="请输入名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-input v-model="queryParam.firstLetter" maxlength="1" class="filter-item" placeholder="请输入首字母" clearable />
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
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleCategory(scope.$index, scope.row)">关联分类</el-dropdown-item>
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
          <el-form-item label="品牌名" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="首字母" prop="firstLetter">
            <el-input v-model="entityData.firstLetter" />
          </el-form-item>
          <el-form-item label="品牌描述" prop="description">
            <el-input v-model="entityData.description" />
          </el-form-item>
          <el-form-item label="LOGO" prop="logo">
            <SingleImage v-model="entityData.logo" style="width: 300px;" />
          </el-form-item>
          <el-form-item label="排序" prop="sequence">
            <el-input-number v-model="entityData.sequence" />
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

import SingleImage from '@/components/Upload/SingleImage2'

export default {
  name: 'Brand',
  components: { SingleImage },
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/brands',

      defaultData: {
        name: '',
        logo: '',
        description: '',
        firstLetter: '',
        sequence: 0
      },
      title: '品牌',
      rules: {
        name: [{ required: true, message: '品牌名称不能为空', trigger: 'blur' }],
        logo: [{ required: true, message: '品牌Logo地址不能为空', trigger: 'blur' }],
        firstLetter: [
          { required: true, message: '品牌的首字母不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error('品牌的首字母必须是一个字母'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }],
        sequence: [
          { required: true, message: '排序不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (!Number.isInteger(value) || value < 0) {
                callback(new Error('排序必须大于等于0'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }]
      }
    }
  },
  created() {
  },
  methods: {
    handleCategory() {
      // this.entityData.logo = res.url
    }
  }
}
</script>
