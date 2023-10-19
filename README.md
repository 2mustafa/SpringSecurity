1.  Create Two Tables: Users and Authorities
    To implement user authentication and authorization, it's essential to have two tables: Users and Authorities in your database. These tables are used by Spring Security to manage user details and their roles.
    The Users table will store user information such as username and password.
    The Authorities table will store information about user roles, which will be linked to users in the Users table.

2.  Setting Up a Spring Boot Project
    Start by creating a new Spring Boot project. You can use the Spring Initializer or your preferred development environment.
    In your project's pom.xml file, inject the spring-boot-starter-security dependency. This dependency provides the necessary libraries for implementing security in your Spring Boot application.

3.  Create a New Security Package
    To keep your security-related classes organized, create a new package within your project. You can name it "security" or choose a name that suits your project's structure.

4.  Create a SecurityConfig Class
    In your security package, create a new Java class named SecurityConfig. This class will be responsible for configuring Spring Security for your application.

5.  Annotate SecurityConfig with @Configuration
    To ensure that Spring recognizes SecurityConfig as a configuration class, annotate it with @Configuration.

6.  Configure Security
    Inside the SecurityConfig class, configure your security settings. This typically involves defining how different endpoints should be secured. You can use the HttpSecurity object to define rules for access to specific URLs.
    For example, in your securityFilterChain method, you can use the authorizeHttpRequests method to specify which roles are required for accessing certain URLs. This ensures that only authorized users can access certain parts of your application:

                 Step 1: Annotation
                 Use @Configuration to mark the class as a configuration class in Spring.

                 Step 2: UserDetailsManager Bean
                 Define a UserDetailsManager bean to manage user details and roles.
                 Requires a DataSource for JDBC-based user management.

                 Step 3: SecurityFilterChain Bean
                 Create a SecurityFilterChain bean to configure security for the application.

                 Step 4: Access Rules
                 Define access rules for specific URLs.
                 Use HttpSecurity to set up access control rules.
                 Specify required roles for various HTTP methods on specific endpoints.

                 Step 5: Role-Based Access - Employee
                 Use hasRole("EMPLOYEE") to specify that the "EMPLOYEE" role is required for reading (GET) operations.
                 Apply these rules to /api/employees and its subpaths.

                 Step 6: Role-Based Access - Manager
                 Use hasRole("MANAGER") to specify that the "MANAGER" role is required for creating, updating, and deleting (POST, PUT, DELETE) operations on employee data.
                 Apply these rules to /api/employees and its subpaths.

                 Step 7: HTTP Basic Authentication
                 Enable HTTP Basic authentication.
                 Users must provide a valid username and password to access secured endpoints.

                 tep 8: CSRF Protection
                 Disable Cross-Site Request Forgery (CSRF) protection.
                 Typically not needed for stateless REST APIs.

                 Step 9: Finalization
                 Return the configured security filter chain.
                 Finalizes the security configuration for the application to use.
