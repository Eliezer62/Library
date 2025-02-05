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
    name VARCHAR(255) NOT NULL ,
    bibliography TEXT,
    nationality VARCHAR(255),
    CONSTRAINT pk_authors PRIMARY KEY(id)
) ENGINE = InnoDB;

-- rollback DROP TABLE authors;

-- changeset eliezer:4
ALTER TABLE books ADD COLUMN author_id BIGINT;
-- rollback ALTER TABLE books DROP COLUMN author_id; 

-- changeset eliezer:5
ALTER TABLE books ADD CONSTRAINT fk_books_authors FOREIGN KEY(author_id) REFERENCES authors(id) ON DELETE SET NULL;
-- rollback ALTER TABLE books DROP CONSTRAINT fk_books_authors;

-- changeset eliezer:6
ALTER TABLE books ADD COLUMN name VARCHAR(255) NOT NULL;
-- rollback ALTER TABLE books DROP COLUMN name;