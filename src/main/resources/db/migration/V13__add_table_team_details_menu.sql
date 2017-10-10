create table 'team_details_menu' ('id' integer primary key unique, 'option' text, 'show_only' boolean default 0);
insert into team_details_menu select * from details_menu;
update team_details_menu set option = 'Choose student' where id = 2;
update team_details_menu set option = 'Add student' where id = 3;
insert into team_details_menu (option, show_only) values('Remove team', 0);
delete from details_menu where id = 3;