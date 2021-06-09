CREATE TABLE CATEGORIES (
    category_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    label           VARCHAR(30) NOT NULL
);
DROP TABLE CATEGORIES;

CREATE TABLE AUCTIONS (
    auction_id		INTEGER AUTO_INCREMENT PRIMARY KEY,
    auction_name	VARCHAR(30) NOT NULL,
    description		TEXT(200),
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






