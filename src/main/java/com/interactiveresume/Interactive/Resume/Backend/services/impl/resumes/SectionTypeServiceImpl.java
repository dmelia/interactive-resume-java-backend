package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionTypeJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SectionTypeServiceImpl implements SectionTypeService {

    private final SectionTypeJPARepository sectionTypeJPARepository;

    public SectionTypeServiceImpl(SectionTypeJPARepository sectionTypeJPARepository) {
        this.sectionTypeJPARepository = sectionTypeJPARepository;
    }

    @Override
    public void deleteSectionType(Long id) {

    }

    @Override
    public SectionType saveSectionType(SectionType sectionType) {
        return null;
    }

    @Override
    public List<SectionType> getSectionTypes(User user) {
        return sectionTypeJPARepository.findSectionTypesByUser(user);
    }

    @Override
    public List<SectionType> getGenericSectionTypes() {
        return sectionTypeJPARepository.getGenericSectionTypes();
    }

    @Override
    public void deleteSectionInputType(Long id) {

    }

    @Override
    public SectionInputType saveSectionInputType(SectionInputType sectionInputType) {
        return null;
    }

    @Override
    public SectionType getSectionTypeById(Long id) {
        return null;
    }
}
