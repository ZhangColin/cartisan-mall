## 环境搭建

### Docker 

1. 拉取镜像
```shell
docker pull minio/minio
```

2. 运行 
```shell
docker run -d --name cartisan-minio -e "MINIO_ROOT_USER=zhang_colin" -e "MINIO_ROOT_PASSWORD=cartisan" -p 9000:9000 -p 9001:9001 minio/minio server /data --console-address ":9001"
```
https://mp.weixin.qq.com/s/5Xku63p7s6p2jkwyzZWweA

https://mp.weixin.qq.com/s/rzaqPmpTOUJJsKISr6eF9Q
