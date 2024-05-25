CREATE TABLE client
(
    client_id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(250)
);

CREATE TABLE status
(
    status_id SERIAL PRIMARY KEY,
    status_name VARCHAR(30) NOT NULL
);

CREATE TABLE team_members
(
    member_id SERIAL PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email_address VARCHAR(250) NOT NULL
);

CREATE TABLE type
(
    type_id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE project
(
    project_id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(250),
    client_id INT NOT NULL,
    status_id INT DEFAULT 1,
    member_id INT,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE,
    CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id) ON DELETE CASCADE,
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES team_members(member_id) ON DELETE CASCADE
);

CREATE TABLE item
(
    item_id SERIAL PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    description VARCHAR(250),
    project_id INT,
    CONSTRAINT fk_project FOREIGN KEY(project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    status_id INT,
    CONSTRAINT fk_status_item FOREIGN KEY(status_id) REFERENCES status(status_id) ON DELETE CASCADE,
    type_id INT,
    member_id INT,
    CONSTRAINT fk_type FOREIGN KEY(type_id) REFERENCES type(type_id) ON DELETE CASCADE,
    CONSTRAINT fk_member_item FOREIGN KEY (member_id) REFERENCES team_members(member_id) ON DELETE CASCADE
);