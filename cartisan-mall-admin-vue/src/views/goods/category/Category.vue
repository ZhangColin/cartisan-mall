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
    <el-drawer
      :title="drawerTitle"
      :visible.sync="drawerVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
          <el-form-item label="名称" prop="name">
            <el-input v-model="entityData.name" />
          </el-form-item>
          <el-form-item label="是否显示" prop="isShow">
            <el-switch
              v-model="entityData.isShow"
              :active-value="true"
              :inactive-value="false"
            />
          </el-form-item>
          <el-form-item label="是否导航" prop="isMenu">
            <el-switch
              v-model="entityData.isMenu"
              :active-value="true"
              :inactive-value="false"
            />
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

import { getCategoryTree, moveCategories } from '@/api/goods/category-api'
import { add, edit, get, remove } from '@/api/common-api'

export default {
  name: 'Category',
  data() {
    return {
      apiBaseUrl: '/goods/categories',
      draggable: false,
      categoryTreeData: [],
      defaultProps: {
        label: 'name'
      },
      expandedKeys: [],
      drawerVisible: false,
      drawerTitle: '',
      defaultData: {
        parentId: 0,
        name: '',
        isShow: true,
        isMenu: false
      },
      entityData: {},
      title: '商品分类',
      rules: {
        name: [{ required: true, message: '请输入商品分类名称', trigger: 'blur' }]
      }
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
      this.entityData = Object.assign({}, this.defaultData)
      this.drawerTitle = `添加${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['entityDataForm'].clearValidate())
    },
    addSubCategory(data) {
      this.entityData = Object.assign({}, this.defaultData, { parentId: data.id })
      this.drawerTitle = `添加${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['entityDataForm'].clearValidate())
    },
    handleEdit(data) {
      get(this.apiBaseUrl, data.id).then(response => {
        this.entityData = Object.assign({}, response.data)
        this.drawerTitle = `编辑${this.title}`
        this.drawerVisible = true
        this.$nextTick(_ => this.$refs['entityDataForm'].clearValidate())
      })
    },
    handleDelete(data) {
      this.$confirm(`是否要删除该${this.title}`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        remove(this.apiBaseUrl, data.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '删除成功'
          })
          this.fetchData()
        })
      }).catch(() => {})
    },
    handleConfirm() {
      this.$refs['entityDataForm'].validate((valid) => {
        if (valid) {
          if (this.drawerTitle === `添加${this.title}`) {
            add(this.apiBaseUrl, this.entityData).then(() => {
              this.$notify.success({
                title: '成功',
                message: '添加成功'
              })
              this.drawerVisible = false
              if (this.entityData.parentId !== 0) {
                this.expandedKeys = Array.from(new Set([...this.expandedKeys, this.entityData.parentId]).keys())
              }
              this.fetchData()
            }).catch(() => {})
          } else {
            edit(this.apiBaseUrl, this.entityData.id, this.entityData).then(() => {
              this.$notify.success({
                title: '成功',
                message: '修改成功'
              })
              this.drawerVisible = false
              this.fetchData()
            }).catch(() => {})
          }
        }
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
          this.expandedKeys = Array.from(new Set([...this.expandedKeys, parentId]).keys())
        }
        this.fetchData()
      })
    },
    handleExpand(data) {
      this.expandedKeys = [...new Set([...this.expandedKeys, data.id])]
    },
    handleCollapse(data) {
      const index = this.expandedKeys.indexOf(data.id)
      if (index > -1) {
        this.expandedKeys.splice(index, 1)
      }
    }
  }
}
</script>

