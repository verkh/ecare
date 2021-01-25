package com.ecare.services;

import com.ecare.controllers.HomeController;
import com.ecare.dao.ContractDAO;
import com.ecare.dao.IContractDAO;
import com.ecare.dao.IUserDAO;
import com.ecare.dao.UserDAO;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Custom UserDeatilsService which support authentication by email or phone number of user
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Autowired
    protected IUserDAO userDAO;

    @Autowired
    protected IContractDAO contractDAO;

    /**
     * Custom role holder
     * @see GrantedAuthority
     */
    @Getter
    public class Role implements GrantedAuthority {
        private String authority;
        Role(String authority) {
            this.authority = authority;
        }
    }

    /**
     * Custom UserDetails implementation which is constructured from UserPO
     * @see UserPO
     * @see UserDetails
     */
    @Getter
    public class UserPrincipal implements UserDetails {
        private long id;
        private String password;
        private String username;
        private List<Role> authorities;
        private boolean enabled = true;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;

        /**
         * Constructs from UserPO data
         * @param subscriber found user
         */
        UserPrincipal(UserPO subscriber) {
            this.id = subscriber.getId();
            this.username = subscriber.getEmail();
            this.password = subscriber.getPasswordHash();
            this.authorities = Arrays.asList(new Role(subscriber.getAuthority().toString()));
        }
    }

    /**
     * Seeks user info by username which could be email or phonme number
     * @param username email of phone number
     * @return Object of UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Looking for a user with login: " + username);
        UserPO user = null;

        if(username.contains("@")) {
            user = userDAO.findByEmail(username);
        } else {
            ContractPO contract = contractDAO.findByPhone(username);
            if(contract != null)
                user = contract.getUser();
        }
        if(user == null) {
            logger.debug("User hasn't been found");
            throw new UsernameNotFoundException(username);
        }

        logger.debug(String.format("User is found. Name=%s, Lastname=%s, email=%s, phoneNumber=%s",
                user.getName(), user.getLastName(), user.getEmail(), user.getContract().getPhoneNumber()));

        return new UserPrincipal(user);
    }

    /**
     * @return Current user or null
     */
    public UserPO getCurrentUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object authPrincipal = auth.getPrincipal();
            if (authPrincipal == null)
                return null;
            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            return userDAO.get(principal.getId()).orElse(null);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
