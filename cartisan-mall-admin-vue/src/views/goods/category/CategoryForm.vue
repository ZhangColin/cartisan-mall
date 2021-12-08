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
        <el-form-item label="图标" prop="icon">
          <SingleImage v-model="entityData.icon" style="width: 300px;" />
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
import SingleImage from '@/components/Upload/SingleImage2'

export default {
  name: 'CategoryForm',
  components: { SingleImage },
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/categories',
      title: '商品分类',

      defaultData: {
        parentId: 0,
        name: '',
        icon: ''
      },
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' },
          { max: 64, message: '名称最大长度不能超过64', trigger: 'blur' }
        ]
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

