-- 模板表
truncate `gds_templates`;

INSERT INTO gds_templates (id, name) VALUES (42, '手机');
INSERT INTO gds_templates (id, name) VALUES (43, '电视');
INSERT INTO gds_templates (id, name) VALUES (44, '服装');

-- 规格表
truncate `gds_specifications`;

INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (26, 44, '尺码', '165,170,175', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (27, 42, '颜色', '红色,蓝色,黑色,槟色,白色,金色,银色,灰色.紫色', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (28, 42, '版本', '8GB+128GB,6GB+64GB,6GB+128GB,4GB+64GB,4GB+32GB', 1);
INSERT INTO gds_specifications (id, template_id, name, options, sequence) VALUES (33, 43, '尺寸', '70寸,80寸,43寸,49寸,55寸,46寸', 1);

-- 参数表
truncate `gds_parameters`;

INSERT INTO gds_parameters (id, template_id, name, options, sequence) VALUES (1, 42, '出厂年份', '2001,2002,2004,2005', 1);
INSERT INTO gds_parameters (id, template_id, name, options, sequence) VALUES (2, 42, '版本', '10,20,30', 11);
