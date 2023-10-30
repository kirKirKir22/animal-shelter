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

CREATE TABLE report
(
    id            BIGSERIAL PRIMARY KEY,
    chatId        BIGINT,
    ration        VARCHAR(255) NOT NULL,
    health        VARCHAR(255) NOT NULL,
    habits        VARCHAR(255) NOT NULL,
    days          BIGINT NOT NULL,
    filePath      VARCHAR(255) NOT NULL,
    fileSize      BIGINT NOT NULL,
    caption       VARCHAR(255) NOT NULL,
    lastMessage   DATE NOT NULL,
    lastMessageMs BIGINT NOT NULL,
    humanCat_id  BIGINT,
    humanDog_id  BIGINT,
    CONSTRAINT humanCat_id_FK FOREIGN KEY (humanCat_id) REFERENCES humanCat (id),
    CONSTRAINT humanDog_id_FK FOREIGN KEY (humanDog_id) REFERENCES humanDog (id)
);
