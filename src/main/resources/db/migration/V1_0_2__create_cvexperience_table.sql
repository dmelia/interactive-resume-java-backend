CREATE TABLE resume_experiences
(
    id          SERIAL PRIMARY KEY,
    placement     INT,
    title       VARCHAR(255),
    company     VARCHAR(255),
    description TEXT,
    start_date  DATE,
    end_date    DATE,
    current     BOOLEAN,
    footnotes   TEXT,
    notes       TEXT,
    resume_id   BIGINT NOT NULL,
    version     INT,

    CONSTRAINT fk_resume_experiences_resume_id
        FOREIGN KEY (resume_id)
            REFERENCES public.resumes (id)
);

CREATE UNIQUE INDEX idx_resume_experiences_id ON resume_experiences (id);