<template>
  <el-row :gutter="20" class="app-container">
    <el-col :span="6">
      <category-tree @tree-node-click="handleCategoryNodeClick" />
    </el-col>
    <el-col :span="18">
      <el-form :inline="true">
        <el-form-item>
          <el-button :disabled="!queryParam.categoryId" type="primary" @click="handleAdd">新增</el-button>
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
        <el-table-column align="center" label="规格ID" prop="id" />
        <el-table-column align="center" label="规格名称" prop="name" />
        <el-table-column align="center" label="可选值列表" prop="valueOptions">
          <template slot-scope="scope">
            <div v-for="(valueOption, index) in scope.row.valueOptions" :key="index">
              <el-tag size="mini">{{ valueOption }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="是否支持手动新增" prop="manualAdd">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.manualAdd"
              :active-value="true"
              :inactive-value="false"
              disabled
            />
          </template>
        </el-table-column>
        <el-table-column align="center" label="是否启用" prop="enable">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enable"
              :active-value="true"
              :inactive-value="false"
              disabled
            />
          </template>
        </el-table-column>
        <el-table-column align="center" label="排序" prop="sequence" />
        <el-table-column align="center" label="操作" width="120">
          <template slot-scope="scope">
            <el-dropdown split-button @click="handleEdit( scope.row)">
              编辑
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="handleDelete(scope.row)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <specification-form ref="specificationForm" @addSuccess="fetchData" @editSuccess="fetchData" />
  </el-row>
</template>

<script>
import { DeleteMixin } from '@/mixins/delete-mixin'

import SpecificationForm from './SpecificationForm'
import CategoryTree from '../category/components/CategoryTree'
import { search } from '@/api/common-api'

export default {
  name: 'Specification',
  components: { SpecificationForm, CategoryTree },
  mixins: [DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/specifications',
      title: '商品规格',
      queryParam: {},
      dataSource: [],
      loading: false
    }
  },
  created() {
  },
  methods: {
    fetchData() {
      this.loading = true
      search(this.apiBaseUrl, this.queryParam).then(response => {
        this.dataSource = response.data
        this.loading = false
      })
    },
    handleAdd() {
      this.$refs.specificationForm.addByCategoryId(this.queryParam.categoryId)
    },
    handleEdit(row) {
      this.$refs.specificationForm.edit(row)
    },
    handleCategoryNodeClick(data, node, component) {
      if (node.isLeaf && this.queryParam.categoryId !== data.id) {
        this.queryParam.categoryId = data.id
        this.fetchData()
      }
    }
  }
}
</script>
