package com.example.application.backEnd.service.impl.security;

import com.example.application.backEnd.reporitory.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UsersDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOpt = userRepository.findFirstByUsername(username);
        return userOpt.map(user -> new User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        )).orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));
    }
}
