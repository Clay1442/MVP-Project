package com.mvpproject.mvp_project.services;

import com.mvpproject.mvp_project.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
   private final UserRepository userRepository;
   public AuthenticationService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails getAuthenticatedUser() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authentication instanceof UserDetails) {
            return (UserDetails) authentication;
        }else {
            throw new RuntimeException("Invalid username or password");
        }
    }

}
