package com.oktfolio.tero.security.config;

import com.oktfolio.tero.security.authentication.provider.EmailAuthenticationProvider;
import com.oktfolio.tero.security.authentication.provider.PhoneAuthenticationProvider;
import com.oktfolio.tero.security.filter.*;
import com.oktfolio.tero.security.userdetails.ITeroUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsUtils;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/19
 */
@Configuration
public class MultiWebSecurityConfig {

    @Configuration
    @Order(Ordered.LOWEST_PRECEDENCE - 100)
    @EnableRedisHttpSession
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;
        private final ITeroUserDetailsService userDetailsService;
        private final AccessDeniedHandler accessDeniedHandler;
        private final AuthenticationFailureHandler authenticationFailureHandler;
        private final AuthenticationSuccessHandler authenticationSuccessHandler;
        private final LogoutSuccessHandler logoutSuccessHandler;
        private final AuthenticationEntryPoint authenticationEntryPoint;
        private final SessionAuthenticationStrategy sessionAuthenticationStrategy;

        public WebSecurityConfig(ITeroUserDetailsService userDetailsService,
                                 AccessDeniedHandler accessDeniedHandler,
                                 AuthenticationFailureHandler authenticationFailureHandler,
                                 AuthenticationSuccessHandler authenticationSuccessHandler,
                                 PasswordEncoder passwordEncoder,
                                 LogoutSuccessHandler logoutSuccessHandler,
                                 AuthenticationEntryPoint authenticationEntryPoint,
                                 SessionAuthenticationStrategy sessionAuthenticationStrategy) {
            this.userDetailsService = userDetailsService;
            this.accessDeniedHandler = accessDeniedHandler;
            this.authenticationFailureHandler = authenticationFailureHandler;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
            this.passwordEncoder = passwordEncoder;
            this.logoutSuccessHandler = logoutSuccessHandler;
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.sessionAuthenticationStrategy = sessionAuthenticationStrategy;
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

            // http.authorizeRequests()
            //         .anyRequest()
            //         .authenticated();

            // http.oauth2Login();

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

            // json authentication filter
            http.addFilterAt(
                    jsonEmailAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // email authentication filter
            http.addFilterAt(
                    emailAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // json phone authentication filter
            http.addFilterAt(
                    jsonPhoneAuthenticationFilter(),
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

            http.logout()
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .invalidateHttpSession(true);
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
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public JsonEmailAuthenticationFilter jsonEmailAuthenticationFilter() throws Exception {
            var filter = new JsonEmailAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/email");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public EmailAuthenticationFilter emailAuthenticationFilter() throws Exception {
            var filter = new EmailAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/email");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public JsonPhoneAuthenticationFilter jsonPhoneAuthenticationFilter() throws Exception {
            var filter = new JsonPhoneAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/phone");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public PhoneAuthenticationFilter phoneAuthenticationFilter() throws Exception {
            var filter = new PhoneAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/phone");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }
    }
}
