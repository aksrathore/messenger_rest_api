CREATE TABLE COMMENTS
(
	`COMMENT_ID` INTEGER(255) UNSIGNED NOT NULL AUTO_INCREMENT,
    `MESSAGE_ID_FK` INT UNSIGNED NOT NULL,
	`AUTHOR` VARCHAR(20),
	`MESSAGE` VARCHAR(255),
	`CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`LAST_UPDATED` datetime NOT NULL,
PRIMARY KEY (`COMMENT_ID`),
FOREIGN KEY (`MESSAGE_ID_FK`) REFERENCES MESSAGES(`MESSAGE_ID`) ON DELETE CASCADE,
FOREIGN KEY (`AUTHOR`) REFERENCES PROFILES(`PROFILENAME`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A1","A1 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A2","A2 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A3","A3 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A4","A4 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A1","A1 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A5","A5 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(1, "A6","A6 COMMENTED ON THE MESSAGE ",NOW());

INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(2, "A2","A2 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(2, "A3","A3 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(2, "A4","A4 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(2, "A1","A1 COMMENTED ON THE MESSAGE ",NOW());
INSERT INTO comments(MESSAGE_ID_FK,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(2, "A1","A1 COMMENTED ON THE MESSAGE ",NOW());