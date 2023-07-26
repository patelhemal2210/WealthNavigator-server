------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
--  USER --
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE USER wnistuser WITH PASSWORD '*******';
ALTER ROLE wnistuser SET search_path TO wnist;

GRANT ALL ON SCHEMA wnist TO wnistuser;

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA wnist TO wnistuser;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA wnist TO wnistuser;

-- SELECT * FROM information_schema.role_table_grants WHERE grantee='wnistuser';

-- SELECT * FROM pg_tables WHERE tableowner = 'wnistuser';


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
--  TABLES --
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


-- Table: wnist.user

-- DROP TABLE IF EXISTS wnist."user";

CREATE TABLE IF NOT EXISTS wnist."user"
(
    _id BIGSERIAL NOT NULL,
    firstname text COLLATE pg_catalog."default" NOT NULL,
    lastname text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    enabled boolean NOT NULL,
    locked boolean NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (_id),
    CONSTRAINT user_email_key UNIQUE (email)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS wnist."user"
    OWNER to postgres;

REVOKE ALL ON TABLE wnist."user" FROM wnistuser;

GRANT ALL ON TABLE wnist."user" TO postgres;

GRANT UPDATE, INSERT, SELECT ON TABLE wnist."user" TO wnistuser;

-- Table: wnist.role

-- DROP TABLE IF EXISTS wnist.role;

CREATE TABLE IF NOT EXISTS wnist.role
(
    _id BIGSERIAL NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (_id),
    CONSTRAINT role_name_key UNIQUE (name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS wnist.role
    OWNER to postgres;

REVOKE ALL ON TABLE wnist.role FROM wnistuser;

GRANT ALL ON TABLE wnist.role TO postgres;

GRANT UPDATE, INSERT, SELECT ON TABLE wnist.role TO wnistuser;    OWNER to postgres;


-- Table: wnist.user_role

-- DROP TABLE IF EXISTS wnist.user_role;

CREATE TABLE IF NOT EXISTS wnist.user_role
(
    _id BIGSERIAL NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (_id),
    CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES wnist.role (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES wnist."user" (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS wnist.user_role
    OWNER to postgres;

REVOKE ALL ON TABLE wnist.user_role FROM wnistuser;

GRANT ALL ON TABLE wnist.user_role TO postgres;

GRANT UPDATE, INSERT, SELECT ON TABLE wnist.user_role TO wnistuser;

-- Table: wnist.auth_attempt

-- DROP TABLE IF EXISTS wnist.auth_attempt;

CREATE TABLE IF NOT EXISTS wnist.auth_attempt
(
    _id BIGSERIAL NOT NULL,
    user_id bigint NOT NULL,
    success boolean NOT NULL,
    date date NOT NULL,
    CONSTRAINT auth_attempt_pkey PRIMARY KEY (_id),
    CONSTRAINT auth_attempt_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES wnist."user" (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS wnist.auth_attempt
    OWNER to postgres;

REVOKE ALL ON TABLE wnist.auth_attempt FROM wnistuser;

GRANT ALL ON TABLE wnist.auth_attempt TO postgres;

GRANT UPDATE, INSERT, SELECT ON TABLE wnist.auth_attempt TO wnistuser;