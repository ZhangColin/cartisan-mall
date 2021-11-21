<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="brandForm" :model="brand" :rules="rules" label-width="120px">
        <el-form-item label="品牌名" prop="name">
          <el-input v-model="brand.name" />
        </el-form-item>
        <el-form-item label="首字母" prop="firstLetter">
          <el-input v-model="brand.firstLetter" />
        </el-form-item>
        <el-form-item label="品牌描述" prop="description">
          <el-input v-model="brand.description" />
        </el-form-item>
        <el-form-item label="LOGO" prop="logo">
          <SingleImage v-model="brand.logo" style="width: 300px;" />
        </el-form-item>
        <el-form-item label="排序" prop="sequence">
          <el-input-number v-model="brand.sequence" />
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

import SingleImage from '@/components/Upload/SingleImage2'
import { add, edit } from '@/api/common-api'

export default {
  name: 'BrandForm',
  components: { SingleImage },
  data() {
    return {
      drawerVisible: false,
      drawerTitle: '',

      apiBaseUrl: '/goods/brands',
      title: '品牌',

      defaultData: {
        name: '',
        logo: '',
        description: '',
        firstLetter: '',
        sequence: 0
      },
      brand: {},
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
  },
  created() {
  },
  methods: {
    add() {
      this.brand = Object.assign({}, this.defaultData)
      this.drawerTitle = `添加${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['brandForm'].clearValidate())
    },
    edit(row) {
      this.brand = Object.assign({}, row)

      this.drawerTitle = `编辑${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['brandForm'].clearValidate())
    },
    handleConfirm() {
      this.$refs['brandForm'].validate((valid) => {
        if (valid) {
          if (this.drawerTitle === `添加${this.title}`) {
            add(this.apiBaseUrl, this.brand).then(() => {
              this.$notify.success({
                title: '成功',
                message: '添加成功'
              })
              this.drawerVisible = false
              this.$emit('addSuccess')
            }).catch(() => {})
          } else {
            edit(this.apiBaseUrl, this.brand.id, this.brand).then(() => {
              this.$notify.success({
                title: '成功',
                message: '修改成功'
              })
              this.drawerVisible = false
              this.$emit('editSuccess')
            }).catch(() => {})
          }
        }
      })
    }
  }
}
</script>
