package com.interactiveresume.Interactive.Resume.Backend.services.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.data.models.User;

import java.util.List;

public interface UserService {

    void deactivateUser(User user);

    void saveUser(User user);

    User findByEmail(String input);
    User findByUsername(String input);

    List<User> findUsers();
}
