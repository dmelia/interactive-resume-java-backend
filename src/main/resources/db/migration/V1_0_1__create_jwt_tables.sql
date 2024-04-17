CREATE SEQUENCE public.refresh_tokens_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE public.refresh_tokens
(
    id      BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('refresh_tokens_id_seq'),
    version INTEGER,
    expires TIMESTAMP(6) WITH TIME ZONE,
    token   UUID,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_users_refresh_tokens_id
        FOREIGN KEY (user_id)
            REFERENCES public.users (id)
);

CREATE UNIQUE INDEX idx_refresh_token_id ON public.refresh_tokens (id);