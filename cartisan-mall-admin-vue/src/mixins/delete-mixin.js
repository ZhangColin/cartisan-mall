import { remove } from '@/api/common-api'

export const DeleteMixin = {
  data() {
    return {
      apiBaseUrl: '',
      title: ''
    }
  },
  methods: {
    handleDelete(row) {
      this.$confirm(`是否要删除该${this.title}`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        remove(this.apiBaseUrl, row.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '删除成功'
          })
          this.fetchData()
        })
      }).catch(() => {})
    }
  }
}
