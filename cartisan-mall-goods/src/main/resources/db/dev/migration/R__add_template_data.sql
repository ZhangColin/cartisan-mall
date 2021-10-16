-- 模板表
truncate `gds_templates`;

INSERT INTO gds_templates (id, name) VALUES (42, '手机');
INSERT INTO gds_templates (id, name) VALUES (43, '电视');

-- 规格表
truncate `gds_specifications`;

INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (26, 43, '尺码', '165,170,175', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (27, 42, '网络', '联通2G,联通3G,联通4G,移动4G,联通3G/移动4G,电信4G', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (28, 42, '手机屏幕尺寸', '5寸,5.5寸', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (32, 42, '机身内存', '16G,32G,128G', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (33, 43, '电视屏幕尺寸', '20英寸,50英寸,60英寸', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (34, 42, '存储', '16G,32G,64G', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (36, 42, '像素', '300万像素,800万像素', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (37, 43, '电视音响效果', '立体声,环绕,小影院', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (38, 42, '颜色', '红,绿,蓝,紫,白,黑', 11);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (39, 42, '测试', '实施,学习,实施,测试,显示,s11', 1);

-- 参数表
truncate `gds_parameters`;

INSERT INTO gds_parameters (id, template_id, name, options, sequence) VALUES (1, 42, '出厂年份', '2001,2002,2004,2005', 1);
INSERT INTO gds_parameters (id, template_id, name, options, sequence) VALUES (2, 42, '版本', '10,20,30', 11);
