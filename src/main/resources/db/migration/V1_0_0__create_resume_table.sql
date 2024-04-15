CREATE SEQUENCE public.users_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE public.users
(
    active    boolean,
    id        BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('users_id_seq'),
    email     VARCHAR(100) UNIQUE,
    firstname VARCHAR(100),
    lastname  VARCHAR(100),
    username  VARCHAR(255) UNIQUE,
    password  VARCHAR(255),
    version   INTEGER
);

CREATE UNIQUE INDEX idx_user_id ON public.users (id);

CREATE TABLE public.roles
(
    name VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE SEQUENCE public.resumes_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE public.resumes
(
    id        BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('resumes_id_seq'),
    name    VARCHAR(255),
    icon    VARCHAR(255),
    version INTEGER,
    user_id bigint NOT NULL,
    CONSTRAINT fk_users_resumes_id
        FOREIGN KEY (user_id)
            REFERENCES public.users (id)
);

CREATE UNIQUE INDEX idx_resume_id ON public.resumes (id);

CREATE SEQUENCE public.resume_pages_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE public.resume_pages
(
    id        BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('resume_pages_id_seq'),
    name      VARCHAR(255),
    index     INTEGER,
    version   INTEGER,
    resume_id BIGINT NOT NULL,
    CONSTRAINT fk_resumes_resume_pages_id
        FOREIGN KEY (resume_id)
            REFERENCES public.resumes (id)
);

CREATE UNIQUE INDEX idx_resume_page_id ON public.resume_pages (id);

CREATE TABLE public.users_roles
(
    user_id   BIGINT      NOT NULL,
    CONSTRAINT fk_users_roles_users_id
        FOREIGN KEY (user_id)
            REFERENCES public.users (id),
    role_name VARCHAR(50) NOT NULL,
    CONSTRAINT fk_users_roles_roles_name
        FOREIGN KEY (role_name)
            REFERENCES public.roles,
    PRIMARY KEY (user_id, role_name)
);

-- Insert the default values into the ROLES table
INSERT INTO public.roles (name)
VALUES ('STANDARD_USER');
INSERT INTO public.roles (name)
VALUES ('ADMIN_USER');



-- Create the function that will be called by the trigger on user creation
CREATE OR REPLACE FUNCTION public.insert_into_users_roles()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO public.users_roles(user_id, role_name)
    VALUES (NEW.id, 'STANDARD_USER');
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

-- Set the function to be called on insert into the user table, the default user role will be 'STANDARD_USER'
CREATE TRIGGER trigger_insert_into_users_roles
    AFTER INSERT
    ON public.users
    FOR EACH ROW
EXECUTE FUNCTION public.insert_into_users_roles();