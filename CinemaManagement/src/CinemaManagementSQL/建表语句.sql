/*
	@date 2022/8/29
	@description Create tables:User,Film,Cinema
*/

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `User`;
CREATE TABLE `Userr` (
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(30)  NOT NULL COMMENT '密码',
  `role` bool DEFAULT 0 COMMENT '身份',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Film`;
CREATE TABLE `Film` (
  `name` varchar(20) NOT NULL COMMENT '电影名称',
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT'电影编号',
  `releaseDate` date DEFAULT NULL COMMENT '上映日期',
  `offlineDate` date DEFAULT NULL COMMENT '下线日期',
  `beginTime` time DEFAULT NULL COMMENT '开始时间',
  `endTime` time DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Cinema`;
CREATE TABLE `Cinema` (
  `id` tinyint(5) NOT NULL AUTO_INCREMENT COMMENT '影厅编号',
  `totalRow` smallint(3) DEFAULT 50 COMMENT '座位行数',
  `totalColumn` smallint(3) DEFAULT 50 COMMENT '座位列数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
