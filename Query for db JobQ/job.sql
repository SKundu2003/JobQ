use jobq;

CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `description` text,
  `location` varchar(255) DEFAULT NULL,
  `domain_id` bigint(20) DEFAULT NULL,
  `domain_name` varchar(255) DEFAULT NULL,
  `job_type` varchar(255) DEFAULT NULL,
  `salary_rage_to` varchar(255) DEFAULT NULL,
  `salary_rage_from` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `skill_ids` varchar(255) DEFAULT NULL,
  `skill_names` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_on` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_on` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `job` (`title`, `company_name`, `description`, `location`, `domain_id`, `domain_name`, `job_type`, `salary_rage_to`, `salary_rage_from`, `experience`, `skill_ids`, `skill_names`, `created_by`, `created_on`, `modified_on`, `deleted_on`, `is_deleted`) VALUES
('Software Engineer', 'ABC Company', 'Description of Software Engineer position', 'New York', 1, 'IT', 'Full Time', '100000', '80000', '5 years', '1,2,3', 'Java, SQL, HTML', 'John Doe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 0),
('HR Manager', 'XYZ Inc.', 'Description of HR Manager position', 'Los Angeles', 2, 'HR', 'Full Time', '90000', '70000', '8 years', '4,5,6', 'Recruiting, Employee Relations, Training', 'Jane Smith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 0);
