import { add, edit } from '@/api/common-api'

export const FormMixin = {
  data() {
    return {
      apiBaseUrl: '',
      drawerVisible: false,
      drawerTitle: '',
      defaultData: {},
      entityData: {},
      title: ''
    }
  },
  methods: {
    add() {
      this.entityData = Object.assign({}, this.defaultData)
      this.drawerTitle = `添加${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['entityDataForm'].clearValidate())
    },
    edit(row) {
      this.entityData = Object.assign({}, row)

      this.drawerTitle = `编辑${this.title}`
      this.drawerVisible = true
      this.$nextTick(_ => this.$refs['entityDataForm'].clearValidate())
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
              this.$emit('addSuccess')
            }).catch(() => {})
          } else {
            edit(this.apiBaseUrl, this.entityData.id, this.entityData).then(() => {
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
    },
    handleCancel() {
      this.drawerVisible = false
      this.$emit('cancel')
    }
  }
}
