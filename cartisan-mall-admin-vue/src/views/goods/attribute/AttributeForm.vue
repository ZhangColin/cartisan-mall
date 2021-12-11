<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
        <el-form-item label="分组ID" prop="groupId">
          <el-select v-model="entityData.groupId" placeholder="请选择属性分组">
            <el-option v-for="group in groups" :key="group.id" :label="group.name" :value="group.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="属性名称" prop="name">
          <el-input v-model="entityData.name" />
        </el-form-item>
        <el-form-item label="属性录入方式" prop="inputType">
          <el-select v-model="entityData.inputType" placeholder="请选择属性录入方式" @change="handleInputTypeChange">
            <el-option label="手工录入" :value="1" />
            <el-option label="从列表中选取" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="可选值列表" prop="valueOptions">
          <el-input-tag v-model="entityData.valueOptions" :read-only="entityData.inputType!==2" />
        </el-form-item>
        <el-form-item label="是否启用" prop="enable">
          <el-switch
            v-model="entityData.enable"
            :active-value="true"
            :inactive-value="false"
          />
        </el-form-item>
        <el-form-item label="快速展示" prop="quickShow">
          <el-switch
            v-model="entityData.quickShow"
            :active-value="true"
            :inactive-value="false"
          />
        </el-form-item>
        <el-form-item label="排序" prop="sequence">
          <el-input-number v-model="entityData.sequence" />
        </el-form-item>
      </el-form>
      <div class="drawer__footer">
        <el-button @click="handleCancel()">取消</el-button>
        <el-button type="primary" @click="handleConfirm()">确定</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script>

import { FormMixin } from '@/mixins/form-mixin'

export default {
  name: 'AttributeForm',
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/attributes',
      title: '商品属性',

      defaultData: {
        categoryId: 0,
        groupId: 0,
        name: '',
        inputType: 1,
        valueOptions: [],
        enable: true,
        quickShow: false,
        sequence: 0
      },
      rules: {
        categoryId: [
          { required: true, message: '分类ID不能为空', trigger: 'blur' }
        ],
        groupId: [
          { required: true, message: '分组ID不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '属性名称不能为空', trigger: 'blur' },
          { max: 32, message: '属性名称最大长度不能超过32', trigger: 'blur' }
        ],
        inputType: [
          { required: true, message: '属性录入方式不能为空', trigger: 'change' }
        ],
        enable: [
          { required: true, message: '是否启用不能为空', trigger: 'change' }
        ],
        quickShow: [
          { required: true, message: '快速展示不能为空', trigger: 'change' }
        ],
        sequence: [
          { required: true, message: '排序不能为空', trigger: 'blur' }
        ]
      },
      groups: []
    }
  },
  methods: {
    addByCategoryIdWithGroups(categoryId, groups) {
      this.defaultData.categoryId = categoryId
      this.groups = groups
      this.defaultData.groupId = this.groups[0].id || 0
      this.add()
    },
    editWithGroups(row, groups) {
      this.groups = groups
      this.edit(row)
    },
    handleInputTypeChange(value) {
      this.entityData.valueOptions = []
    }
  }
}
</script>
