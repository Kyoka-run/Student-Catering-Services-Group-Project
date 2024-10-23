package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    private static final String SECRET = "R2fSbTNja+5oMFtVQ2DABbsJNuN9qIsDiD+iDN/7WIuVHScg4f1cAq5yQkRJ1fIfURHwlSzu/9nwcAWJxrqtbQ==";
    public static final int EXPIRATION_TIME = 10 * 24 * 60 * 60; // 10 days
    
    private SecretKey getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
        return Keys.hmacShaKeyFor(keyBytes); 
    }
    
    private Claims extractAllClaims(String token) { 
        return Jwts
                .parser()
                .verifyWith(getSignKey()) 
                .build() 
                .parseSignedClaims(token) 
                .getPayload(); 
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    }
    
    public String extractUsername(String token) { 
        return extractClaim(token, Claims::getSubject); 
    }
  
    public Date extractExpiration(String token) { 
        return extractClaim(token, Claims::getExpiration); 
    }
    
    private Boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date()); 
    }
    
    private String createToken(Map<String, Object> claims, String username) { 
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) 
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) 
                .signWith(getSignKey()).compact(); 
    }
    
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(); 
        return createToken(claims, username); 
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        return (userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token)); 
    }
}