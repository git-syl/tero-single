package com.okfolio.tero.security.config;

import com.okfolio.tero.security.authentication.provider.EmailAuthenticationProvider;
import com.okfolio.tero.security.authentication.provider.PhoneAuthenticationProvider;
import com.okfolio.tero.security.filter.EmailAuthenticationFilter;
import com.okfolio.tero.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.okfolio.tero.security.filter.PhoneAuthenticationFilter;
import com.okfolio.tero.security.userdetails.ITeroUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
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

    private final PasswordEncoder passwordEncoder;
    private final ITeroUserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public WebSecurityConfig(ITeroUserDetailsService userDetailsService,
                             AccessDeniedHandler accessDeniedHandler,
                             AuthenticationFailureHandler authenticationFailureHandler,
                             AuthenticationSuccessHandler authenticationSuccessHandler,
                             PasswordEncoder passwordEncoder,
                             AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf
        http.csrf().disable();

        http.userDetailsService(userDetailsService);

        // grant all preflight request
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll();

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

        // email authentication filter
        http.addFilterAt(
                emailAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        // phone authentication filter
        http.addFilterAt(
                phoneAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
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

    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        var filter = new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }

    public EmailAuthenticationFilter emailAuthenticationFilter() throws Exception {
        var filter = new EmailAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login/email");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }

    public PhoneAuthenticationFilter phoneAuthenticationFilter() throws Exception {
        var filter = new PhoneAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login/phone");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }
}
