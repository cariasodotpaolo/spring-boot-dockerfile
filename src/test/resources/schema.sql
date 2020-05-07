--H2 config
--place sql scripts here to create tables, etc

CREATE SEQUENCE IF NOT EXISTS seq_my_table START WITH 1;

CREATE TABLE IF NOT EXISTS "MY_TABLE"
(
"ID" NUMBER(20,0) PRIMARY KEY,
"COLUMN_1" VARCHAR2(256),
CONSTRAINT pk_my_table PRIMARY KEY (ID)
) AS SELECT * FROM CSVREAD('classpath:h2/my_table.csv')