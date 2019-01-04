DROP SCHEMA IF EXISTS `blog`;

CREATE SCHEMA `blog`;

use `blog`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `author_detail`;

CREATE TABLE `author_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bio` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `author_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`author_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`author_detail_id`) REFERENCES `author_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
