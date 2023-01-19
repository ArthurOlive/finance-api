CREATE SEQUENCE IF NOT EXISTS public.user_sys_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.user_sys
(
    id bigint NOT NULL DEFAULT nextval('user_sys_id_seq'::regclass),
    created_at timestamp without time zone,
    auth_id bigint,
    role_id bigint,
    CONSTRAINT user_sys_pk PRIMARY KEY (id),
    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_auth FOREIGN KEY (auth_id)
        REFERENCES public.auth (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
