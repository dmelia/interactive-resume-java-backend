package com.interactiveresume.Interactive.Resume.Backend.services.impl;

import com.interactiveresume.Interactive.Resume.Backend.jpa.UserJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserJPARepository userJPARepository;

    public UserServiceImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public void deactivateUser(User user) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User findByUsername(String input) {
        return null;
    }

    @Override
    public User findByEmail(String input) {
        return null;
    }

    @Override
    public List<User> findUsers() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJPARepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
    }
}
