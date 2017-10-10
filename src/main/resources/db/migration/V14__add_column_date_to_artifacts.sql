ALTER TABLE `artifacts`
ADD `accept_date` DATE DEFAULT null;

UPDATE `artifacts`
SET `accept_date` = null;
