package com.interactiveresume.Interactive.Resume.Backend.services.impl.auth;

import com.interactiveresume.Interactive.Resume.Backend.constants.Constants;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.Role;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.auth.UserJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SectionTypeService sectionTypeService;

    private final UserJPARepository userJPARepository;

    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Checks if an email is of a valid format
     * @param input the email to check
     * @return if the email is valid or not
     */
    private static boolean isEmail(String input) {
        return EMAIL.matcher(input).matches();
    }

    /**
     * Constructor
     * @param userJPARepository
     * @param sectionTypeService
     */
    public UserServiceImpl(UserJPARepository userJPARepository, SectionTypeService sectionTypeService) {
        this.userJPARepository = userJPARepository;
        this.sectionTypeService = sectionTypeService;
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
        if(optionalUser.isPresent()) {
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
    //TODO
    @Override
    public User saveUser(User user) {
        User savedUser = null;
        if (!isEmail(user.getEmail())) {
            throw new InputInvalidException();
        }
        savedUser = userJPARepository.save(user);
        return savedUser;
    }

    /**
     * {@inheritDoc}
     */
    //TODO
    @Override
    public User createUser(User user) {
        if (!isEmail(user.getEmail())) {
           throw new InputInvalidException();
        }
        user.setActive(true);

        // Find the generic SectionTypes, create a clone for each and save them to the user
        List<SectionType> genericSectionTypes = sectionTypeService.getGenericSectionTypes();
        List<SectionType> newSectionTypes = new ArrayList<>();

        genericSectionTypes.forEach(sectionType -> {
            newSectionTypes.add(sectionType.clone());
        });

        // Set the user's new section types
        user.setSectionTypes(newSectionTypes);

        return userJPARepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    //TODO
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
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }



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
