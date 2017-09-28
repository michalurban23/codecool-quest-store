ALTER TABLE `artifact_template`
ADD `active` BOOLEAN DEFAULT 1;

UPDATE `artifact_template`
SET `active` = 1;
