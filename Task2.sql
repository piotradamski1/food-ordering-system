-- 1
SELECT c.Name
FROM Country c
         JOIN City ci ON c.CountryID = ci.CountryID
GROUP BY c.CountryID
HAVING SUM(ci.Population) > 400;

-- 2
SELECT c.Name
FROM Country c
WHERE c.CountryID NOT IN (
    SELECT ci.CountryID
    FROM City ci
             JOIN Building b ON ci.CityID = b.CityID
);