ALTER TABLE `quest_template`
ADD `active` BOOLEAN DEFAULT 1;

UPDATE `quest_template`
SET `active` = 1;
