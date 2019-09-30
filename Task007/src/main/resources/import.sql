INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`) VALUES ('1', 'ROLE_USER');
INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`) VALUES ('3', 'ROLE_SUPPORT');
INSERT INTO `mydbteset`.`users_table_test` (`id`, `email`, `login`, `password`) VALUES ('1', 'admin@mail.com', 'admin', 'admin');
INSERT INTO `mydbteset`.`user_role` (`user_id`, `role_id`) VALUES ('1', '2');