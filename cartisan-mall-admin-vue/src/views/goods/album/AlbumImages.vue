<template>
  <div class="app-container">
    <el-upload
      action="http://localhost:7001/file/upload"
      list-type="picture-card"
      :on-success="handleUploadSuccess"
      :file-list="fileList"
    >
      <i slot="default" class="el-icon-plus" />
      <div slot="file" slot-scope="{file}">
        <el-image
          class="el-upload-list__item-thumbnail"
          :src="file.url"
          fit="contain"
        />
        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
            <i class="el-icon-zoom-in" />
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleCopyUrl(file, $event)"
          >
            <i class="el-icon-document-copy" />
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
          >
            <i class="el-icon-delete" />
          </span>
        </span>
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <el-image width="100%" :src="dialogImageUrl" fit="contain" />
    </el-dialog>
  </div>
</template>

<script>

import clipboard from '@/utils/clipboard'
import { getAlbumImages, addAlbumImage, removeAlbumImage } from '@/api/goods/album-api'

export default {
  name: 'AlbumImages',
  data() {
    return {
      apiBaseUrl: '/goods/albums',

      fileList: [],
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false
    }
  },
  created() {
    this.$route.meta.title = this.$route.query.albumTitle
    getAlbumImages(this.$route.query.albumId).then(response => {
      this.fileList = response.data.map(url => ({ url }))
    })
  },
  methods: {
    handleUploadSuccess(res, file) {
      addAlbumImage(this.$route.query.albumId, res.url).then(() => {
      })
    },
    handleRemove(file) {
      removeAlbumImage(this.$route.query.albumId, file.url).then(() => {
        this.fileList.splice(this.fileList.indexOf(file), 1)
      })
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleCopyUrl(file, event) {
      clipboard(file.url, event)
    }
  }
}
</script>
