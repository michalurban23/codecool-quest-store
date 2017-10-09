BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `member_menu` (
	`id`	INTEGER UNIQUE,
	`option`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `member_menu` VALUES (0,'Back');
INSERT INTO `member_menu` VALUES (1,'Remove student from Team');
COMMIT;
