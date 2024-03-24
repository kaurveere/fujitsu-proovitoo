create table weather(
    id int auto_increment,
    timestamp timestamp NOT NULL,
    name varchar(255) NOT NULL,
    wmocode int NOT NULL,
    temperature double NOT NULL,
    windspeed double NOT NULL,
    phenomenon varchar(255)
);
INSERT INTO weather (timestamp, name, wmocode, temperature, windspeed, phenomenon)
VALUES
    ('2024-03-24 08:00:00', 'tallinn', 12345, -1, 10.2, 'Thunderstorm'),
    ('2024-03-24 09:00:00', 'parnu', 12345, 26.1, 40, 'Moderate snowfall'),
    ('2024-03-24 10:00:00', 'tartu', 12345, -2.1, 4.7, 'Light snow shower');