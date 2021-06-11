CREATE SEQUENCE public.books_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.books_id_seq
    OWNER TO postgres;

CREATE TABLE public.books
(
    id bigint NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    genre integer,
    name character varying(255) COLLATE pg_catalog."default",
    pages integer,
    price numeric(19,2),
    publishdate date,
    bookjson jsonb,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;