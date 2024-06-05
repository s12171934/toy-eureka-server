package com.solo.toyeurekaserver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${server.username}")
    private String username;

    @Value("${server.password}")
    private String password;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // InMemoryUser의 password를 암호화 하기 위한 메서드

        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() { // ConfigServer 관리자, 서버실행시 한 계정만 생성 후 인메모리저장

        UserDetails user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder().encode(password))
                .roles("ADMIN")
                .build();

        System.out.println("Spring Config Server ADMIN username : " + username + " password : " + password);

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //config server를 호출하는 url에 username과 password를 담아 인증 후 데이터를 가져옴
        http.csrf((auth) -> auth.disable());
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated());

        return http.build();
    }
}
