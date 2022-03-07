//package io.security.corespringsecurity.security.configs;
//
//import io.security.corespringsecurity.security.common.FormAuthenticationDetailsSource;
//import io.security.corespringsecurity.security.handler.FormAccessDeniedHandler;
//import io.security.corespringsecurity.security.provider.FormAuthenticationProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//
//@Configuration
//@EnableWebSecurity
//@Order(1) // 실행순서
//@Slf4j
//public class SecurityConfigBak extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler customAuthenticationFailureHandler;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private FormAuthenticationDetailsSource authenticationDetailsSource;
//
////    메모리 방식 사용안함
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        String password = passwordEncoder().encode("1111");
////        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
////        auth.inMemoryAuthentication().withUser("manager").password(password).roles("USER","MANAGER");
////        auth.inMemoryAuthentication().withUser("admin").password(password).roles("USER","MANAGER","ADMIN");
////    }
//
//    /**
//     * 인증처리
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService); // 사용안함
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // permitAll과 흡사 다만 보안 필터 영역 밖
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/users", "user/login/**", "/login*").permitAll()
//                .antMatchers("/mypage").hasRole("USER")
//                .antMatchers("/messages").hasRole("MANAGER")
//                .antMatchers("/config").hasRole("ADMIN")
//                .anyRequest().authenticated()
//
//        .and()
//                .formLogin()
//                // 로그인 페이지
//                .loginPage("/login")
//                .loginProcessingUrl("/login_proc")
////                .defaultSuccessUrl("/") // 성공여부
//                .authenticationDetailsSource(authenticationDetailsSource)
//                .successHandler(customAuthenticationSuccessHandler)
//                .failureHandler(customAuthenticationFailureHandler)
//                .permitAll()// 모든 사람이 접근 가능
//        .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                .accessDeniedPage("/denied")
//                .accessDeniedHandler(accessDeniedHandler());
//
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        FormAccessDeniedHandler commonAccessDeniedHandler = new FormAccessDeniedHandler();
//        commonAccessDeniedHandler.setErrorPage("/denied");
//        return commonAccessDeniedHandler;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new FormAuthenticationProvider();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}