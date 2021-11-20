<template>
  <el-drawer
    title="品牌分类关联"
    :visible.sync="visible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-row>
        <el-form>
          <el-form-item>
            <category-cascader v-model="categoryId" style="width: 100%" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleBuildRelation">关联</el-button>
          </el-form-item>
        </el-form>
        <el-divider />
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
          <el-table-column align="center" label="品牌" prop="brandName" />
          <el-table-column align="center" label="分类" prop="categoryName" />
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button split-button @click="handleCancelRelation(scope.row)">
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <div class="drawer__footer">
        <el-button @click="visible=false">关闭</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script>
import CategoryCascader from '@/components/goods/category/CategoryCascader'

import { getByBrand, buildRelation, cancelRelation } from '@/api/goods/brand-category-api'

export default {
  name: 'BrandCategory',
  components: { CategoryCascader },
  data() {
    return {
      visible: false,
      loading: false,
      dataSource: [],
      brandId: '',
      categoryId: ''
    }
  },
  created() {
  },
  methods: {
    show(brandId) {
      this.brandId = brandId
      this.visible = true
      this.$nextTick(() => {
        this.fetchData()
      })
    },
    fetchData() {
      this.loading = true
      getByBrand(this.brandId).then(response => {
        this.dataSource = response.data
        this.loading = false
      })
    },
    handleBuildRelation() {
      buildRelation(this.brandId, this.categoryId).then(_ => this.fetchData())
    },
    handleCancelRelation(row) {
      cancelRelation(this.brandId, row.categoryId).then(_ => this.fetchData())
    }
  }
}
</script>
