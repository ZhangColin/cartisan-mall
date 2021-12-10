<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
        <el-form-item label="规格名称" prop="name">
          <el-input v-model="entityData.name" />
        </el-form-item>
        <el-form-item label="可选值列表" prop="valueOptions">
          <el-input-tag v-model="entityData.valueOptions" />
        </el-form-item>
        <el-form-item label="是否支持手动新增" prop="manualAdd">
          <el-switch
            v-model="entityData.manualAdd"
            :active-value="true"
            :inactive-value="false"
          />
        </el-form-item>
        <el-form-item label="是否启用" prop="enable">
          <el-switch
            v-model="entityData.enable"
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
  name: 'SpecificationForm',
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/specifications',
      title: '商品规格',

      defaultData: {
        categoryId: 0,
        name: '',
        valueOptions: [],
        manualAdd: false,
        enable: true,
        sequence: 0
      },
      rules: {
        name: [
          { required: true, message: '规格名称不能为空', trigger: 'blur' },
          { max: 32, message: '规格名称最大长度不能超过32', trigger: 'blur' }
        ],
        manualAdd: [
          { required: true, message: '是否支持手动新增不能为空', trigger: 'change' }
        ],
        enable: [
          { required: true, message: '是否启用不能为空', trigger: 'change' }
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
