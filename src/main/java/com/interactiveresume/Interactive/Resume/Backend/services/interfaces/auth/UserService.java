package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.auth.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.Role;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface UserService {

    /**
     * Gets the current {@link User} from the {@link SecurityContextHolder}
     * @return {@link User} the logged-in user
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    User getCurrentUser() throws UserNotFoundException;

    /**
     * Deactivate a {@link User} account, should only be called by an administrator or the current user
     * @param user the {@link User} to deactivate
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    void deactivateUser(User user) throws UserNotFoundException;

    /**
     * Updates a pre-existing {@link User} in the database
     * @param userDTO the {@link UserDTO} containing the data to update
     * @return the result of the saved {@link User} to the database
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    User saveUser(UserDTO userDTO) throws UserNotFoundException;

    /**
     * Creates a new {@link User} in the database, the default rights should be that of a standard user
     * @param userDTO the {@link UserDTO}containing the data for creating the {@link User}
     * @return the result of the saved {@link User} to the database
     */
    User createUser(UserDTO userDTO);


    /**
     * Find a {@link User} by its email
     * @param input the email
     * @return {@link User} the found User account
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    User findByEmail(String input) throws UserNotFoundException;

    /**
     * Finds and returns a {@link User} by its username
      * @param input the username
     * @return the found {@link User} or null
     */
    User findByUsername(String input);

    /**
     * Find all users currently saved in the application, should only be called by a {@link User} with the administrator {@link Role}
     * @return {@link List<User>} the users present in the database
     */
    List<User> findUsers();
}
