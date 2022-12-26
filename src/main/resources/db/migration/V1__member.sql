CREATE TABLE IF NOT EXISTS member(
    id SERIAL,
    fullname VARCHAR(100),
    email VARCHAR(100),
    age INT,
    PRIMARY KEY (id)
    );