create database middleware default charset=utf8;

DROP TABLE IF EXISTS `current_palmset`;
CREATE TABLE `current_palmset` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `palmset_name` VARCHAR(36) NOT NULL COMMENT '掌脉集名称',
  `count` INTEGER(8) NOT NULL COMMENT '当前掌脉集已使用数量',
  `enable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用 0:不可用 1:可用',
  `create_by` bigint(18) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(18) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前空余掌脉集';


