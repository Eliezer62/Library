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

-- changeset eliezer:7
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    cpf CHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role ENUM('admin', 'library', 'user') DEFAULT 'user',
    card_id CHAR(13) NOT NULL,
    status ENUM('active', 'disabled', 'locked', 'expired') NOT NULL DEFAULT 'active',
    registrationDate DATE,
    keycloak_id CHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY(id),
    CONSTRAINT uc_users_cpf UNIQUE(cpf),
    CONSTRAINT uc_users_email UNIQUE(email),
    CONSTRAINT uc_users_card_id UNIQUE(card_id),
    CONSTRAINT uc_users_keycloak_id UNIQUE(keycloak_id)
) ENGINE = InnoDB;
-- rollback DROP TABLE users;

-- changeset elizer:8
ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL;
-- rollback ALTER TABLE users DROP COLUMN password;

-- changeset eliezer:9
ALTER TABLE users ADD COLUMN registration_date DATE;
ALTER TABLE users DROP COLUMN registrationDate;
-- rollback ALTER TABLE users DROP COLUMN registration_date;

-- changeset eliezer:10
CREATE TABLE address (
    id BIGINT NOT NULL AUTO_INCREMENT,
    street VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip CHAR(8) NOT NULL,
    apartament CHAR(8) NOT NULL,
    country VARCHAR(255) NOT NULL DEFAULT 'Brasil',
    identification VARCHAR(255),
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY(id),
    CONSTRAINT fk_address_user FOREIGN KEY(user_id) REFERENCES users(id)
) ENGINE=InnoDB;
-- rollback DROP TABLE address;

-- changeset eliezer:11
CREATE TABLE phones (
    id BIGINT NOT NULL AUTO_INCREMENT,
    identification VARCHAR(255),
    ddi CHAR(4) NOT NULL DEFAULT '+55',
    ddd CHAR(3) NOT NULL,
    number VARCHAR(11) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_phones PRIMARY KEY(id),
    CONSTRAINT fk_phone_user FOREIGN KEY(user_id) REFERENCES users(id)
);
-- rollback DROP TABLE phones;

-- changeset eliezer:12
CREATE TABLE loans (
    id BIGINT NOT NULL AUTO_INCREMENT,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status ENUM('active', 'returned', 'overdue') NOT NULL DEFAULT 'active',
    renew BOOLEAN DEFAULT false,
    notes TEXT,
    user_id BIGINT NOT NULL,
    copy_id BIGINT NOT NULL,
    CONSTRAINT pk_loan PRIMARY KEY(id),
    CONSTRAINT fk_loan_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_loan_copy FOREIGN KEY(copy_id) REFERENCES copies(id)
) ENGINE=InnoDB;
-- rollback DROP TABLE loans;

-- changeset eliezer:13
CREATE TABLE overduefees (
    id BIGINT NOT NULL AUTO_INCREMENT,
    amount DECIMAL(15,2) NOT NULL,
    reason VARCHAR(255) NOT NULL,
    status ENUM('pending', 'paid'),
    due_date DATE NOT NULL,
    paid_date DATE,
    loan_days_overdue DATE NOT NULL,
    notes TEXT,
    user_id BIGINT NOT NULL,
    loan_id BIGINT NOT NULL,
    CONSTRAINT pk_overduefees PRIMARY KEY(id),
    CONSTRAINT fk_overduefees_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_overduefees_loan FOREIGN KEY(loan_id) REFERENCES loans(id),
    CONSTRAINT chk_overduesfees_amount_positive CHECK (amount > 0.0)
) ENGINE=InnoDB;