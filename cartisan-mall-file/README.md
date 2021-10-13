## 环境搭建

### Docker 

1. 拉取镜像
```shell
docker pull morunchang/fastdfs
```

2. 运行 tracker
```shell
docker run -d --name fastdfs-tracker --net=host morunchang/fastdfs sh tracker.sh

# mac
docker run -d --name fastdfs-tracker -p 22122:22122 morunchang/fastdfs sh tracker.sh

```

3. 运行 storage
```shell
docker run -d --name fastdfs-storage --net=host -e TRACKER_IP=192.168.31.101:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh

# mac
docker run -d --name fastdfs-storage -p 18888:8080 -p 23000:23000 -e TRACKER_IP=192.168.31.101:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh
```

+ 使用的网络模式是 --net=host, 
+ 192.168.31.101 是宿主机的IP
+ group1 是组名，即 storage 的组
+ 如果想要增加新的 storage 服务器，再次运行该命令，注意更换新组名

4. 配置Nginx
> Nginx 在这里主要提供对 FastDFS 图片访问的支持，Docker 容器中已经集成了 Nginx，我们需要修改 Nginx 的配置，进入 storage 的容器内部，修改 nginx.conf

```shell
docker exec -it fastdfs-storage /bin/bash
vi /etc/nginx/conf/nginx.conf
```

添加配置：
```shell
location ~ /M00 {
  root /data/fast_data/data;
  ngx_fastdfs_module;
}
```

禁止缓存：
```shell
add_header Cache-Control no-store;
```

退出，重启容器
```shell
exit
docker restart fastdfs-storage
```

5. 开启启动设置
```shell
docker update --restart=always fastdfs-tracker
docker update --restart=always fastdfs-storage
```
