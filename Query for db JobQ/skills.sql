use jobq;
CREATE TABLE `skills` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Inserting dummy data
-- INSERT INTO `skills` (`skill_name`) VALUES
-- ('Java Programming'),
-- ('Recruitment');
