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


DROP TABLE IF EXISTS `blog_entry`;

CREATE TABLE `blog_entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(10000) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_AUTHOR_idx` (`author_id`),
  
  CONSTRAINT `FK_AUTHOR` 
  FOREIGN KEY (`author_id`) 
  REFERENCES `author` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(5000) DEFAULT NULL,
  `blog_entry_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),

  KEY `FK_BLOG_ENTRY_idx` (`blog_entry_id`),
  
  CONSTRAINT `FK_BLOG_ENTRY` 
  FOREIGN KEY (`blog_entry_id`) 
  REFERENCES `blog_entry` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `follower`;

CREATE TABLE `follower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `blog_entry_follower`;

CREATE TABLE `blog_entry_follower` (
  `follower_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,

  PRIMARY KEY (`follower_id`, `author_id`),
  KEY `FK_AUTHOR_idx` (`author_id`),

  CONSTRAINT `FK_FOLLOWER_05` 
  FOREIGN KEY (`follower_id`) 
  REFERENCES `follower` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_AUTHOR_05` 
  FOREIGN KEY (`author_id`) 
  REFERENCES `author` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
