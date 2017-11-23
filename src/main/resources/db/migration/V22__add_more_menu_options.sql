UPDATE `main_menu`
SET `URI` = '/backlog', `mentor_access` = 0, `admin_access` = 0
WHERE `option` = 'Backlog';

INSERT INTO `main_menu`(option, admin_access, mentor_access, student_access, URI)
VALUES ('Experience Levels', 1, 0, 0, '/experience');