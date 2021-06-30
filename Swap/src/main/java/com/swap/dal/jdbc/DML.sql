INSERT INTO USERS values (
'1',
'deliec',
'cast',
'chloe',
'chloe@gmail.com',
'0231936141',
'60 rue des palmiers',
'14112',
'MONDEVILLE',
'Pa$$w0rd',
'0',
'0'
);

INSERT INTO CATEGORIES VALUES 
(1, "Fashion"),
(2, "Home & Garden"),
(3, "Sports, Hobbies & Leisure"),
(4, "Electronics"),
(5, "Motors"),
(6, "Pets"),
(7, "Collectables & Art"),
(8, "Health & Beauty"),
(9, "Media"),
(10, "Business & Office Supplies"),
(11, "Others")
;

INSERT INTO AUCTIONS VALUES 
(1, "Robe longue", "Jolie robe d'été rouge 1-2-3", '2021-05-20', '2021-05-30', 15, 25, 2, 1, "OVER"),
(2, "Robe courte", "Jolie robe d'été bleue NAF-NAF", '2021-05-10', '2021-05-21', 9, 13, 2, 1, "OVER"),
(3, "Painting by Marcel L'enfant", "Oil on canvas depicting a sailboat at sea", '2021-06-20', '2021-06-30', 150, 0, 3, 7, "CREATED"),
(4, "Painting by Michel Piel", "Oil on canvas depicting horses silhouettes on a red background", '2020-06-20', '2020-07-30', 700, 1500, 3, 7, "OVER"),
(5, "Black trousers", "Size S women, never worn, as good as new", '2019-06-20', '2019-07-30', 15, 20, 5, 1, "OVER"),
(6, "Computer screen", "32' Samsung", '2021-06-03', '2020-06-15', 30, 32, 4, 4, "ONGOING")
;

INSERT INTO BIDS VALUES 
(1, 3, 2, "2021-05-10 10:34:09", 13),
(2, 3, 1, "2021-05-20 10:53:09", 25),
(3, 3, 4, "2020-06-23 11:34:09", 1500)
;

SELECT * FROM USERS;
SELECT * FROM CATEGORIES;
SELECT * FROM AUCTIONS;
SELECT * FROM BIDS;
SELECT * FROM PICTURES;
SELECT * FROM NOTIFICATIONS;


DELETE FROM PICTURES;
DELETE FROM PICK_UP_POINTS;
DELETE FROM BIDS;
DELETE FROM AUCTIONS;
DELETE FROM CATEGORIES;
DELETE FROM USERS;

UPDATE USERS set balance = balance + 200 WHERE user_id = 4;
UPDATE USERS set is_admin = 1 WHERE user_id = 1;

