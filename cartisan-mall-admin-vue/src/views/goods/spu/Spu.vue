<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="queryParam.nameOrSn" class="filter-item" placeholder="商品名称/商品货号" clearable />
      </el-col>
      <el-col :span="6">
        <el-cascader :props="categoryCascaderProps" clearable style="width: 100%" placeholder="请选择商品分类" @change="handleCategoryCascaderChange" />
      </el-col>
      <el-col :span="6">
        <el-select v-model="queryParam.brandId" placeholder="请选择品牌" filterable clearable style="width: 100%">
          <el-option
            v-for="brand in brands"
            :key="brand.id"
            :label="brand.name"
            :value="brand.id"
          />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
        <el-button class="filter-item" type="primary" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>
    <el-table
      v-loading="loading"
      :data="dataSource"
      row-key="id"
      class="table-container"
      element-loading-text="加载中..."
      stripe
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="商品ID" prop="id" width="160" />
      <el-table-column align="center" label="商品图片" prop="image" width="140">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.image"
            fit="contain"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品名称" prop="name">
        <template slot-scope="scope">
          <div>{{ scope.row.name }}</div>
          <div>品牌：{{ scope.row.brandName }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="价格/货号" prop="sn" width="160">
        <template slot-scope="scope">
          <span>SN：{{ scope.row.sn }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否上架" prop="isMarketable" width="80">
        <template slot-scope="scope">
          <el-popconfirm
            :title="`确定要${(scope.row.isMarketable?'上架':'下架')}[${scope.row.name}]`"
            confirm-button-text="确定"
            cancel-button-text="取消"
            @confirm="handleIsMarketableConfirmChange(scope.$index, scope.row)"
            @cancel="handleIsMarketableCancelChange(scope.$index, scope.row)"
          >
            <el-switch
              slot="reference"
              v-model="scope.row.isMarketable"
              :active-value="true"
              :inactive-value="false"
            />
          </el-popconfirm>
        </template>
      </el-table-column>
      <el-table-column align="center" label="审核状态" prop="auditStatus" width="80">
        <template slot-scope="scope">
          <span>{{ ({0:'未审核', 1: '通过', 2: '未通过'})[scope.row.auditStatus] }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="showSku(scope.$index, scope.row)">SKU</el-dropdown-item>
              <el-dropdown-item @click.native="showLog(scope.$index, scope.row)">日志</el-dropdown-item>
              <el-dropdown-item @click.native="handleAudit(scope.$index, scope.row)">审核</el-dropdown-item>
              <el-dropdown-item @click.native="showAuditDetail(scope.$index, scope.row)">审核详情</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="page.currentPage"
      :page-sizes="[5, 10, 20, 50, 100]"
      :page-size="page.pageSize"
      :total="page.total"
      class="pagination-container"
      background
      align="right"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <el-drawer
      title="商品日志"
      :visible.sync="drawerLogVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-table
          v-loading="loading"
          :data="logDataSource"
          row-key="id"
          class="table-container"
          element-loading-text="加载中..."
          stripe
          border
          fit
          highlight-current-row
        >
          <el-table-column align="center" label="日志内容" prop="content" />
          <el-table-column align="center" label="操作人员" prop="operator" />
          <el-table-column align="center" label="操作时间" prop="operateDate" />
        </el-table>
        <div class="drawer__footer">
          <el-button @click="drawerLogVisible=false">关闭</el-button>
        </div>
      </div>
    </el-drawer>
    <el-drawer
      title="商品审核详情"
      :visible.sync="drawerAuditDetailVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-table
          v-loading="loading"
          :data="auditDataSource"
          row-key="id"
          class="table-container"
          element-loading-text="加载中..."
          stripe
          border
          fit
          highlight-current-row
        >
          <el-table-column align="center" label="审核结果" prop="result" />
          <el-table-column align="center" label="反馈详情" prop="feedback" />
          <el-table-column align="center" label="审核人员" prop="auditor" />
          <el-table-column align="center" label="审核时间" prop="auditDate" />
        </el-table>
        <div class="drawer__footer">
          <el-button @click="drawerAuditDetailVisible=false">关闭</el-button>
        </div>
      </div>
    </el-drawer>

    <el-drawer
      title="商品审核"
      :visible.sync="auditVisible"
      :wrapper-closable="false"
      size="50%"
    >
      <div class="drawer__content">
        <el-form ref="auditForm" :model="auditData" label-width="120px">
          <el-form-item label="审核结果" prop="result">
            <el-radio-group v-model="auditData.result">
              <el-radio :label="true">审核通过</el-radio>
              <el-radio :label="false">审核不通过</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="反馈详情" prop="feedback">
            <el-input v-model="auditData.feedback" />
          </el-form-item>
        </el-form>
        <div class="drawer__footer">
          <el-button @click="auditVisible=false">取消</el-button>
          <el-button type="primary" @click="handleAuditConfirm()">确定</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { PaginationMixin } from '@/mixins/pagination-mixin'
import { add, edit, getAll, remove } from '@/api/common-api'
import { getSpuLogs, getSpuAudits, approve, reject, put, pull } from '@/api/goods/spu-api'

export default {
  name: 'Spu',
  mixins: [PaginationMixin],
  data() {
    return {
      apiBaseUrl: '/goods/spus',

      title: 'SPU',
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
        checkStrictly: true
      },
      brands: [],

      drawerLogVisible: false,
      logDataSource: [],

      drawerAuditDetailVisible: false,
      auditDataSource: [],
      auditVisible: false,
      auditData: { spuId: 0, result: false, feedback: '' }
    }
  },
  created() {
    getAll('/goods/brands').then(response => { this.brands = response.data })
  },
  methods: {
    handleCategoryCascaderChange(value) {
      const [category1Id, category2Id, category3Id] = value
      this.queryParam = { ...this.queryParam, category1Id, category2Id, category3Id }
    },
    showSku(index, spu) {
      this.$router.push({ path: `/goods/spu/sku?spuId=${spu.id}` })
    },
    showLog(index, spu) {
      getSpuLogs(spu.id).then(response => {
        this.drawerLogVisible = true
        this.logDataSource = response.data
      })
    },
    handleAudit(index, spu) {
      this.auditData = { spuId: spu.id, result: false, feedback: '' }
      this.auditVisible = true
    },
    handleAuditConfirm() {
      if (this.auditData.result) {
        approve(this.auditData.spuId, this.auditData.feedback).then(_ => {
          this.auditVisible = false
          this.$notify.success({
            title: '成功',
            message: '审核成功'
          })
        })
      } else {
        reject(this.auditData.spuId, this.auditData.feedback).then(_ => {
          this.auditVisible = false
          this.$notify.success({
            title: '成功',
            message: '审核成功'
          })
        })
      }
    },
    showAuditDetail(index, spu) {
      getSpuAudits(spu.id).then(response => {
        this.drawerAuditDetailVisible = true
        this.auditDataSource = response.data
      })
    },
    handleIsMarketableConfirmChange(index, row) {
      if (row.isMarketable) {
        put(row.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '上架成功'
          })
        }).catch(() => {
          row.isMarketable = !row.isMarketable
        })
      } else {
        pull(row.id).then(() => {
          this.$notify.success({
            title: '成功',
            message: '下架成功'
          })
        }).catch(() => {
          row.isMarketable = !row.isMarketable
        })
      }
    },
    handleIsMarketableCancelChange(index, row) {
      row.isMarketable = !row.isMarketable
    },
    handleAdd() {
      this.$router.push({ path: `/goods/spu/goodsForm` })
    },
    handleEdit(index, row) {
      this.$router.push({ path: `/goods/spu/goodsForm/${row.id}` })
    },
    handleDelete(index, row) {
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
              this.fetchData()
            }).catch(() => {})
          } else {
            edit(this.apiBaseUrl, this.entityData.id, this.entityData).then(() => {
              this.$notify.success({
                title: '成功',
                message: '修改成功'
              })
              this.drawerVisible = false
              this.fetchData()
            }).catch(() => {})
          }
        }
      })
    }
  }
}
</script>

