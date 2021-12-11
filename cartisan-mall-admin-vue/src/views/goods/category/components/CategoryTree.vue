<template>
  <div>
    <el-form>
      <el-form-item>
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
      </el-form-item>
    </el-form>
    <el-tree
      ref="categoryTree"
      :data="categoryTreeData"
      :props="defaultProps"
      :expand-on-click-node="false"
      node-key="id"
      :filter-node-method="filterNode"
      :highlight-current="true"
      @node-click="nodeClick"
    />
  </div>
</template>

<script>

import { getCategoryTree } from '@/api/goods/category-api'

export default {
  name: 'CategoryTree',
  data() {
    return {
      filterText: '',
      categoryTreeData: [],
      defaultProps: {
        label: 'name'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.categoryTree.filter(val)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    nodeClick(data, node, component) {
      this.$emit('tree-node-click', data, node, component)
    },
    fetchData() {
      getCategoryTree().then(response => { this.categoryTreeData = response.data })
    }
  }
}
</script>
