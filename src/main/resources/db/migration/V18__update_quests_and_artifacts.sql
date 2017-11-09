UPDATE `quests` SET `owner` = 1 WHERE `id` = 9;
UPDATE `quests` SET `template_name` = "Solve the magic puzzle" WHERE `id` = 7;
UPDATE `quests` SET `owner` = 2 WHERE `id` = 8;
INSERT INTO `quests` (`template_name`, `owner`, `accept_date`, `return_date`)
VALUES ("Forge weapons", 1, "2017-10-10", '2017-10-15'), ("Tame a pet", 1, "2017-10-10", "2017-10-15"), ("Explore a dungeon", 6, "2017-10-05", "2017-10-10"), ("Solve the magic puzzle", 8, "2017-09-25", "2017-10-05"), ("Solve the magic puzzle", 8, "2017-09-28", "2017-10-11"), ("Slay a dragon", 4, "2017-09-12", "2017-09-20");
