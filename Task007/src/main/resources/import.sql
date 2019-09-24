INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`)
VALUES ('1', 'ROLE_USER');
INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`)
VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `mydbteset`.`roles_tables_test` (`id`, `name_role`)
VALUES ('3', 'ROLE_SUPPORT');
INSERT INTO `mydbteset`.`users_table_test` (`id`, `name`, `login`, `password`)
VALUES ('1', 'admin', 'admin', '$2a$10$2fANNUMYolEIkXlJteQbq.i5ZDgfk4CbM2fFJpMEHxRZXwjiRIBSi');
INSERT INTO `mydbteset`.`user_role` (`user_id`, `role_id`)
VALUES ('1', '2');