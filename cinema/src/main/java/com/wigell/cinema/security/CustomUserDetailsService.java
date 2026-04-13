package com.wigell.cinema.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.AppUser;
import com.wigell.cinema.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new User(
            appUser.getUsername(),
            appUser.getPassword(),
            List.of(new SimpleGrantedAuthority(appUser.getRole()))
        );

    }
}
