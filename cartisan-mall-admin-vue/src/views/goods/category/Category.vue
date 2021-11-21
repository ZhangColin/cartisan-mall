<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item>
        <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-tree
      :data="categoryTreeData"
      :props="defaultProps"
      :expand-on-click-node="false"
      :default-expanded-keys="expandedKeys"
      node-key="id"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
      @node-expand="handleExpand"
      @node-collapse="handleCollapse"
    >
      <span slot-scope="{node, data}">
        <span class="el-tree-node__label">{{ node.label }}</span>
        <span style="padding-left: 20px;">
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="addSubCategory(data)">添加子类</el-button>
          <el-button type="text" size="mini" @click="handleEdit(data)">编辑</el-button>
          <el-button v-if="node.childNodes.length===0" type="text" size="mini" @click="handleDelete(data)">删除</el-button>
        </span>
      </span>
    </el-tree>
    <CategoryForm ref="categoryForm" @addSuccess="fetchData" @editSuccess="fetchData" />
  </div>
</template>

<script>

import { getCategoryTree, moveCategories } from '@/api/goods/category-api'
import { DeleteMixin } from '@/mixins/delete-mixin'
import { get } from '@/api/common-api'
import CategoryForm from '@/views/goods/category/CategoryForm'

export default {
  name: 'Category',
  components: { CategoryForm },
  mixins: [DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/categories',
      draggable: false,
      categoryTreeData: [],
      defaultProps: {
        label: 'name'
      },
      expandedKeys: [],

      title: '商品分类'
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getCategoryTree().then(response => { this.categoryTreeData = response.data })
    },
    handleAdd() {
      this.$refs.categoryForm.addByParent(0)
    },
    addSubCategory(data) {
      this.recordExpandedKeys(data.id)
      this.$refs.categoryForm.addByParent(data.id)
    },
    handleEdit(data) {
      get(this.apiBaseUrl, data.id).then(response => {
        this.$refs.categoryForm.edit(response.data)
      })
    },
    allowDrop(draggingNode, dropNode, type) {
      const deep = this.countNodeLevel(draggingNode)
      if (type === 'inner') {
        return deep + dropNode.level <= 3
      } else {
        return deep + dropNode.parent.level <= 3
      }
    },
    countNodeLevel(node) {
      const childLevels = (node.childNodes || []).map(node => this.countNodeLevel(node))
      return Math.max(...childLevels, 0) + 1
    },
    handleDrop(draggingNode, dropNode, dropType, ev) {
      let parentId = 0
      let siblings = null
      if (dropType === 'before' || dropType === 'after') {
        parentId = dropNode.data.parentId
        siblings = dropNode.parent.childNodes
      } else {
        parentId = dropNode.data.id
        siblings = dropNode.childNodes
      }
      moveCategories(siblings.map((node, index) => ({ id: node.data.id, parentId, sequence: index }))).then(_ => {
        if (parentId !== 0) {
          this.recordExpandedKeys(parentId)
        }
        this.fetchData()
      })
    },
    handleExpand(data) {
      this.recordExpandedKeys(data.id)
    },
    handleCollapse(data) {
      const index = this.expandedKeys.indexOf(data.id)
      if (index > -1) {
        this.expandedKeys.splice(index, 1)
      }
    },
    recordExpandedKeys(id) {
      this.expandedKeys = [...new Set([...this.expandedKeys, id])]
    }
  }
}
</script>
