package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.Ee417GroupKAssignmentApplication;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jwt.JwtAuthFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthFilter authFilter;

	public static final class BearerTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException e) throws IOException, ServletException {
        	//Because Jwt token is stateless, just tell browser to delete cookie
	        Cookie cookie = new Cookie(JwtAuthFilter.tokenTag, "");
	        cookie.setPath("/");
	        cookie.setMaxAge(0);
	        response.addCookie(cookie);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "{\"success\":0,\"error\":\"" + e.getMessage() + "\"}");
		}
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors(cors -> cors.configurationSource(request -> {
        	CorsConfiguration config = new CorsConfiguration();
        	config.applyPermitDefaultValues();
        	config.setAllowedOrigins(Arrays.asList(Ee417GroupKAssignmentApplication.corsAllowedUrl));
            return config;
        }))
        .authorizeHttpRequests(requests -> requests
        		.requestMatchers("/user").hasAuthority("admin")
        		.requestMatchers("/user/logout").authenticated()
        		.requestMatchers("/item_update").authenticated()
        		.requestMatchers("/item_delete").authenticated()
        		.requestMatchers("/queue_add").authenticated()
        		.requestMatchers("/queue_update").authenticated()
        		.requestMatchers("/queue_delete").authenticated()
        		.requestMatchers("/section_update").authenticated()
        		.requestMatchers("/section_delete").authenticated()
        		.requestMatchers("/store_occup_report").authenticated()
        		.requestMatchers("/html/dashboard.html").authenticated()
        		.anyRequest().permitAll())
        .sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        .authenticationProvider(authenticationProvider())
        .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint((request, response, exception) -> {
        	BearerTokenAuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
            delegate.commence(request, response, exception);
        }))
        .csrf(requests -> requests.disable()) //temporarily disabling csrf check
        .headers(headers -> headers.frameOptions(options -> options.sameOrigin()));
		return http.build();
	}
    
    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
