DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS line;

CREATE TABLE ticket
(
    id INTEGER NOT NULL IDENTITY PRIMARY KEY,
    checked BIT
);

CREATE TABLE line
(
    id INTEGER     NOT NULL IDENTITY PRIMARY KEY,
    line_numbers INTEGER(3) NOT NULL,
    result  INTEGER(2),
    ticket_id INTEGER,
    serial_number FLOAT NOT NULL
);
