<template>
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
        <el-form-item label="组图标" prop="icon">
          <el-input v-model="entityData.icon" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="entityData.description" />
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
  name: 'AttributeGroupForm',
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/attributeGroups',
      title: '属性分组',

      defaultData: {
        categoryId: 0,
        name: '',
        icon: '',
        description: '',
        sequence: 0
      },
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' },
          { max: 32, message: '名称最大长度不能超过32', trigger: 'blur' }
        ],
        icon: [
          { max: 256, message: '组图标最大长度不能超过256', trigger: 'blur' }
        ],
        description: [
          { max: 256, message: '描述最大长度不能超过256', trigger: 'blur' }
        ],
        sequence: [
          { required: true, message: '排序不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    addByCategoryId(categoryId) {
      this.defaultData.categoryId = categoryId
      this.add()
    }
  }
}
</script>
