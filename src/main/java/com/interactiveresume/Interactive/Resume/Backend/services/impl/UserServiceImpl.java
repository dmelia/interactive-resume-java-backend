package com.interactiveresume.Interactive.Resume.Backend.services.impl;

import com.interactiveresume.Interactive.Resume.Backend.constants.Constants;
import com.interactiveresume.Interactive.Resume.Backend.data.models.Role;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.UserJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserJPARepository userJPARepository;

    //TODO use this and then return an exception if email is not valid
    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmail(String s) {
        return EMAIL.matcher(s).matches();
    }

    public UserServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    /**
     * Gets the current {@link User} from the {@link SecurityContextHolder}
     * @return {@link User} the logged-in user
     * @throws UserNotFoundException
     */
    @Override
    public User getCurrentUser() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();

        Optional<User> optionalUser = userJPARepository.findByUsername(usernameFromAccessToken);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * Deactivate a {@link User} account, should only be called by an administrator or the current user
     * @param user
     */
    @Override
    public void deactivateUser(User user) throws UserNotFoundException {
        User currentUser = getCurrentUser();
        Set<Role> roles = currentUser.getRoles();
        if (currentUser.getUsername().equals(user.getUsername()) || roles.stream().anyMatch(role -> role.getName().equals(Constants.ADMIN_USER_ROLE))) {
            user.setActive(false);
            userJPARepository.save(user);
            userJPARepository.flush();
        }
    }

    //TODO
    @Override
    public User saveUser(User user) {
        User savedUser = null;
        savedUser = userJPARepository.save(user);
        return savedUser;
    }

    //TODO
    @Override
    public User createUser(User user) {
        user.setActive(true);
        return userJPARepository.save(user);
    }

    //TODO
    @Override
    public User findByUsername(String input) {

        return null;
    }

    /**
     * Find a {@link User} by its email
     * @param input the email
     * @return {@link User} the found User account
     * @throws {@link UserNotFoundException} if the user is not found
     */
    @Override
    public User findByEmail(String input) throws UserNotFoundException {
        Optional<User> optionalUser = userJPARepository.findByEmail(input);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }


    /**
     * Find all users currently saved in the application, should only be called by a {@link User} with the administrator {@link Role}
     * @return {@link List<User>} the users present in the database
     */
    @Override
    public List<User> findUsers() {
        return userJPARepository.findAll();
    }

    /**
     * The method inherited from the UserDetailsService interface, used by Spring Security
     * @param username the username of the user we wish to load
     * @return {@link UserDetails} the security details of the user
     * @throws {@link UsernameNotFoundException}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userJPARepository.findByUsername(username);

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        User user = userOptional.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
    }
}
