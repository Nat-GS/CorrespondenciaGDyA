-- Table: roles
CREATE TABLE IF NOT EXISTS roles (
    id_rol SERIAL PRIMARY KEY,
    user_role VARCHAR(75) NOT NULL UNIQUE,
    status SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: person
CREATE TABLE IF NOT EXISTS person (
    id_person SERIAL PRIMARY KEY,
    ci VARCHAR(75) NOT NULL UNIQUE,
    name_ VARCHAR(75) NOT NULL,
    father_last_name VARCHAR(75),
    mother_last_name VARCHAR(75),
    description VARCHAR(2000),
    email VARCHAR(150) NOT NULL UNIQUE,
    cellphone VARCHAR(75) NOT NULL UNIQUE,
    status SMALLINT NOT NULL,
    image_url VARCHAR(300),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: users
CREATE TABLE IF NOT EXISTS users (
    id_users SERIAL PRIMARY KEY,
    person_id_person INT UNIQUE REFERENCES person(id_person) ON DELETE CASCADE,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(4000) NOT NULL,
    salt VARCHAR(4000) NOT NULL,
    status SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: rol_has_user
CREATE TABLE IF NOT EXISTS rol_has_user (
    id_rol_user SERIAL PRIMARY KEY,
    users_id_users INT NOT NULL REFERENCES users(id_users) ON DELETE CASCADE,
    roles_id_rol INT NOT NULL REFERENCES roles(id_rol) ON DELETE CASCADE,
    status SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: doc
CREATE TABLE IF NOT EXISTS doc (
    id_doc SERIAL PRIMARY KEY,
    rol_has_user_id_rol_user INT NOT NULL REFERENCES rol_has_user(id_rol_user) ON DELETE CASCADE,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    send_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: doc_rev
CREATE TABLE IF NOT EXISTS doc_rev (
    id_doc_rev SERIAL PRIMARY KEY,
    rol_has_user_id_rol_user INT NOT NULL REFERENCES rol_has_user(id_rol_user) ON DELETE CASCADE,
    doc_id_doc INT NOT NULL REFERENCES doc(id_doc) ON DELETE CASCADE,
    reference VARCHAR(50) NOT NULL,
    office VARCHAR(12) NOT NULL,
    receiver VARCHAR(50) NOT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: doc_send
--CREATE TABLE IF NOT EXISTS doc_send (
  --  id_soc_sen SERIAL PRIMARY KEY,
--    d_messenger VARCHAR(50) NOT NULL,
    --d_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    --r_messenger VARCHAR(50) NOT NULL,
  --  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
--);
