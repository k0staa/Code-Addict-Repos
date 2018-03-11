package com.example.permissionevaluatorboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Bean
    public UserDetailsService userDetailsService() {
        // Spring Boot 2 default PasswordEncoder is built as a DelegatingPasswordEncoder. Using
        // {noop} will force DelegatingPasswordEncoder to use NoOpPasswordEncoder
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user1").password("{noop}pass").roles("USER")
                .build());
        manager.createUser(User.withUsername("user2").password("{noop}pass").roles("USER").build());
        return manager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);
        web.expressionHandler(handler);
    }

}