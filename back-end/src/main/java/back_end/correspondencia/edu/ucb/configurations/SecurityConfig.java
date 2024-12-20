package back_end.correspondencia.edu.ucb.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EndPoints publicos
                    http.requestMatchers("/**").permitAll();
                    //http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // EndPoints Privados
//                    http.requestMatchers(HttpMethod.GET, "/method/get").hasAuthority("READ");
//                    http.requestMatchers(HttpMethod.POST, "/method/post").hasAuthority("CREATE");
//                    http.requestMatchers(HttpMethod.DELETE, "/method/delete").hasAuthority("DELETE");
//                    http.requestMatchers(HttpMethod.PUT, "/method/put").hasAuthority("UPDATE");

                    http.anyRequest().denyAll();
                })
                .build();
    }
}
