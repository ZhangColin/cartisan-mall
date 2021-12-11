<template>
  <el-row :gutter="20" class="app-container">
    <el-col :span="6">
      <category-tree @tree-node-click="handleCategoryNodeClick" />
    </el-col>
    <el-col :span="14">
      <attribute-list ref="attributeList" />
    </el-col>
    <el-col :span="4">
      <attribute-group-list ref="attributeGroupList" @attribute-group-reload="handleAttributeGroupReload" />
    </el-col>
  </el-row>
</template>

<script>

import AttributeList from './AttributeList'
import AttributeGroupList from './AttributeGroupList'
import CategoryTree from '@/views/goods/category/components/CategoryTree'

export default {
  name: 'Attribute',
  components: { CategoryTree, AttributeList, AttributeGroupList },
  data() {
    return {
      currentCategoryId: 0
    }
  },
  created() {
  },
  methods: {
    handleCategoryNodeClick(data, node, component) {
      if (node.isLeaf && this.currentCategoryId !== data.id) {
        this.currentCategoryId = data.id
        this.$refs.attributeList.initData(this.currentCategoryId)
        this.$refs.attributeGroupList.initData(this.currentCategoryId)
      }
    },
    handleAttributeGroupReload(groups) {
      this.$refs.attributeList.setGroups(groups)
    }
  }
}
</script>
