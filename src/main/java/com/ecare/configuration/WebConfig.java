package com.ecare.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Configures mapping of JSFs for application
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.ecare")
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * @return the resolver for JSPs
     */
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Configures resources mapping
     * @param registry
     */
    // Static Resource Config
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/videos/**").addResourceLocations("/videos/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    /**
     * Configures default auth page
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth").setViewName("SingIn");
    }
}
