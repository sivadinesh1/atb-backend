CREATE TABLE IF NOT EXISTS `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `r_title` varchar(2000) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `read_time` varchar(45) DEFAULT NULL,
  `body` varchar(2000) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `tags` varchar(2000) DEFAULT NULL,
  `cover_img` varchar(200) DEFAULT NULL,
  `article_img` varchar(200) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `article_bitly` varchar(100) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `published_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;