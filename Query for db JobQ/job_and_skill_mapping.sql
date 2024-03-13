CREATE TABLE `job_and_skill_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) DEFAULT NULL,
  `skill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_job_id` (`job_id`),
  KEY `fk_skill_id` (`skill_id`),
  CONSTRAINT `fk_job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_skill_id` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Inserting dummy data
INSERT INTO `job_and_skill_mapping` (`job_id`, `skill_id`) VALUES
(1, 1), -- Assuming job_id 1 requires skill_id 1
(2, 2); -- Assuming job_id 2 requires skill_id 2
