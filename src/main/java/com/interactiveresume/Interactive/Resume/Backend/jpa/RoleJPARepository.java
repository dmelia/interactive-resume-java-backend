package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.data.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJPARepository extends CrudRepository<Role, String> {
}