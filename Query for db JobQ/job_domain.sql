use jobq;

CREATE TABLE `job_domain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Inserting dummy data
INSERT INTO `job_domain` (`domain_name`) VALUES
('Information Technology'),
('Human Resources');
