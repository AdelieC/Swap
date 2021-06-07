CREATE TABLE CATEGORIES (
    category_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    label           VARCHAR(30) NOT NULL
);

CREATE TABLE ITEMS (
    item_id			INTEGER AUTO_INCREMENT PRIMARY KEY,
    item_name		VARCHAR(30) NOT NULL,
    description		VARCHAR(300) NOT NULL,
    initial_price	INTEGER,
    category_id		INTEGER NOT NULL,
    user_id			INTEGER NOT NULL,
    FOREIGN KEY(category_id) REFERENCES CATEGORIES(category_id),
    FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);

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

CREATE TABLE BIDS (
	bid_id			INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id			INTEGER NOT NULL,
    auction_id		INTEGER NOT NULL,
    bid_date    	DATETIME NOT NULL,
	bid_price		INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES USERS(user_id)
);

CREATE TABLE PICK_UP_POINTS (
	id				INTEGER AUTO_INCREMENT PRIMARY KEY,
	auction_id      INTEGER NOT NULL, 
    street          VARCHAR(30) NOT NULL,
    postcode        VARCHAR(15) NOT NULL,
    city            VARCHAR(30) NOT NULL,
    FOREIGN KEY(auction_id) REFERENCES AUCTIONS(auction_id)
);

INSERT INTO CATEGORIES VALUES 
(1, "Fashion"),
(2, "Home & Garden"),
(3, "Toys"),
(4, "Electronics"),
(5, "Motors"),
(6, "Pets"),
(7, "Collectables & Art"),
(8, "Health & Beauty"),
(9, "Media"),
(10, "Business & Office Supplies"),
(11, "Others")
;
SELECT * FROM CATEGORIES;
