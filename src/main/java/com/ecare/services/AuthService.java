package com.ecare.services;

import com.ecare.dao.ContractDAO;
import com.ecare.dao.UserDAO;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("authService")
public class AuthService implements UserDetailsService {

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected ContractDAO contractDAO;

    @Getter
    public class Role implements GrantedAuthority {
        private String authority;
        Role(String authority) {
            this.authority = authority;
        }
    }

    @Getter
    public class UserPrincipal implements UserDetails {
        private String password;
        private String username;
        private List<Role> authorities;
        private boolean enabled;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;

        UserPrincipal(UserPO subscriber) {
            this.username = subscriber.getEmail();
            this.password = subscriber.getPasswordHash();
            this.enabled = subscriber.isEnabled();
            this.authorities = Arrays.asList(new Role(subscriber.getAuthority()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = null;

        if(username.contains("@")) {
            user = userDAO.findByEmail(username);
        } else {
            ContractPO contract = contractDAO.findByPhone(username);
            if(contract != null)
                user = contract.getUser();
        }
        if(user == null)
            throw new UsernameNotFoundException(username);

        return new UserPrincipal(user);
    }
}
