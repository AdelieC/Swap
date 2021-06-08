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
DROP TABLE ITEMS;

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

INSERT INTO AUCTIONS VALUES 
(1, "Robe longue", "Jolie robe d'été rouge 1-2-3", '2021-05-20', '2021-05-30', 15, 25, 2, 1, "OVER"),
(2, "Robe courte", "Jolie robe d'été bleue NAF-NAF", '2021-05-10', '2021-05-21', 9, 13, 2, 1, "OVER"),
(3, "Painting by Marcel L'enfant", "Oil on canvas depicting a sailboat at sea", '2021-06-20', '2021-06-30', 150, 0, 3, 7, "CREATED"),
(4, "Painting by Michel Piel", "Oil on canvas depicting horses silhouettes on a red background", '2020-06-20', '2020-07-30', 700, 1500, 3, 7, "PICKED_UP"),
(5, "Black trousers", "Size S women, never worn, as good as new", '2019-06-20', '2019-07-30', 15, 20, 5, 1, "PICKED_UP"),
(6, "Computer screen", "32' Samsung", '2021-06-03', '2020-06-15', 30, 32, 4, 4, "ONGOING")
;
SELECT * FROM AUCTIONS;

INSERT INTO BIDS VALUES 
(1, 3, 2, "2021-05-10 10:34:09", 13),
(2, 3, 1, "2021-05-20 10:53:09", 25),
(3, 3, 4, "2020-06-23 11:34:09", 1500)
;
SELECT * FROM BIDS;








