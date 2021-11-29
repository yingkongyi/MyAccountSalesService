package com.westlakefinancial.technology.config;

import com.westlakefinancial.technology.filter.JwtAuthenticationTokenFilter;
import com.westlakefinancial.technology.security.*;
import com.westlakefinancial.technology.service.impl.SelfUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * security configuration class
 *
 * @author jiapeng.wu
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AjaxAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AjaxAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private SelfUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Add custom safety certification
        // auth.authenticationProvider(provider);

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Static resource path required by swagger
        http.authorizeRequests().antMatchers( "/swagger-ui.html",
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/webjars/**",
                "/error").permitAll();
        http
            // Remove CSRF
            .csrf().disable()
            // Use JWT, close the token
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and()
            // Filter all Options requests
            //.authorizeRequests().antMatchers("/**").permitAll()
            .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // Any request can be accessed after logging in
            .anyRequest()
            // RBAC dynamic URL authentication
            .access("@rbacauthorityservice.hasPermission(request, authentication)")
            .and()
            // Turn on login
            .formLogin()
            // Log in successfully processor
            .successHandler(authenticationSuccessHandler)
            // Logon failure handler
            .failureHandler(authenticationFailureHandler)
            .permitAll()
            // The default logout behavior is logout
            .and().logout().logoutUrl("/logout")
            // Logout processor
            .logoutSuccessHandler(logoutSuccessHandler)
            .permitAll();

        // remember me
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(1000);
        // No access to the processor
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
