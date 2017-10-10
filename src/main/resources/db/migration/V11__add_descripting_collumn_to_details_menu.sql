alter table details_menu add show_only integer default 0;

update details_menu set option = 'Back', show_only = 1 where id = 0;