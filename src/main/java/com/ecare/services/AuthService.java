package com.ecare.services;

import com.ecare.dto.Contract;
import com.ecare.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface of custom UserDeatilsService which support authentication by email or phone number of user\
 * @see UserDetailsService
 */
public interface AuthService extends UserDetailsService {

    /**
     * Seeks user info by username which could be email or phonme number
     * @param username email of phone number
     * @return Object of UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * @return Current user or null
     */
    public Contract getCurrentUser();
}
