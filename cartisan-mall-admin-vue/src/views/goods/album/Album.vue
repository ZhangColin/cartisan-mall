<template>
  <div class="app-container">
    <el-form :inline="true" @keyup.enter.native="handleSearch">
      <el-form-item>
        <el-input v-model="queryParam.title" placeholder="请输入相册标题查询" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-col v-for="album in dataSource" :key="album.id" :span="6" :style="{marginBottom:'20px'}">
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
              <el-button type="text" class="button" @click="handleDelete(album)">删除</el-button>
              <el-button type="text" class="button" style="margin-right: 5px" @click="handleEdit(album)">编辑</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <album-form ref="albumForm" @addSuccess="fetchData" @editSuccess="fetchData" />
  </div>
</template>

<script>
import { ListMixin } from '@/mixins/list-mixin'
import { DeleteMixin } from '@/mixins/delete-mixin'

import AlbumForm from '@/views/goods/album/AlbumForm'

export default {
  name: 'Album',
  components: { AlbumForm },
  mixins: [ListMixin, DeleteMixin],
  data() {
    return {
      apiBaseUrl: '/goods/albums',
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
    handleAdd() {
      this.$refs.albumForm.add()
    },
    handleEdit(row) {
      this.$refs.albumForm.edit(row)
    }
  }
}
</script>
