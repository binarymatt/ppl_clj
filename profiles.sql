CREATE TABLE profiles (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer references users(id) NOT NULL,
    slug character varying(255),
    created_ts timestamp without time zone DEFAULT now(),
    name character varying(255),
    bio text,
    location character varying(255),
    lat double precision,
    long double precision,
    updated_ts timestamp without time zone DEFAULT now(),
    url character varying(255),
    twitter character varying(256),
    github_name character varying(255)
);
