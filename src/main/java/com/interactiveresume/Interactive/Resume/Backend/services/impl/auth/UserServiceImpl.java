package com.interactiveresume.Interactive.Resume.Backend.services.impl.auth;

import com.interactiveresume.Interactive.Resume.Backend.constants.Constants;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.auth.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.Role;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.auth.UserJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
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

    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Checks if an email is of a valid format
     *
     * @param input the email to check
     * @return if the email is valid or not
     */
    private static boolean isEmail(String input) {
        return EMAIL.matcher(input).matches();
    }

    /**
     * Constructor
     *
     * @param userJPARepository
     */
    public UserServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User getCurrentUser() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();

        Optional<User> optionalUser = userJPARepository.findByUsername(usernameFromAccessToken);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }


    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveUser(UserDTO userDTO) throws UserNotFoundException {
        if (userDTO.getId() == null) throw new InputInvalidException();
        Optional<User> optionalUser = userJPARepository.findById(userDTO.getId());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            User currentUser = getCurrentUser();

            // Check if the current user has the same ID as the one being updated
            if (existingUser.getId().equals(currentUser.getId())) {
                // Update user fields
                existingUser.setFirstname(userDTO.getFirstname());
                existingUser.setLastname(userDTO.getLastname());
                existingUser.setEmail(userDTO.getEmail());

                // Save and return the updated user
                return userJPARepository.save(existingUser);
            } else {
                // Throw an exception or handle unauthorized access
                throw new InputInvalidException();
            }
        } else {
            // Handle user not found
            throw new InputInvalidException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(UserDTO userDTO) {
        if (!isEmail(userDTO.getEmail())) {
            throw new InputInvalidException();
        }

        // Check if username or email already exists
        if (userJPARepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new InputInvalidException();
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .active(true)
                .build();

        return userJPARepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByUsername(String input) {
        Optional<User> userOptional = userJPARepository.findByUsername(input);
        return userOptional.orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String input) throws UserNotFoundException {
        Optional<User> optionalUser = userJPARepository.findByEmail(input);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findUsers() {
        return userJPARepository.findAll();
    }

    /**
     * The method inherited from the UserDetailsService interface, used by Spring Security
     *
     * @param username the username of the user we wish to load
     * @return {@link UserDetails} the security details of the user
     * @throws {@link UsernameNotFoundException}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userJPARepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        User user = userOptional.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
    }
}
