CREATE TABLE USERS (
    user_id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    first_name      VARCHAR(30) NOT NULL,
    email           VARCHAR(20) NOT NULL,
    telephone       VARCHAR(20),
    street          VARCHAR(30) NOT NULL,
    postcode        VARCHAR(10) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    password        VARCHAR(30) NOT NULL,
    credit          INTEGER NOT NULL,
    is_admin        BOOLEAN DEFAULT 0
);
DROP TABLE USERS;
