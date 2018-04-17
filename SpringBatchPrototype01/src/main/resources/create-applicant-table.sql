DROP TABLE applicant;

CREATE TABLE `applicant` (
  `name` varchar(30) NOT NULL,
  `score` double DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `street` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;