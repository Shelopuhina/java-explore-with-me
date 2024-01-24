DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS requests CASCADE;
DROP TABLE IF EXISTS compilations CASCADE;
DROP TABLE IF EXISTS compilations_events CASCADE;
CREATE TABLE users (
    id    INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name  VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    CONSTRAINT uq_user_email UNIQUE (email)
);

CREATE TABLE categories
(
    id   INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    CONSTRAINT uq_category_name UNIQUE (name)
);

CREATE TABLE compilations
(
    id     INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title  VARCHAR NOT NULL,
    pinned BOOLEAN NOT NULL,
    CONSTRAINT uq_compilation_title UNIQUE (title)
);

CREATE TABLE events
(
    id                 INTEGER          NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    initiator_id       INTEGER          NOT NULL,
    annotation         VARCHAR          NOT NULL,
    category_id        INTEGER          NOT NULL,
    description        VARCHAR          NOT NULL,
    event_date         TIMESTAMP        NOT NULL,
    lat                DOUBLE PRECISION NOT NULL,
    lon                DOUBLE PRECISION NOT NULL,
    paid               BOOLEAN          NOT NULL,
    participant_limit  INTEGER          NOT NULL,
    request_moderation BOOLEAN          NOT NULL,
    title              VARCHAR          NOT NULL,
    created_on         TIMESTAMP,
    state              VARCHAR          NOT NULL,
    published_on       TIMESTAMP,
    CONSTRAINT fk_events_to_users FOREIGN KEY (initiator_id) REFERENCES users (id),
    CONSTRAINT fk_events_to_categories FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE compilations_events (
    id             INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    compilation_id INTEGER NOT NULL,
    event_id       INTEGER NOT NULL,
    CONSTRAINT fk_compilations_events_to_compilations FOREIGN KEY (compilation_id) REFERENCES compilations (id),
    CONSTRAINT fk_compilations_events_to_events FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE requests
(
    id           INTEGER   NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id     INTEGER   NOT NULL,
    requester_id INTEGER   NOT NULL,
    created      TIMESTAMP NOT NULL,
    status       VARCHAR   NOT NULL,
    CONSTRAINT fk_requests_to_users FOREIGN KEY (requester_id) REFERENCES users (id),
    CONSTRAINT fk_requests_to_events FOREIGN KEY (event_id) REFERENCES events (id)
);