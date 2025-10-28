USE `nasa-db`;

--
-- Table structure for table `nasa_members`
--

CREATE TABLE `nasa_members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `nasa_members`
--
-- NOTE: The passwords are encrypted using BCrypt

INSERT INTO `nasa_members`
VALUES
('guest','{bcrypt}$2a$10$4cnbKbq2zROx3zBZ9xMh6uRNHuSvDpWVmwz17PmvogpWxb1uUh18m',1),
('admin','{bcrypt}$2a$10$uAu7BPIIZpXC.H1hJp453.Bsr/iJ7pYOP3GkXbA7m04Gc69nMc.n6',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `nasa_roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `nasa_members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `nasa_roles`
--

INSERT INTO `nasa_roles`
VALUES
('guest','ROLE_EMPLOYEE'),
('guest','ROLE_MANAGER'),
('admin','ROLE_EMPLOYEE'),
('admin','ROLE_MANAGER'),
('admin','ROLE_ADMIN');
