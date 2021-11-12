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
    <el-row :gutter="20">
      <el-col v-for="(album, index) in dataSource" :key="album.id" :span="6" :style="{marginBottom:'20px'}">
        <el-card :body-style="{ padding: '0px'}">
          <el-image
            :style="{width:'100%', height: windowWidth /4+'px'}"
            :src="album.coverImage"
            fit="cover"
            @click="showImages(album)"
          />
          <div style="padding: 14px;">
            <span>{{ album.title }}</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button" @click="handleDelete(index, album)">删除</el-button>
              <el-button type="text" class="button" style="margin-right: 5px" @click="handleEdit(index, album)">编辑</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
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
import { ListMixin } from '@/mixins/list-mixin'
import { CudMixin } from '@/mixins/cud-mixin'

export default {
  name: 'Album',
  mixins: [ListMixin, CudMixin],
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
      },
      windowWidth: document.documentElement.clientWidth
    }
  },
  mounted() {
    const that = this
    window.onresize = () => {
      that.windowWidth = document.documentElement.clientWidth
    }
  },
  destroyed() {
    window.onresize = null
  },
  created() {
  },
  methods: {
    showImages(album) {
      this.$router.push({ path: `/goods/album/albumImages?albumId=${album.id}&albumTitle=${album.title}` })
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
/*.el-row {*/
/*    display: flex;*/
/*    flex-wrap: wrap;*/
/*}*/

/*.el-card{*/
/*    min-width: 100%;*/
/*    height: 100%;*/
/*}*/

.time {
    font-size: 13px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.image {
    width: 100%;
    display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

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
