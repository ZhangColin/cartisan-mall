-- 相册表
TRUNCATE `gds_albums`;

INSERT INTO `gds_albums`(`id`,`title`,`image`,`image_items`) VALUES (1, '华为mete20', 'http://localhost:9101/img/2.jpg', '[{\"url\":\"http://localhost:9101/img/1.jpg\",\"uid\":1548143143154,\"status\":\"success\"},{\"url\":\"http://localhost:9101/img/7.jpg\",\"uid\":1548143143155,\"status\":\"success\"}]');
INSERT INTO `gds_albums`(`id`,`title`,`image`,`image_items`) VALUES (3, '深圳一日游', 'http://www.itheima.com/1.jpg', '[{\"url\":\"http://localhost:9101/img/1.jpg\",\"uid\":1548143143154,\"status\":\"success\"},{\"url\":\"http://localhost:9101/img/7.jpg\",\"uid\":1548143143155,\"status\":\"success\"}]');
INSERT INTO `gds_albums`(`id`,`title`,`image`,`image_items`) VALUES (5, '哈哈哈', '哈哈哈', 'http://1.jpg');
INSERT INTO `gds_albums`(`id`,`title`,`image`,`image_items`) VALUES (7, '白兔测试图片', 'http://www.baidu.com/1.jpg', 'http://www.baidu.com');
