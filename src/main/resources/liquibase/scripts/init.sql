-- Liquibase formatted SQL

-- Changeset Nemahov:2

CREATE TABLE CAT
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    breed       VARCHAR(255) NOT NULL,
    age         INT NOT NULL,
    description VARCHAR(255) NOT NULL
);
ALTER TABLE CAT
    OWNER TO postgres;

CREATE TABLE DOG
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    breed       VARCHAR(255) NOT NULL,
    age         INT NOT NULL,
    description VARCHAR(255) NOT NULL
);
ALTER TABLE DOG
    OWNER TO postgres;

CREATE TABLE humanCat
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    yearOfBirth INT NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    chatId      BIGINT NOT NULL,
    status      VARCHAR(255) NOT NULL,
    cat_id      BIGINT NOT NULL,
    CONSTRAINT CAT_ID_FK FOREIGN KEY (cat_id) REFERENCES CAT (id)
);
ALTER TABLE humanCat
    OWNER TO postgres;

CREATE TABLE humanDog
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    yearOfBirth INT NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    chatId      BIGINT NOT NULL,
    status      VARCHAR(255) NOT NULL,
    dog_id      BIGINT NOT NULL,
    CONSTRAINT DOG_ID_FK FOREIGN KEY (dog_id) REFERENCES DOG (id)
);
ALTER TABLE humanDog
    OWNER TO postgres;
CREATE TABLE reports (
                         id SERIAL PRIMARY KEY,
                         chatId BIGINT,
                         ration VARCHAR(255),
                         health VARCHAR(255),
                         habits VARCHAR(255),
                         days BIGINT,
                         filePath VARCHAR(255),
                         fileSize BIGINT,
                         data BYTEA,
                         caption VARCHAR(255),
                         lastMessage TIMESTAMP,
                         lastMessageMs BIGINT,
                         humanCat_id BIGINT,
                         humanDog_id BIGINT,
                         FOREIGN KEY (humanCat_id) REFERENCES humanCat (id),
                         FOREIGN KEY (humanDog_id) REFERENCES humanDog (id)
);

