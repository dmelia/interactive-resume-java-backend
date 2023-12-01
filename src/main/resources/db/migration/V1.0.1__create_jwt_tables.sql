CREATE TABLE public.refresh_tokens
(
    id      serial NOT NULL PRIMARY KEY,
    version INTEGER,
    expires TIMESTAMP(6) WITH TIME ZONE,
    token   VARCHAR(255),
    user_id bigint NOT NULL
        CONSTRAINT fk_users_refresh_tokens
            REFERENCES public.users
);



