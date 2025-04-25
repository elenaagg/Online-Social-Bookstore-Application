CREATE DATABASE  IF NOT EXISTS `secure_users_directory`;
USE `secure_users_directory`;

--
-- Table structure for table `usersprofile`
--

DROP TABLE IF EXISTS `usersprofile`;


CREATE TABLE `usersprofile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` text DEFAULT NULL,
  `full_name` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `age` text DEFAULT NULL,
  `phone_number` text DEFAULT NULL,
  `Category` text DEFAULT NULL,
  `Authors` text DEFAULT NULL,

  `role` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

