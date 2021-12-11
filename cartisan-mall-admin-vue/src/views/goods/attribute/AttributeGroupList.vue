<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-button :disabled="!queryParam.categoryId" type="primary" @click="handleAdd">新增属性分组</el-button>
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
      :show-header="false"
    >
      <el-table-column align="left">
        <template slot-scope="scope">
          <el-row>
            <el-image v-if="scope.row.icon" style="width: 100px; height: 100px" :src="scope.row.icon" fit="fill" />
            <span>{{ scope.row.name }}</span>
          </el-row>
          <el-row>
            <el-button type="primary" @click.native="handleEdit( scope.row)">编辑</el-button>
            <el-button type="danger" @click.native="handleDelete(scope.row)">删除</el-button>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <attributeGroup-form ref="attributeGroupForm" @addSuccess="fetchData" @editSuccess="fetchData" />
  </div>
</template>

<script>

import AttributeGroupForm from './AttributeGroupForm'
import { DeleteMixin } from '@/mixins/delete-mixin'
import { search } from '@/api/common-api'

export default {
  name: 'AttributeGroupList',
  components: { AttributeGroupForm },
  mixins: [DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/attributeGroups',
      title: '商品属性分组',
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

        this.$emit('attribute-group-reload', this.dataSource.map(group => ({ id: group.id, name: group.name })))
      })
    },
    initData(categoryId) {
      this.queryParam.categoryId = categoryId
      this.fetchData()
    },
    handleAdd() {
      this.$refs.attributeGroupForm.addByCategoryId(this.queryParam.categoryId)
    },
    handleEdit(row) {
      this.$refs.attributeGroupForm.edit(row)
    }
  }
}
</script>
