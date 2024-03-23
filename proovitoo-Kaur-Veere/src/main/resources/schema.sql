create table weather(
    id int auto_increment,
    timestamp timestamp NOT NULL,
    name varchar(255) NOT NULL,
    wmocode int NOT NULL,
    temperature double NOT NULL,
    windspeed double NOT NULL,
    phenomenon varchar(255)
);