//package com.soundy.config;
//
//
//import com.soundy.service.AuthUserService;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//
//import static com.soundy.config.Constants.ADMIN_ROLE;
//import static com.soundy.config.Constants.ARTIST_ROLE;
//import static com.soundy.config.Constants.USER_ROLE;
//
//
////@EnableWebSecurity
////@Configuration
//@Setter
//@AllArgsConstructor
//public class SecurityConfig {
//
//    AuthUserService userService;
//
//
//    @Bean
//    public static AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public static BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(registry ->
//                        // разрешить всем регистрироваться
//                        // однако удалять аккаунты имеют право только админ, либо же сам владелец аккаунта
//                        registry.requestMatchers(HttpMethod.POST, "soundy/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE, "soundy/auth/**").authenticated()
//                                // треки могут удалять только артисты или админ
//                                .requestMatchers(HttpMethod.DELETE, "soundy/tracks/**").hasAnyRole(ARTIST_ROLE, ADMIN_ROLE)
//                                // постить треки только артист
//                                .requestMatchers(HttpMethod.POST, "soundy/tracks/**").hasRole(ARTIST_ROLE)
//                                // создавать плейлисты может только юзер
//                                .requestMatchers(HttpMethod.POST, "soundy/tracks/**").hasRole(USER_ROLE)
//
//                                // вызывать функцию заполнения может только админ
//                                .requestMatchers(HttpMethod.GET, "soundy/load").hasRole(ADMIN_ROLE)
//
//
//                                .anyRequest().permitAll())
//                //возможность авторизоваться
//                .sessionManagement(configurer ->
//                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling(handling -> handling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////        daoAuthenticationProvider.setUserDetailsService(userService);
//        return daoAuthenticationProvider;
//    }
//
//
//}
