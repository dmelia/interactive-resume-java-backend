CREATE TABLE public.users
(
    active    boolean,
    id        serial NOT NULL PRIMARY KEY,
    email     VARCHAR(50) UNIQUE,
    firstname VARCHAR(50),
    lastname  VARCHAR(50),
    username  VARCHAR(50) UNIQUE,
    password  VARCHAR(100),
    version   INTEGER
);

CREATE TABLE public.roles
(
    name VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE public.resumes
(
    id      serial NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    version INTEGER,
    user_id bigint NOT NULL
        CONSTRAINT fk_users_resumes
            REFERENCES public.users
);

CREATE TABLE public.users_roles
(
    user_id   bigint      NOT NULL
        CONSTRAINT fk_users_roles_users
            REFERENCES users,
    role_name VARCHAR(50) NOT NULL
        CONSTRAINT fk_users_roles_roles
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