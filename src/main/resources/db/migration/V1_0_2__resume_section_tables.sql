-- Create section tables

CREATE SEQUENCE public.resume_sections_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS public.resume_sections
(
    id       BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('resume_sections_id_seq'),
    position INT,
    version  INT,
    page_id  BIGINT NOT NULL,
    CONSTRAINT fk_resumes_resume_pages_id
        FOREIGN KEY (page_id)
            REFERENCES public.resume_pages (id)
);

CREATE UNIQUE INDEX idx_resume_sections_id ON public.resume_sections (id);

CREATE SEQUENCE public.resume_section_fields_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS public.resume_section_fields
(
    id         BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('resume_section_fields_id_seq'),
    value      VARCHAR,
    type       VARCHAR,
    version    INT,
    section_id BIGINT NOT NULL,
    CONSTRAINT fk_resumes_resume_sections_id
        FOREIGN KEY (section_id)
            REFERENCES public.resume_sections (id)
);

CREATE UNIQUE INDEX idx_resume_section_fields_id ON public.resume_section_fields (id);

-- Index for faster lookups on resume_section_fields
CREATE INDEX idx_resume_section_fields_section_id ON public.resume_section_fields (section_id);
