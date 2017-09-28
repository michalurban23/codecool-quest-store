UPDATE login_info
SET salt = 'jklpw6tfhbxx4ew2'
WHERE salt IS NULL;

UPDATE login_info
SET password = '5794fbd1c17a67957cb41937be2efa38a3451eecf8cea674c6c80534abf1d38e';
