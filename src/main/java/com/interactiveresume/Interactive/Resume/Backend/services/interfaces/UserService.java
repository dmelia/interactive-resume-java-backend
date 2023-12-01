package com.interactiveresume.Interactive.Resume.Backend.services.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    User getCurrentUser() throws UserNotFoundException;

    void deactivateUser(User user) throws UserNotFoundException;

    User saveUser(User user);

    User createUser(User user);

    User findByEmail(String input) throws UserNotFoundException;
    User findByUsername(String input);

    List<User> findUsers();
}
