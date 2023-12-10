-- Create section tables

CREATE TABLE IF NOT EXISTS public.resume_section_types
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR,
    description VARCHAR,
    notes       VARCHAR,
    icon        VARCHAR,
    generic     BOOLEAN,
    version     INT,
    -- The reference to the resume can be null, in the case of a generic section type
    resume_id   BIGINT REFERENCES public.resumes (id) NULL
);

CREATE TABLE IF NOT EXISTS public.resume_section_input_types
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR,
    type            VARCHAR,
    position        INT,
    version         INT,
    section_type_id BIGINT REFERENCES public.resume_section_types (id),
    CONSTRAINT unique_section_input_types UNIQUE (title, section_type_id)
);

CREATE TABLE IF NOT EXISTS public.resume_section_elements
(
    id              SERIAL PRIMARY KEY,
    position        INT,
    version         INT,
    section_type_id BIGINT REFERENCES public.resume_section_types (id),
    CONSTRAINT unique_section_elements UNIQUE (position, section_type_id)
);

CREATE TABLE IF NOT EXISTS public.resume_element_values
(
    id                 SERIAL PRIMARY KEY,
    value              VARCHAR,
    section_element_id BIGINT REFERENCES public.resume_section_elements (id),
    input_type_id      BIGINT REFERENCES public.resume_section_input_types (id),
    CONSTRAINT unique_element_values UNIQUE (section_element_id, input_type_id)
);

-- Index for faster lookups on resume_section_types
CREATE INDEX idx_resume_section_types_title ON public.resume_section_types (title);

-- Index for faster lookups on resume_section_input_types
CREATE INDEX idx_resume_section_input_types_title ON public.resume_section_input_types (title);
CREATE INDEX idx_resume_section_input_types_section_type_id ON public.resume_section_input_types (section_type_id);

-- Index for faster lookups on resume_section_elements
CREATE INDEX idx_resume_section_elements_position ON public.resume_section_elements (position);
CREATE INDEX idx_resume_section_elements_section_type_id ON public.resume_section_elements (section_type_id);

-- Index for faster lookups on resume_element_values
CREATE INDEX idx_resume_element_values_section_element_id ON public.resume_element_values (section_element_id);
CREATE INDEX idx_resume_element_values_input_type_id ON public.resume_element_values (input_type_id);
