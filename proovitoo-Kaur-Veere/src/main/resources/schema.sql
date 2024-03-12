create table weather(
    id int auto_increment,
    timestamp int NOT NULL,
    name varchar(255) NOT NULL,
    wmocode int NOT NULL,
    temperature double NOT NULL,
    windspeed double NOT NULL,
    phenomenon varchar(255)
);
--INSERT INTO weather (timestamp, name, wmocode, temperature, windspeed, phenomenon)
--VALUES (2304, 'CityName', 123, 25.5, 10.2, 'Sunny');