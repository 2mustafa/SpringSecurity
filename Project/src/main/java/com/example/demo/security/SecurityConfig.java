package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;
@Configuration // Step 1: This annotation marks the class as a configuration class in Spring.
public class SecurityConfig {

    // Step 2: Add support for JDBC with UserDetailsManager
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Define a UserDetailsManager bean for JDBC-based user management.
        // This bean manages user details and roles.
        // It takes a DataSource as a parameter, which is configure in applicationConfig: spring.datasource.*
        return new JdbcUserDetailsManager(dataSource);
    }

    // Step 3: Create a SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> {
            // Step 4: Define access rules for specific URLs with configurer
            // Configure is a lambda expression that takes a HttpSecurity instance as a parameter.

            // Require "EMPLOYEE" role for reading (GET) operations.
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE") //** is for specific employee
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("MANAGER"); //** is for specific employee
        });

        // Step 5: Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults()); // This step sets up HTTP Basic authentication,


        // Step 6: Disable CSRF (no need for stateless REST API with basic CRUD)
        http.csrf(csrf -> csrf.disable()); // This step disables Cross-Site Request Forgery (CSRF) protection.
        // For a stateless REST API, CSRF protection is typically not needed,
        // as it's mainly used for web applications that rely on cookies and sessions for security.

        // Step 7: Return the configured security filter chain.
        return http.build(); // This line returns the configured security filter chain.
        // It finalizes the security configuration and makes it available for the application to use.
    }
}

