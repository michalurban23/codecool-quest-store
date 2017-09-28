BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `quests` (
	`id`	INTEGER NOT NULL,
	`template_name`	TEXT DEFAULT NULL,
	`owner`	INTEGER DEFAULT NULL,
	`accept_date`	DATE DEFAULT NULL,
	`return_date`	DATE DEFAULT NULL,
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `quest_template` (
	`name`	TEXT NOT NULL,
	`description`	TEXT,
	`value`	INTEGER DEFAULT NULL,
	`special`	BOOLEAN DEFAULT NULL,
	PRIMARY KEY(`name`)
);
CREATE TABLE IF NOT EXISTS `groups` (
	`id`	INTEGER NOT NULL,
	`name`	varchar ( 255 ),
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `user_groups` (
	`user_id`	INTEGER,
	`group_id`	INTEGER,
	PRIMARY KEY(`user_id`,`group_id`)
);
CREATE TABLE IF NOT EXISTS `experience` (
	`level`	TEXT NOT NULL,
	`value`	INTEGER DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS `backlog` (
	`id`	INTEGER NOT NULL,
	`action_date`	DATE DEFAULT NULL,
	`description`	TEXT DEFAULT NULL,
	`status`	TEXT DEFAULT NULL,
	`value`	INTEGER DEFAULT NULL,
	`owner`	INTEGER DEFAULT NULL,
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `artifacts` (
	`id`	INTEGER,
	`template_name`	varchar ( 255 ),
	`owner`	INTEGER DEFAULT NULL,
	`completion`	INTEGER DEFAULT NULL,
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `artifact_template` (
	`name`	varchar ( 255 ),
	`description`	text,
	`value`	INTEGER DEFAULT NULL,
	`special`	BOOLEAN DEFAULT NULL,
	PRIMARY KEY(`description`)
);
CREATE TABLE IF NOT EXISTS `class_name` (
	`id`	INTEGER NOT NULL,
	`name`	varchar ( 255 ),
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `users` (
	`id`	INTEGER NOT NULL,
	`first_name`	varchar ( 255 ) DEFAULT NULL,
	`last_name`	varchar ( 255 ) DEFAULT NULL,
	`email`	varchar ( 255 ) DEFAULT NULL,
	`address`	varchar ( 255 ) DEFAULT NULL,
	`status`	varchar ( 255 ) DEFAULT NULL,
	`class_name`	varchar ( 255 ),
	`exp`	INTEGER DEFAULT NULL,
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `login_info` (
	`id`	INTEGER NOT NULL,
	`password`	varchar ( 255 ) DEFAULT NULL,
	`login`	varchar ( 255 ) DEFAULT NULL,
	`salt`	TEXT,
	PRIMARY KEY(`id`)
);
COMMIT;
