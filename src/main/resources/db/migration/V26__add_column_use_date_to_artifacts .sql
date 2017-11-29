ALTER TABLE `artifacts`
ADD `return_date` DATE DEFAULT null;

UPDATE `artifacts`
SET `return_date` = null;
