package lt.techin.Running.Club.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(c -> c.disable())
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/events").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/events/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/events").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/api/{eventId}/register").hasRole("USER")
                    .anyRequest().authenticated()
            );
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
