BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `main_menu` (
	`id`	INTEGER UNIQUE,
	`option`	TEXT,
	`admin_access`	INTEGER,
	`mentor_access`	INTEGER,
	`student_access`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `main_menu` VALUES (0,'Log out',1,1,1);
INSERT INTO `main_menu` VALUES (1,'Edit account data',1,1,1);
INSERT INTO `main_menu` VALUES (2,'Classes',1,1,0);
INSERT INTO `main_menu` VALUES (3,'Students',0,1,0);
INSERT INTO `main_menu` VALUES (4,'Mentors',1,0,0);
INSERT INTO `main_menu` VALUES (5,'Teams',0,1,1);
INSERT INTO `main_menu` VALUES (6,'Quests',0,1,1);
INSERT INTO `main_menu` VALUES (7,'Artifacts',0,1,1);
CREATE TABLE IF NOT EXISTS `details_menu` (
	`id`	INTEGER UNIQUE,
	`option`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `details_menu` VALUES (0,'');
INSERT INTO `details_menu` VALUES (1,'Edit');
INSERT INTO `details_menu` VALUES (2,'Remove');
CREATE TABLE IF NOT EXISTS `browse_menu` (
	`id`	INTEGER UNIQUE,
	`option`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `browse_menu` VALUES (0,'Back');
INSERT INTO `browse_menu` VALUES (1,'Choose from list');
INSERT INTO `browse_menu` VALUES (2,'Add new');
COMMIT;
