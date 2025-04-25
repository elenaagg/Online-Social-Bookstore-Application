DROP TABLE IF EXISTS `requests`;



CREATE TABLE `requests` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name` text DEFAULT NULL,
    `bookTitle` text DEFAULT NULL,
    `owner` text DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
