CREATE TABLE profiles (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer references users(id),
    slug character varying(255),
    created_ts timestamp without time zone DEFAULT now(),
    name character varying(255),
    bio text,
    location character varying(255),
    updated_ts timestamp without time zone DEFAULT now(),
    url character varying(255),
    email character varying(256),
    twitter character varying(256)
);
