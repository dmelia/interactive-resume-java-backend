CREATE TABLE public.refresh_tokens
(
    id      serial NOT NULL PRIMARY KEY,
    version INTEGER,
    expires TIMESTAMP(6) WITH TIME ZONE,
    token   VARCHAR(255),
    user_id bigint NOT NULL,
    CONSTRAINT fk_users_refresh_tokens_id
        FOREIGN KEY (user_id)
            REFERENCES public.users (id)
);

CREATE UNIQUE INDEX idx_refresh_token_id ON public.refresh_tokens (id);