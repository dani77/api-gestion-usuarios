DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
id NUMERIC NOT NULL PRIMARY KEY,
name VARCHAR (150) NOT NULL,
email VARCHAR (150),
password VARCHAR (50) NOT NULL,
phones VARCHAR (100),
created DATE,
modified DATE,
lastlogin VARCHAR(100),
token VARCHAR(100),
isactive VARCHAR(100) ,
role VARCHAR(50) 
);