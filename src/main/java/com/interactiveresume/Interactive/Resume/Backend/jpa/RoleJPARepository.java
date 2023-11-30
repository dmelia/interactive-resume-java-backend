package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleJPARepository extends CrudRepository<Role, String> {
}