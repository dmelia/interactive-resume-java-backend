DO
$$
    DECLARE
        work_experience_id bigint;
        education_id       bigint;
        skills_id          bigint;
    BEGIN
        -- Inserting generic section types and retrieve the ids
        INSERT INTO public.resume_section_types (title, description, notes, icon, generic, version, user_id)
        VALUES ('Work Experience', 'Details about your work experience',
                'Additional notes about work experience section', 'work_icon.png', true, 1, NULL)
        RETURNING id INTO work_experience_id;

        INSERT INTO public.resume_section_types (title, description, notes, icon, generic, version, user_id)
        VALUES ('Education', 'Details about your education', 'Additional notes about education section',
                'education_icon.png', true, 1, NULL)
        RETURNING id INTO education_id;

        INSERT INTO public.resume_section_types (title, description, notes, icon, generic, version, user_id)
        VALUES ('Skills', 'List of your skills', 'Additional notes about skills section', 'skills_icon.png', true, 1, NULL)
        RETURNING id INTO skills_id;

        -- Inserting generic section input types for 'Work Experience'
        INSERT INTO public.resume_section_input_types (title, type, position, version, section_type_id)
        VALUES ('Job Title', 'text', 1, 1, work_experience_id),
               ('Company', 'text', 2, 1, work_experience_id),
               ('Description', 'textarea', 3, 1, work_experience_id),
               ('Start Date', 'date', 4, 1, work_experience_id),
               ('End Date', 'date', 5, 1, work_experience_id);

        -- Inserting generic section input types for 'Education'
        INSERT INTO public.resume_section_input_types (title, type, position, version, section_type_id)
        VALUES ('Institution', 'text', 1, 1, education_id),
               ('Degree', 'text', 2, 1, education_id),
               ('Description', 'textarea', 3, 1, education_id),
               ('Field of Study', 'text', 4, 1, education_id),
               ('Graduation Date', 'date', 5, 1, education_id);

        -- Inserting generic section input types for 'Skills'
        INSERT INTO public.resume_section_input_types (title, type, position, version, section_type_id)
        VALUES ('Skill', 'text', 1, 1, skills_id),
               ('Proficiency', 'text', 2, 1, skills_id);
    END
$$;