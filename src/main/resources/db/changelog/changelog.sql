-- liquibase formatted sql
-- changeset eliezer:1
CREATE TABLE IF NOT EXISTS books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(13) NOT NULL,
    ean VARCHAR(13),
    publisher VARCHAR(255),
    publisher_date DATE,
    pages INT,
    synopsis TEXT,
    CONSTRAINT pk_books PRIMARY KEY(id),
    CONSTRAINT uc_books_page_positive CHECK (pages > 0)
) ENGINE = InnoDB;

-- rollback DROP TABLE books;

-- changeset eliezer:2
CREATE TABLE IF NOT EXISTS copies(
    id BIGINT NOT NULL AUTO_INCREMENT,
    state ENUM('new', 'good', 'bad', 'destroyed') NOT NULL DEFAULT 'new',
    code INT NOT NULL,
    available BOOLEAN NOT NULL DEFAULT true,
    book_id BIGINT NOT NULL,
    CONSTRAINT pk_copies PRIMARY KEY(id),
    CONSTRAINT fk_copies_books FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE
) ENGINE = InnoDB;

-- rollback DROP TABLE copies;

-- changeset eliezer:3
CREATE TABLE IF NOT EXISTS authors(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name NOT NULL VARCHAR(255),
    bibliography TEXT,
    nationality VARCHAR(255),
    CONSTRAINT pk_authors PRIMARY KEY(id)
) ENGINE = InnoDB;

-- rollback DROP TABLE authors;