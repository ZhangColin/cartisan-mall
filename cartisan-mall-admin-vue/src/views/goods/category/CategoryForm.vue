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
</template>

<script>

import { FormMixin } from '@/mixins/form-mixin'

export default {
  name: 'CategoryForm',
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/categories',
      title: '商品分类',

      defaultData: {
        parentId: 0,
        name: '',
        isShow: true,
        isMenu: false
      },
      rules: {
        name: [{ required: true, message: '请输入商品分类名称', trigger: 'blur' }]
      }
    }
  },
  created() {
  },
  methods: {
    addByParent(parentId) {
      this.defaultData.parentId = parentId
      this.add()
    }
  }
}
</script>

