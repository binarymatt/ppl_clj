CREATE TABLE users (
    id SERIAL NOT NULL PRIMARY KEY,
    sign_in_count integer DEFAULT 0,
    created_ts timestamp without time zone DEFAULT now(),
    updated_ts timestamp without time zone,
    admin boolean DEFAULT false,
    email character varying(256) NOT NULL,
    access_token character varying(255) NOT NULL,
    access_token_secret text,
    provider character varying(255)
);
