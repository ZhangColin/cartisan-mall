<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="entityData.title" />
        </el-form-item>
        <el-form-item label="封面" prop="coverImage">
          <SingleImage v-model="entityData.coverImage" style="width: 300px;" />
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
import SingleImage from '@/components/Upload/SingleImage2'

export default {
  name: 'AlbumForm',
  components: { SingleImage },
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/goods/albums',
      components: { SingleImage },
      title: '相册',

      defaultData: {
        title: '',
        coverImage: '',
        description: '',
        sequence: 0
      },
      rules: {
        title: [
          { required: true, message: '标题不能为空', trigger: 'blur' },
          { max: 64, message: '标题最大长度不能超过64', trigger: 'blur' }
        ],
        coverImage: [
          { max: 256, message: '封面最大长度不能超过256', trigger: 'blur' }
        ],
        description: [
          { max: 256, message: '描述最大长度不能超过256', trigger: 'blur' }
        ],
        sequence: [
          { required: true, message: '排序不能为空', trigger: 'blur' }
        ]
      }
    }
  }
}
</script>
