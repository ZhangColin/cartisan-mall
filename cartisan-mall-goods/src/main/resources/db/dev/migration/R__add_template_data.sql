-- 模板表
truncate `gds_templates`;

INSERT INTO `gds_templates`(`id`,`name`) VALUES (42, '手机');
INSERT INTO `gds_templates`(`id`,`name`) VALUES (43, '电视');

-- 规格表
truncate `gds_specifications`;

INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (26, '尺码', '165,170,175', 43);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (27, '网络', '联通2G,联通3G,联通4G,移动4G,联通3G/移动4G,电信4G', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (28, '手机屏幕尺寸', '5寸,5.5寸', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (32, '机身内存', '16G,32G,128G', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (33, '电视屏幕尺寸', '20英寸,50英寸,60英寸', 43);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (34, '存储', '16G,32G,64G', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (36, '像素', '300万像素,800万像素', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (37, '电视音响效果', '立体声,环绕,小影院', 43);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (38, '颜色', '红,绿,蓝,紫,白,黑', 42);
INSERT INTO `gds_specifications`(`id`,`name`,`options`,`template_id`) VALUES (39, '测试', '实施,学习,实施,测试,显示,s11', 42);

-- 参数表
truncate `gds_parameters`;

INSERT INTO `gds_parameters`(`id`,`name`,`options`,`template_id`) VALUES (1, '出厂年份', '2001,2002,2004,2005', 42);
INSERT INTO `gds_parameters`(`id`,`name`,`options`,`template_id`) VALUES (2, '版本', '10,20,30', 42);
