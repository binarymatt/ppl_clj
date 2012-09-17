CREATE TABLE companies (
    id SERIAL NOT NULL PRIMARY KEY,
    name character varying(255),
    slug character varying(255),
    url character varying(255),
    address text,
    description text,
    created_ts timestamp without time zone DEFAULT now(),
    updated_ts timestamp without time zone DEFAULT now(),
    location character varying(255),
    email character varying(256),
    twitter character varying(256)
);
