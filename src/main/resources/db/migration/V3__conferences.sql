
CREATE TABLE IF NOT EXISTS conferences(
    id SERIAL,
    title VARCHAR(100),
    speaker VARCHAR(100),
    hour TIME,
    day DATE,
    event_id INT,
    total_attendees INT,
    PRIMARY KEY (id),
    UNIQUE (title),
    FOREIGN KEY(event_id) REFERENCES event(id)
    );
