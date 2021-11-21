<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
        <el-form-item label="品牌名" prop="name">
          <el-input v-model="entityData.name" />
        </el-form-item>
        <el-form-item label="首字母" prop="firstLetter">
          <el-input v-model="entityData.firstLetter" maxlength="1" />
        </el-form-item>
        <el-form-item label="品牌描述" prop="description">
          <el-input v-model="entityData.description" />
        </el-form-item>
        <el-form-item label="LOGO" prop="logo">
          <SingleImage v-model="entityData.logo" style="width: 300px;" />
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
import SingleImage from '@/components/Upload/SingleImage2'

export default {
  name: 'BrandForm',
  components: { SingleImage },
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/brands',
      title: '品牌',

      defaultData: {
        name: '',
        logo: '',
        description: '',
        firstLetter: '',
        sequence: 0
      },
      rules: {
        name: [{ required: true, message: '品牌名称不能为空', trigger: 'blur' }],
        logo: [{ required: true, message: '品牌Logo地址不能为空', trigger: 'blur' }],
        firstLetter: [
          { required: true, message: '品牌的首字母不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error('品牌的首字母必须是一个字母'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }],
        sequence: [
          { required: true, message: '排序不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (!Number.isInteger(value) || value < 0) {
                callback(new Error('排序必须大于等于0'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }]
      }
    }
  }
}
</script>
