CREATE TABLE CATEGORIES (
    category_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    label           VARCHAR(30) NOT NULL
);
DROP TABLE CATEGORIES;

CREATE TABLE USERS (
    user_id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    first_name      VARCHAR(30) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    telephone       VARCHAR(15),
    street          VARCHAR(100) NOT NULL,
    postcode        VARCHAR(10) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    password        TEXT NOT NULL,
    salt			TEXT NOT NULL,
    balance         INTEGER NOT NULL,
    is_admin        BOOLEAN DEFAULT 0,
    UNIQUE(username, email)
);
DROP TABLE USERS;

CREATE TABLE AUCTIONS (
    auction_id		INTEGER AUTO_INCREMENT PRIMARY KEY,
    auction_name	VARCHAR(30) NOT NULL,
    description		TEXT(300),
    start_date		DATE NOT NULL,
    end_date		DATE NOT NULL,
    initial_price	INTEGER,
    sale_price		INTEGER,
    user_id			INTEGER NOT NULL,
    category_id		INTEGER NOT NULL,
    status			VARCHAR(30) NOT NULL,
    FOREIGN KEY(user_id) REFERENCES USERS(user_id),
    FOREIGN KEY(category_id) REFERENCES CATEGORIES(category_id)
);
DROP TABLE AUCTIONS;

CREATE TABLE BIDS (
	bid_id			INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id			INTEGER NOT NULL,
    auction_id		INTEGER NOT NULL,
    bid_date    	DATETIME NOT NULL,
	bid_price		INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);
DROP TABLE BIDS;

CREATE TABLE PICK_UP_POINTS (
	id				INTEGER AUTO_INCREMENT PRIMARY KEY,
	auction_id      INTEGER NOT NULL, 
    street          VARCHAR(30) NOT NULL,
    postcode        VARCHAR(15) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    FOREIGN KEY(auction_id) REFERENCES AUCTIONS(auction_id)
);
DROP TABLE PICK_UP_POINTS;

CREATE TABLE PICTURES (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	auction_id INTEGER NOT NULL,
	name VARCHAR(30) NOT NULL,
	extension VARCHAR(4) NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
	FOREIGN KEY(auction_id) REFERENCES AUCTIONS(auction_id),
	UNIQUE(name)
);
DROP TABLE PICTURES;

CREATE TABLE NOTIFICATIONS (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	recipient_id INTEGER NOT NULL,
	sender_id INTEGER NOT NULL,
	type VARCHAR(30) NOT NULL,
	content TEXT NOT NULL,
	is_read BOOLEAN DEFAULT 0,
	timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(recipient_id) REFERENCES USERS(user_id),
	FOREIGN KEY(sender_id) REFERENCES USERS(user_id)
);
DROP TABLE NOTIFICATIONS;




