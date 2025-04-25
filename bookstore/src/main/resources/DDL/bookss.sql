DROP TABLE IF EXISTS `bookss`;

CREATE TABLE `bookss` (
    `user_name` text DEFAULT NULL,
    `bookid` int NOT NULL AUTO_INCREMENT,
    `bookTitle` text DEFAULT NULL,
    `Authors` text DEFAULT NULL,
    `Category` text DEFAULT NULL,
    `Summary` text DEFAULT NULL,
    PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

