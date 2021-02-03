package com.ecare.configuration;

import com.ecare.services.AuthService;
import com.ecare.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Security config handles configuration of Authentication and authorities
 */
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity
@ComponentScan("com.ecare")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures security policies
     * @param http namespace security configuration
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(csrfTokenRepository());
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/administration/users").hasAnyRole("DICTATOR", "ADMIN")
                .antMatchers("/administration/tariffs/**").hasAnyRole("ADMIN")
                .antMatchers("/administration/options/**").hasAnyRole("ADMIN")
                .antMatchers("/administration/contracts/**").hasAnyRole("ADMIN")
                .antMatchers("/profile").authenticated()
                .antMatchers("/contract").authenticated()
                //.antMatchers("/Plans/**").hasRole("ADMIN")
                .and()
                .anonymous()
                .and()
                .formLogin().loginPage("/auth")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    /**
     * Setups roles hiearchy in application
     * @return
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER" +
                "ROLE_DICTATOR > ROLE_USER" +
                "ROLE_ADMIN > ROLE_DICTATOR");
        return hierarchy;
    }

    /**
     * @return default encoder for the application
     */
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    /**
     * @return custom application service
     */
    @Bean
    public AuthService authService(){
        return new AuthServiceImpl();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthService authService;

    /**
     * Setups service and password encoder for security
     * @param auth authentication manger builder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource);
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    /**
     * @return CSRF token for JSP pages
     */
    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
