ALTER TABLE main_menu
ADD URI text;

update main_menu set URI = '/artifacts' where option = 'Artifacts';
update main_menu set URI = '/class' where option = 'Classes';
update main_menu set URI = '/group' where option = 'Teams';
update main_menu set URI = '/students' where option = 'Students';
update main_menu set URI = '/mentors' where option = 'Mentors';
update main_menu set URI = '/quests' where option = 'Quests';