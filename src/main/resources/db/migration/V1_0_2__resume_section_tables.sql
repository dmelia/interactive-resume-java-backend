-- Create section tables

CREATE TABLE IF NOT EXISTS public.resume_sections
(
    id       SERIAL PRIMARY KEY,
    position INT,
    version  INT,
    page_id  BIGINT REFERENCES public.resume_pages (id)
);

CREATE TABLE IF NOT EXISTS public.resume_section_fields
(
    id         SERIAL PRIMARY KEY,
    value      VARCHAR,
    type       VARCHAR,
    version    INT,
    section_id BIGINT REFERENCES public.resume_sections (id)
);

-- Index for faster lookups on resume_sections
CREATE INDEX idx_resume_section_elements_position ON public.resume_sections (position);

-- Index for faster lookups on resume_section_fields
CREATE INDEX idx_resume_element_values_section_id ON public.resume_section_fields (section_id);
