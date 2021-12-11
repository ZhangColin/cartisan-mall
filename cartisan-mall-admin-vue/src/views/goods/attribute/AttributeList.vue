<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-button :disabled="!queryParam.categoryId" type="primary" @click="handleAdd">新增属性</el-button>
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
      <el-table-column align="center" label="属性" prop="name">
        <template slot-scope="scope">
          <el-row>属性：{{ scope.row.name }}</el-row>
          <el-row>属性组：{{ (groups.filter(group=>group.id===scope.row.groupId)[0]||{}).name }}</el-row>
        </template>
      </el-table-column>
      <el-table-column align="center" label="属性值" prop="inputType">
        <template slot-scope="scope">
          <el-row>{{ ({1:'手工录入',2:'从列表中选取'})[scope.row.inputType] }}</el-row>
          <el-row v-if="scope.row.inputType===2"><div v-for="(valueOption, index) in scope.row.valueOptions" :key="index">
            <el-tag size="mini">{{ valueOption }}</el-tag>
          </div></el-row>
        </template>
      </el-table-column>
      <el-table-column align="center" label="其它" prop="enable">
        <template slot-scope="scope">
          <el-row>是否启用：<el-switch
            v-model="scope.row.enable"
            :active-value="true"
            :inactive-value="false"
            disabled
          /></el-row>
          <el-row>快速展示：<el-switch
            v-model="scope.row.quickShow"
            :active-value="true"
            :inactive-value="false"
            disabled
          /></el-row>
        </template>
      </el-table-column>
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
    <attribute-form ref="attributeForm" @addSuccess="fetchData" @editSuccess="fetchData" />
  </div>
</template>

<script>

import AttributeForm from './AttributeForm'
import { DeleteMixin } from '@/mixins/delete-mixin'
import { search } from '@/api/common-api'

export default {
  name: 'AttributeList',
  components: { AttributeForm },
  mixins: [DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/attributes',
      title: '商品属性',
      queryParam: {},
      dataSource: [],
      loading: false,

      groups: []
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
    initData(categoryId) {
      this.queryParam.categoryId = categoryId
      this.fetchData()
    },
    setGroups(groups) {
      this.groups = groups
    },
    handleAdd() {
      this.$refs.attributeForm.addByCategoryIdWithGroups(this.queryParam.categoryId, this.groups)
    },
    handleEdit(row) {
      this.$refs.attributeForm.editWithGroups(row, this.groups)
    }
  }
}
</script>
