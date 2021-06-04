CREATE TABLE USERS (
    user_id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    first_name      VARCHAR(30) NOT NULL,
    email           VARCHAR(20) NOT NULL,
    telephone       VARCHAR(15),
    street          VARCHAR(30) NOT NULL,
    postcode        VARCHAR(10) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    password        VARCHAR(30) NOT NULL,
    credit          INTEGER NOT NULL,
    is_admin        BOOLEAN DEFAULT 0,
    UNIQUE(username, email)
);

DROP TABLE USERS;

INSERT INTO USERS values (
'1',
'adeliec',
'castel',
'adelie',
'adelie@gmail.com',
'0231936141',
'60 rue des rosiers',
'14000',
'CAEN',
'passbidon',
'0',
'0'
);
UPDATE USERS SET password = 'Pa$$w0rd' WHERE user_id = '1';