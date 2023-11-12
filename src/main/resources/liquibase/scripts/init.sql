-- Liquibase formatted SQL

-- Changeset Nemahov:2

-- Создаем таблицу CAT
CREATE TABLE cat
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    breed       VARCHAR(255) NOT NULL,
    age         INT NOT NULL,
    description VARCHAR(255) NOT NULL
);
ALTER TABLE cat
    OWNER TO postgres;

-- Создаем таблицу DOG
CREATE TABLE dog
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    breed       VARCHAR(255) NOT NULL,
    age         INT NOT NULL,
    description VARCHAR(255) NOT NULL
);
ALTER TABLE dog
    OWNER TO postgres;

-- Создаем таблицу human_cat
CREATE TABLE human_cat
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    year_of_birth INT NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    chat_id     BIGINT NOT NULL,
    status      VARCHAR(255) NOT NULL,
    cat_id      BIGINT,
    CONSTRAINT CAT_ID_FK FOREIGN KEY (cat_id) REFERENCES cat (id)
);
ALTER TABLE human_cat
    OWNER TO postgres;

-- Создаем таблицу human_dog
CREATE TABLE human_dog
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    year_of_birth INT NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    chat_id     BIGINT NOT NULL,
    status      VARCHAR(255) NOT NULL,
    dog_id      BIGINT,
    CONSTRAINT DOG_ID_FK FOREIGN KEY (dog_id) REFERENCES dog (id)
);
ALTER TABLE human_dog
    OWNER TO postgres;

-- Создаем таблицу reports
CREATE TABLE reports
(
    id            BIGSERIAL PRIMARY KEY,
    chatId        BIGINT,
    ration        VARCHAR(255),
    health        VARCHAR(255),
    habits        VARCHAR(255),
    days          BIGINT,
    filePath      VARCHAR(255),
    fileSize      BIGINT,
    data          BYTEA,
    caption       VARCHAR(255),
    lastMessage   TIMESTAMP,
    lastMessageMs BIGINT,
    human_сat_id   BIGINT,
    human_dog_id   BIGINT,
    CONSTRAINT human_cat_id_FK FOREIGN KEY (human_cat_id) REFERENCES human_cat (id),
    CONSTRAINT human_dog_id_FK FOREIGN KEY (human_dog_id) REFERENCES human_dog (id)
);
