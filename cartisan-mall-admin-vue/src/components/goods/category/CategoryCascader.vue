<template>
  <el-cascader
    :value="value"
    filterable
    clearable
    :options="categoryTreeData"
    :props="props"
    @change="handleChange"
  />
</template>

<script>
import { getCategoryTree } from '@/api/goods/category-api'

export default {
  name: 'CategoryCascader',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      props: {
        expandTrigger: 'hover',
        emitPath: false,
        value: 'id',
        label: 'name',
        children: 'children'
      },
      categoryTreeData: []
    }
  },
  created() {
    getCategoryTree().then(response => { this.categoryTreeData = response.data })
  },
  methods: {
    handleChange(value) {
      this.$emit('input', value)
    }
  }
}
</script>
