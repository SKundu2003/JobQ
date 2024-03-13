CREATE TABLE `job_and_domain_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) DEFAULT NULL,
  `domain_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_job_id` (`job_id`),
  KEY `fk_domain_id` (`domain_id`),
  CONSTRAINT `fk_domain_id` FOREIGN KEY (`domain_id`) REFERENCES `job_domain` (`id`),
  CONSTRAINT `fk_job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Inserting dummy data
INSERT INTO `job_and_domain_mapping` (`job_id`, `domain_id`) VALUES
(1, 1), -- Assuming job_id 1 is related to domain_id 1
(2, 2); -- Assuming job_id 2 is related to domain_id 2
