package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.security.UserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter { 
	
	public static final String tokenTag = "SESSION";
  
    @Autowired
    private JwtTokenUtil jwtTokenUtil; 
  
    @Autowired
    private UserDetailsService userDetailsService; 
    
    private static Optional<String> readCookie(HttpServletRequest request, String key) {
    	Cookie[] cookies = request.getCookies();
    	if (cookies == null || cookies.length == 0) return Optional.empty();
        return Arrays.stream(cookies)
			.filter(c -> key.equals(c.getName()))
			.map(Cookie::getValue)
			.findAny();
    }
  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
        Optional<String> cookie = readCookie(request, tokenTag);
        if (cookie.isPresent()) {
        	try {
	        	String token = cookie.get();
		        String username = jwtTokenUtil.extractUsername(token);
		        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
		            UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
		            if (jwtTokenUtil.validateToken(token, userDetails)) { 
		                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
		                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
		                SecurityContextHolder.getContext().setAuthentication(authToken); 
		            }
		        }
        	} catch (ExpiredJwtException e) {
        		//Do nothing to return 401 Unautorized. The front-end code will redirect user to login page
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		System.out.println(e.getStackTrace());
        	}
        }
        filterChain.doFilter(request, response); 
    }
}
