CREATE TABLE IF NOT EXISTS register(
    id SERIAL,
    member_id INT,
    conferences_id INT,
    code VARCHAR(100),
    registed_at VARCHAR (100),
    assisted VARCHAR (100),
    PRIMARY KEY (id),
    FOREIGN KEY(member_id) REFERENCES member(id),
    FOREIGN KEY(conferences_id) REFERENCES conferences(id),
    UNIQUE(code)
    );

