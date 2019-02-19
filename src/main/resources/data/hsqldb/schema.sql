DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `idarticle` bigint(20) NOT NULL AUTO_INCREMENT,
  `art_title_en` varchar(200) NOT NULL,
  `art_title_rl` varchar(1000) DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `published_timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`idarticle`),
  UNIQUE KEY `idarticle_UNIQUE` (`idarticle`),
  UNIQUE KEY `art_title_en_UNIQUE` (`art_title_en`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
