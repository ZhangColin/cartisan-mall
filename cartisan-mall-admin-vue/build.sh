#!/usr/bin/env bash
npm run build:staging

docker build -t hub.c.163.com/zhangcolin/cartisan-mall-admin .

docker push hub.c.163.com/zhangcolin/cartisan-mall-admin
