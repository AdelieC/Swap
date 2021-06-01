CREATE TABLE USERS (
    user_id         INTEGER AUTO_INCREMENT CONSTRAINT PK_users PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    fist_name       VARCHAR(30) NOT NULL,
    email           VARCHAR(20) NOT NULL,
    telephone       VARCHAR(15),
    street_nb		VARCHAR(5) NOT NULL,
    street          VARCHAR(30) NOT NULL,
    postcode        VARCHAR(10) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    password        VARCHAR(30) NOT NULL,
    credit          INTEGER NOT NULL,
    is_admin        BOOLEAN NOT NULL DEFAULT 0
)