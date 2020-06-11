package com.okfolio.tero.security.config;

import com.okfolio.tero.security.authentication.EmailAuthenticationProvider;
import com.okfolio.tero.security.authentication.PhoneAuthenticationProvider;
import com.okfolio.tero.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.okfolio.tero.security.service.ITeroUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ITeroUserDetailsService userDetailsService;

    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(ITeroUserDetailsService userDetailsService,
                             AccessDeniedHandler accessDeniedHandler,
                             AuthenticationFailureHandler authenticationFailureHandler,
                             AuthenticationSuccessHandler authenticationSuccessHandler,
                             PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf
        http.csrf().disable();

        http.userDetailsService(userDetailsService);

        // grant all preflight request
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()

//        http.authorizeRequests()
//                .anyRequest()
//                .denyAll()

        // access denied handler
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        // json username password authentication filter
        http.addFilterAt(
                jsonUsernamePasswordAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        // passwordEncoder
        provider.setPasswordEncoder(passwordEncoder);
        // username UserDetailsService
        provider.setUserDetailsService(userDetailsService);
        // setHideUserNotFoundExceptions
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    public EmailAuthenticationProvider emailAuthenticationProvider() {
        var provider = new EmailAuthenticationProvider();
        // passwordEncoder
        provider.setPasswordEncoder(passwordEncoder);
        // username UserDetailsService
        provider.setUserDetailsService(userDetailsService);
        // setHideUserNotFoundExceptions
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    public PhoneAuthenticationProvider phoneAuthenticationProvider() {
        var provider = new PhoneAuthenticationProvider();
        // passwordEncoder
        provider.setPasswordEncoder(passwordEncoder);
        // username UserDetailsService
        provider.setUserDetailsService(userDetailsService);
        // setHideUserNotFoundExceptions
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(phoneAuthenticationProvider());
        auth.authenticationProvider(emailAuthenticationProvider());
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        var filter = new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }
}
