alter table browse_menu add show_only integer default 0;
update browse_menu set show_only = 1 where id = 1;
update browse_menu set show_only = 1 where id = 0;

	

