SELECT * FROM books;

SELECT * FROM books WHERE id = 1;

INSERT INTO books (code, title, author, year, amountPages, amountLinks, citationIndex)
VALUES ('B001', 'Чистый код', 'Роберт Мартин', 2008, 464, 120, 4.7);

UPDATE books SET code=?, title=?, author=?, year=?, amountPages=?, amountLinks=?, citationIndex=?
WHERE id=?;

DELETE FROM books WHERE id=?;

SELECT * FROM books WHERE code=?;

SELECT author FROM books
GROUP BY author
HAVING SUM(citationIndex) = (
    SELECT MAX(sumCitationIndex) FROM (
        SELECT SUM(citationIndex) AS sumCitationIndex FROM books
        GROUP BY author
    ) AS sums
);