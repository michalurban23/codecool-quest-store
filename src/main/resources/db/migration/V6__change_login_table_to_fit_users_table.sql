delete from login_info;
insert into login_info (id, login, password) select id, last_name, 'pass' from users;