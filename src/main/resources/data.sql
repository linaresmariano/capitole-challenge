
-- Valores por defecto para las columnas de auditoria
ALTER TABLE price ALTER COLUMN version_number SET DEFAULT 0;
ALTER TABLE price ALTER COLUMN creation_timestamp SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE price ALTER COLUMN modification_timestamp SET DEFAULT CURRENT_TIMESTAMP;


INSERT INTO price (brand_id, start_date, end_date, product_id, priority, price_value, curr)
VALUES (1, {ts '2020-06-14 00:00:00'}, {ts '2020-12-31 23:59:59'}, 35455,0,35.50,'EUR');

INSERT INTO price (brand_id, start_date, end_date, product_id, priority, price_value, curr)
VALUES (1, {ts '2020-06-14 15:00:00'}, {ts '2020-06-14 18:30:00'}, 35455,1,25.45,'EUR');

INSERT INTO price (brand_id, start_date, end_date, product_id, priority, price_value, curr)
VALUES (1, {ts '2020-06-15 00:00:00'}, {ts '2020-06-15 11:00:00'}, 35455,1,30.50,'EUR');

INSERT INTO price (brand_id, start_date, end_date, product_id, priority, price_value, curr)
VALUES (1, {ts '2020-06-15 16:00:00'}, {ts '2020-12-31 23:59:59'}, 35455,1,38.95,'EUR');


-- 10 dias atras, y 5 dias adelante
INSERT INTO price (brand_id, start_date, end_date, product_id, priority, price_value, curr)
VALUES (1,
	CAST(CURRENT_DATE AS TIMESTAMP) - INTERVAL '10' DAY,
	CAST(CURRENT_DATE AS TIMESTAMP) + INTERVAL '6' DAY - INTERVAL '1' SECOND,
 	--CURRENT_TIMESTAMP + INTERVAL '30' MINUTE,
	35455, 2, 35.50, 'EUR');





/*
INSERT INTO price  
SELECT 'brand_id',
	parsedatetime('start_date', 'yyyy-MM-dd-HH:mm:ss'),
	parsedatetime('end_date', 'yyyy-MM-dd-HH:mm:ss'),
	'product_id', 'priority', 'price_value', 'curr'
FROM CSVREAD('classpath:/price.csv');
*/
