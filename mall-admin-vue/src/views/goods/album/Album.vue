<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="queryParam.title" class="filter-item" placeholder="请输入相册标题查询" clearable />
      </el-col>
      <el-col :span="12">
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
      <el-table-column align="center" label="相册Id" prop="id" />
      <el-table-column align="center" label="标题" prop="title">
        <template slot-scope="scope">
          <el-button type="text" @click="showImages(scope.row)">{{ scope.row.title }}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="封面" prop="coverImage">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.coverImage"
            fit="contain"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
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
            <el-upload
              class="avatar-uploader"
              action="http://localhost:7001/file/upload"
              :show-file-list="false"
              :on-success="handleUploadSuccess"
              :before-upload="beforeUpload"
            >
              <el-image v-if="entityData.coverImage" :src="entityData.coverImage" class="avatar" fit="contain" />
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>
          </el-form-item>
        </el-form>
        <div class="drawer__footer">
          <el-button @click="drawerVisible=false">取消</el-button>
          <el-button type="primary" @click="handleConfirm()">确定</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { PaginationMixin } from '@/mixins/pagination-mixin'
import { CudMixin } from '@/mixins/cud-mixin'

export default {
  name: 'Album',
  mixins: [PaginationMixin, CudMixin],
  data() {
    return {
      apiBaseUrl: '/goods/albums',

      defaultData: {
        title: '',
        coverImage: ''
      },
      title: '相册',
      rules: {
        title: [{ required: true, message: '请输入相册标题', trigger: 'blur' }]
      }
    }
  },
  created() {
  },
  methods: {
    showImages(row) {
      this.$router.push({ path: `/goods/album/albumImages?albumId=${row.id}&albumTitle=${row.title}` })
    },
    handleUploadSuccess(res, file) {
      this.entityData.coverImage = res.url
    },
    beforeUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
