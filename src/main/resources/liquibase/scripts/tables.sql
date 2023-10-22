-- liquibase formatted sql

-- changeset Karachevtsev:1
CREATE TABLE cat (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    breed VARCHAR(255),
    age INT,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES userforcatsshelter(id)
);

CREATE TABLE dog (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    breed VARCHAR(255),
    age INT,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES userfordogsshelter(id)
);

CREATE TABLE userfordogsshelter (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    chatId VARCHAR(255) NOT NULL
);

CREATE TABLE userforcatsshelter (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    chatId VARCHAR(255) NOT NULL
);

CREATE TABLE report (
    id BIGINT PRIMARY KEY,
    animalName VARCHAR(255),
    diet VARCHAR(255),
    wellBeing VARCHAR(255),
    behaviorChanges VARCHAR(255),
    reportDate TIMESTAMP,
    animalType VARCHAR(255),
    filePath VARCHAR(255),
    fileSize BIGINT,
    userForCats_id BIGINT,
    userForDogs_id BIGINT,
    FOREIGN KEY (userForCats_id) REFERENCES userforcatsshelter(id),
    FOREIGN KEY (userForDogs_id) REFERENCES userfordogsshelter(id)
);