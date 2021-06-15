-- liquibase formatted sql
-- changeset agafonov:15.06.2021-1
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from pg_catalog.pg_tables where tablename = 'books';
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
    book_json jsonb,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;