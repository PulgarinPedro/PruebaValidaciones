CREATE TABLE IF NOT EXISTS event(
    id SERIAL,
    description VARCHAR(100),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_attendees INT,
    PRIMARY KEY (id)
    );

