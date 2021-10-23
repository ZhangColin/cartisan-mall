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
      <el-table-column align="center" label="SPUId" prop="id" />
      <el-table-column align="center" label="货号" prop="sn" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="副标题" prop="caption" />
      <el-table-column align="center" label="品牌Id" prop="brandId" />
      <el-table-column align="center" label="一级分类Id" prop="category1Id" />
      <el-table-column align="center" label="二级分类Id" prop="category2Id" />
      <el-table-column align="center" label="三级分类Id" prop="category3Id" />
      <el-table-column align="center" label="模板Id" prop="templateId" />
      <el-table-column align="center" label="图片" prop="image" />
      <el-table-column align="center" label="图片列表" prop="images" />
      <el-table-column align="center" label="售后服务" prop="saleService" />
      <el-table-column align="center" label="介绍" prop="introduction" />
      <el-table-column align="center" label="规格" prop="specificationItems" />
      <el-table-column align="center" label="参数" prop="parameterItems" />
      <el-table-column align="center" label="是否上架" prop="isMarketable" />
      <el-table-column align="center" label="是否启用规格" prop="enableSpecification" />
      <el-table-column align="center" label="审核状态" prop="auditStatus" />
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

          <el-form-item label="货号" prop="sn">
            <el-input v-model="entityData.sn" />
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="副标题" prop="caption">
            <el-input v-model="entityData.caption" />
          </el-form-item>
          <el-form-item label="品牌Id" prop="brandId">
            <el-input v-model="entityData.brandId" />
          </el-form-item>
          <el-form-item label="一级分类Id" prop="category1Id">
            <el-input v-model="entityData.category1Id" />
          </el-form-item>
          <el-form-item label="二级分类Id" prop="category2Id">
            <el-input v-model="entityData.category2Id" />
          </el-form-item>
          <el-form-item label="三级分类Id" prop="category3Id">
            <el-input v-model="entityData.category3Id" />
          </el-form-item>
          <el-form-item label="模板Id" prop="templateId">
            <el-input v-model="entityData.templateId" />
          </el-form-item>
          <el-form-item label="图片" prop="image">
            <el-input v-model="entityData.image" />
          </el-form-item>
          <el-form-item label="图片列表" prop="images">
            <el-input v-model="entityData.images" />
          </el-form-item>
          <el-form-item label="售后服务" prop="saleService">
            <el-input v-model="entityData.saleService" />
          </el-form-item>
          <el-form-item label="介绍" prop="introduction">
            <el-input v-model="entityData.introduction" />
          </el-form-item>
          <el-form-item label="规格" prop="specificationItems">
            <el-input v-model="entityData.specificationItems" />
          </el-form-item>
          <el-form-item label="参数" prop="parameterItems">
            <el-input v-model="entityData.parameterItems" />
          </el-form-item>
          <el-form-item label="是否上架" prop="isMarketable">
            <el-input v-model="entityData.isMarketable" />
          </el-form-item>
          <el-form-item label="是否启用规格" prop="enableSpecification">
            <el-input v-model="entityData.enableSpecification" />
          </el-form-item>
          <el-form-item label="审核状态" prop="auditStatus">
            <el-input v-model="entityData.auditStatus" />
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
  name: 'Spu',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/spus',

      defaultData: {

        sn: '',
        name: '',
        caption: '',
        brandId: '',
        category1Id: '',
        category2Id: '',
        category3Id: '',
        templateId: '',
        image: '',
        images: '',
        saleService: '',
        introduction: '',
        specificationItems: '',
        parameterItems: '',
        isMarketable: '',
        enableSpecification: '',
        auditStatus: ''
      },
      title: 'SPU',
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

