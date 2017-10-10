create table `class_details_menu` (`id` integer primary key unique, `option` text, `admin_access` boolean, `mentor_access` boolean);
insert into class_details_menu(id, option, admin_access, mentor_access) values (0, 'Back', 1, 1);
insert into class_details_menu(option, admin_access, mentor_access) values ('Edit name', 1, 0);
insert into class_details_menu(option, admin_access, mentor_access) values ('Choose student', 0, 1);
insert into class_details_menu(option, admin_access, mentor_access) values ('Add student', 0, 1);
insert into class_details_menu(option, admin_access, mentor_access) values ('Add mentor', 1, 0);
insert into class_details_menu(option, admin_access, mentor_access) values ('Remove class', 0, 1);