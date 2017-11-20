CREATE TABLE `session` (
    `sessionID` TEXT PRIMARY KEY,
    `userID` TEXT NOT NULL,
    `creationTime` TEXT NOT NULL,
    `lastActiveTime` TEXT NOT NULL,
    `expireTime` TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(id)
);