<template>
  <div class="app-container">
    <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px">
      <el-form-item label="商品分类">
        <el-cascader v-model="categories" :props="categoryCascaderProps" style="width: 100%" placeholder="请选择商品分类" @change="handleCategoryCascaderChange" />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="entityData.name" />
      </el-form-item>
      <el-form-item label="副标题" prop="caption">
        <el-input v-model="entityData.caption" />
      </el-form-item>
      <el-form-item label="品牌" prop="brandId">
        <el-select v-model="entityData.brandId" placeholder="请选择品牌" filterable style="width: 100%">
          <el-option
            v-for="brand in brands"
            :key="brand.id"
            :label="brand.name"
            :value="brand.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="介绍" prop="introduction">
        <el-input
          v-model="entityData.introduction"
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
        />
      </el-form-item>
      <el-form-item label="货号" prop="sn">
        <el-input v-model="entityData.sn" />
      </el-form-item>
      <el-form-item label="售后服务" prop="saleService">
        <el-input v-model="entityData.saleService" />
      </el-form-item>

      <el-form-item v-for="(spec, index) in specifications" :key="'spec_'+spec.name+index" :label="`商品规格-${spec.name}`" prop="specificationItems">
        <el-checkbox-group v-model="specData[spec.name]">
          <el-checkbox v-for="option in spec.options" :key="option" :name="spec.name" :label="option" />
        </el-checkbox-group>
      </el-form-item>

      <el-form-item v-for="(param, index) in parameters" :key="'param_'+param.name+index" :label="`商品参数-${param.name}`" prop="parameterItems">
        <el-checkbox-group v-model="paramData[param.name]">
          <el-checkbox v-for="option in param.options" :key="option" :name="param.name" :label="option" />
        </el-checkbox-group>
      </el-form-item>

      <el-form-item label="图片" prop="image">
        <el-input v-model="entityData.image" />
      </el-form-item>
      <el-form-item label="图片列表" prop="images">
        <el-input v-model="entityData.images" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave">提交审核</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import { get, getAll, add, edit } from '@/api/common-api'
export default {
  name: 'GoodsForm',
  data() {
    return {
      entityData: {},
      defaultData: {
        sn: '',
        name: '',
        caption: '',
        brandId: '',
        category1Id: '',
        category2Id: '',
        category3Id: '',
        templateId: '',
        image: '',
        images: '',
        saleService: '',
        introduction: '',
        specificationItems: '',
        parameterItems: ''
      },
      rules: {
        name: [
        ]
      },
      categoryCascaderProps: {
        lazy: true,
        lazyLoad(node, resolve) {
          const { level } = node
          getAll('/goods/categories', { parentId: node.value })
            .then(response => resolve(response.data.map(category => ({
              value: category.id,
              label: category.name,
              leaf: level >= 2
            }))))
        },
        emitPath: true,
        checkStrictly: false
      },
      brands: [],
      specifications: [],
      parameters: [],
      specData: {},
      paramData: {},

      spuId: undefined,
      categories: []
    }
  },
  created() {
    getAll('/goods/brands').then(response => { this.brands = response.data })

    this.spuId = this.$route.params.spuId
    if (!this.spuId) {
      this.entityData = Object.assign({}, this.defaultData)
    } else {
      get('/goods/spus/', this.spuId).then(response => {
        this.entityData = response.data
        // this.categories = [this.entityData.category1Id, this.entityData.category2Id, this.entityData.category3Id]
        this.$set(this.categories, 0, this.entityData.category1Id)
        this.$set(this.categories, 1, this.entityData.category2Id)
        this.$set(this.categories, 2, this.entityData.category3Id)
        this.specData = JSON.parse(this.entityData.specificationItems)
        this.paramData = JSON.parse(this.entityData.parameterItems)

        get('/goods/categories', this.entityData.category3Id)
          .then(response => {
            const templateId = response.data.templateId
            this.entityData.templateId = templateId
            return Promise.all([getAll('/goods/specifications', { templateId }),
              getAll('/goods/parameters', { templateId })])
          })
          .then(responses => {
            this.specifications = responses[0].data
            this.parameters = responses[1].data
          })
      })
    }
  },
  methods: {
    handleCategoryCascaderChange(value) {
      const [category1Id, category2Id, category3Id] = value
      this.entityData = { ...this.entityData, category1Id, category2Id, category3Id }

      get('/goods/categories', this.entityData.category3Id)
        .then(response => {
          const templateId = response.data.templateId
          this.entityData.templateId = templateId
          return Promise.all([getAll('/goods/specifications', { templateId }),
            getAll('/goods/parameters', { templateId })])
        })
        .then(responses => {
          this.specifications = responses[0].data
          this.parameters = responses[1].data

          this.specData = {}
          this.paramData = {}

          this.specifications.forEach(spec => { this.$set(this.specData, spec.name, []) })
          this.parameters.forEach(param => { this.$set(this.paramData, param.name, []) })
        })
    },
    handleSave() {
      this.entityData.specificationItems = JSON.stringify(this.specData)
      this.entityData.parameterItems = JSON.stringify(this.paramData)

      if (!this.spuId) {
        add('/goods/spus', this.entityData).then(() => {
          this.$notify.success({
            title: '成功',
            message: '添加成功'
          })
          this.$router.go(-1)
        }).catch(() => {
        })
      } else {
        edit('/goods/spus', this.spuId, this.entityData).then(() => {
          this.$notify.success({
            title: '成功',
            message: '编辑成功'
          })
          this.$router.go(-1)
        }).catch(() => {
        })
      }
    }
  }
}
</script>

