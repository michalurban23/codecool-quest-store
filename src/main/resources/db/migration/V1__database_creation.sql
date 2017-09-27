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
INSERT INTO `users` (id,first_name,last_name,email,address,status,class_name,exp) VALUES (1,'Agnieszka','Koszany','agi@codecool.com','Kraków','MENTOR','KRK-2017-1',NULL),
 (2,'Mateusz','Steliga','scooby@codecool.com','Kraków','MENTOR','KRK-2017-1',NULL),
 (3,'Mateusz','Ostafil','master@codecool.com','Kraków','MENTOR','KRK-2017-1',NULL),
 (4,'Michał','Urban','michałurban@gmail.com','Kraków','STUDENT','KRK-2017-1',NULL),
 (5,'Jerzy','Mardaus','boss@codecool.com','Kraków','ADMIN','KRK-2017-1',NULL),
 (6,'Mateusz','Romanowski','romek@gmail.com','Zadupie','STUDENT','KRK-2017-1',NULL),
 (7,'Kacper','Żelichowski','kacpur@gmail.com','Szydłowiec','STUDENT','KRK-2017-1',NULL),
 (8,'Błażej','Pierzak','blazpie@gmail.com','Podgórze','STUDENT','KRK-2017-1',NULL),
 (9,'Przemek','Ciąćka','przemo@codecool.com','Gdzieś','MENTOR','KRK-2016-1',NULL),
 (10,'Konrad','Gadzina','kondziu@codecool.com','Wszędzie','MENTOR','KRK-2017-2',NULL),
 (11,'Krzysiu','Szajowski','szajo.maupa@gmail.com','Prokocim?','STUDENT','KRK-2017-1',NULL),
 (12,'Mateusz','Korga','korges@gmail.com','Rzeszów','STUDENT','KRK-2017-1',NULL),
 (13,'Paweł','Kulpa','pabblo@buziaczek.kom','Krakoov','STUDENT','KRK-2017-1',NULL);
CREATE TABLE IF NOT EXISTS `user_groups` (
	`user_id`	INTEGER,
	`group_id`	INTEGER,
	PRIMARY KEY(`user_id`,`group_id`)
);
INSERT INTO `user_groups` (user_id,group_id) VALUES (4,1),
 (6,1),
 (7,1),
 (8,1),
 (11,2),
 (12,2),
 (13,2);
CREATE TABLE IF NOT EXISTS `quests` (
	`id`	INTEGER NOT NULL,
	`template_name`	TEXT DEFAULT NULL,
	`owner`	INTEGER DEFAULT NULL,
	`accept_date`	DATE DEFAULT NULL,
	`return_date`	DATE DEFAULT NULL,
	PRIMARY KEY(`id`)
);
INSERT INTO `quests` (id,template_name,owner,accept_date,return_date) VALUES (1,'Explore a dungeon',4,'2017-09-08','2017-09-08'),
 (2,'Solve the magic puzzle',4,'2017-09-20',NULL),
 (3,'Solve the magic puzzle',6,'2017-09-24',NULL),
 (4,'Solve the magic puzzle',12,'2017-09-25',NULL),
 (5,'Slay a dragon',7,'2017-09-10','2017-09-20'),
 (6,'Slay a dragon',11,'2017-09-10',NULL),
 (7,'Spot trap',8,'2017-09-19','2017-09-20'),
 (8,'Tame a pet',13,'2017-09-25','2017-09-26'),
 (9,'Master the mornings',7,'2017-08-10','2017-09-24');
CREATE TABLE IF NOT EXISTS `quest_template` (
	`name`	TEXT NOT NULL,
	`description`	TEXT,
	`value`	INTEGER DEFAULT NULL,
	`special`	BOOLEAN DEFAULT NULL,
	PRIMARY KEY(`name`)
);
INSERT INTO `quest_template` (name,description,value,special) VALUES ('Explore a dungeon','Finishing a Teamwork week',100,0),
 ('Solve the magic puzzle','Finishing SI assignment',100,0),
 ('Slay a dragon','Passing a Checkpoint',500,0),
 ('Spot trap','Spotting a major mistake in assignment',50,1),
 ('Tame a pet','Doing a demo about pet project',100,1),
 ('Forge weapons','Organize a workshop for other students',400,1),
 ('Master the mornings','Attending one month without being late',300,1);
CREATE TABLE IF NOT EXISTS `login_info` (
	`id`	INTEGER NOT NULL,
	`password`	varchar ( 255 ) DEFAULT NULL,
	`login`	varchar ( 255 ) DEFAULT NULL,
	`salt`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `login_info` (id,password,login,salt) VALUES (1,'admin','admin',NULL),
 (2,'pass','student',NULL),
 (3,'pass','mentor',NULL);
CREATE TABLE IF NOT EXISTS `groups` (
	`id`	INTEGER NOT NULL,
	`name`	varchar ( 255 ),
	PRIMARY KEY(`id`)
);
INSERT INTO `groups` (id,name) VALUES (1,'rmbk'),
 (2,'B.Analni bandyci');
CREATE TABLE IF NOT EXISTS `experience` (
	`level`	TEXT NOT NULL,
	`value`	INTEGER DEFAULT NULL
);
INSERT INTO `experience` (level,value) VALUES ('Newbie',100),
 ('Novice',200),
 ('Rookie',400),
 ('Beginner',600),
 ('Talented',900),
 ('Skilled',1200),
 ('Intermediate',1500),
 ('Skilfull',1900),
 ('Seasoned',2300),
 ('Proficient',2700),
 ('Experienced',3200),
 ('Advanced',3800),
 ('Senior',4400),
 ('Expert',5000),
 ('God',NULL);
CREATE TABLE IF NOT EXISTS `class_name` (
	`id`	INTEGER NOT NULL,
	`name`	varchar ( 255 ),
	PRIMARY KEY(`id`)
);
INSERT INTO `class_name` (id,name) VALUES (1,'KRK-2016-1'),
 (2,'KRK-2017-2'),
 (3,'KRK-2016-1');
CREATE TABLE IF NOT EXISTS `backlog` (
	`id`	INTEGER NOT NULL,
	`action_date`	DATE DEFAULT NULL,
	`description`	TEXT DEFAULT NULL,
	`status`	TEXT DEFAULT NULL,
	`value`	INTEGER DEFAULT NULL,
	`owner`	INTEGER DEFAULT NULL,
	PRIMARY KEY(`id`)
);
INSERT INTO `backlog` (id,action_date,description,status,value,owner) VALUES (1,'2017-09-10','Combat training','bought',50,6),
 (2,'2017-09-12','Sanctuary','bought',300,7),
 (3,'2017-09-02','Combat training','used',50,4),
 (4,'2017-09-15','Sanctuary','bought',300,8),
 (5,'2017-08-28','Time travel','bought',500,4),
 (6,'2017-08-31','Summon code elemental','bough',1000,1),
 (7,'2017-09-10','Time of knowledge','used',500,1);
CREATE TABLE IF NOT EXISTS `artifacts` (
	`id`	INTEGER,
	`template_name`	varchar ( 255 ),
	`owner`	INTEGER DEFAULT NULL,
	`completion`	INTEGER DEFAULT NULL,
	PRIMARY KEY(`id`)
);
INSERT INTO `artifacts` (id,template_name,owner,completion) VALUES (1,'Combat training',6,'NULL'),
 (2,'Sanctuary',7,'NULL'),
 (3,'Sanctuary',8,'NULL'),
 (4,'Time travel',4,'NULL'),
 (5,'Summon code elemental',1,800);
CREATE TABLE IF NOT EXISTS `artifact_template` (
	`name`	varchar ( 255 ),
	`description`	text,
	`value`	INTEGER DEFAULT NULL,
	`special`	BOOLEAN DEFAULT NULL,
	PRIMARY KEY(`description`)
);
INSERT INTO `artifact_template` (name,description,value,special) VALUES ('Combat training','Private mentoring',50,0),
 ('Sanctuary','Spend a day in home office',300,0),
 ('Time travel','Extend SI week assignment deadline by one day',500,0),
 ('Tome of knowledge','Extra material of current topic',500,1),
 ('Summon code elemental','Mentor joins students team for one hour',1000,1);
