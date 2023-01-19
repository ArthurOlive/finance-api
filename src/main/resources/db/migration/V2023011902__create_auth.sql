CREATE SEQUENCE IF NOT EXISTS public.auth_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.auth
(
    id bigint NOT NULL DEFAULT nextval('auth_id_seq'::regclass),
    created_at timestamp without time zone,
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT auth_pkey PRIMARY KEY (id),
    CONSTRAINT uk_5re8bghl4sqkib3pcehhi4src UNIQUE (username)
)