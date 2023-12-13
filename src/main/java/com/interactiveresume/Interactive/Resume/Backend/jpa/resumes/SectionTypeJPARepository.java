package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionTypeJPARepository extends JpaRepository<SectionType, Long> {

    /**
     * Returns all values from the database where generic is true
     *
     * @return the generic {@link List<SectionType>}
     */
    @Query("SELECT st FROM SectionType st WHERE st.generic = true")
    List<SectionType> getGenericSectionTypes();

    /**
     * Returns a {@link List<SectionType>} by user and where generic is false
     *
     * @param user the {@link User}
     * @return the list of {@link SectionType}
     */
    @Query("SELECT st FROM SectionType st WHERE st.generic = false AND st.user=:user")
    List<SectionType> findSectionTypesByUser(User user);
}
