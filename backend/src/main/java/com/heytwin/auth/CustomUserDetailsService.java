package com.heytwin.auth;

import com.heytwin.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username).map(UserPrincipal::of).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @java.lang.SuppressWarnings("all")
    
    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
