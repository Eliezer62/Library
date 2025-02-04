-- liquibase formatted sql
-- changeset eliezer:1
CREATE TABLE IF NOT EXISTS books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(13),
    ean VARCHAR(13),
    publisher VARCHAR(255),
    publisher_date DATE,
    pages INT,
    synopsis TEXT,
    CONSTRAINT pk_books PRIMARY KEY(id),
    CONSTRAINT uc_books_page_positive CHECK (pages > 0)
) ENGINE = InnoDB;

-- rollback DROP TABLE books;