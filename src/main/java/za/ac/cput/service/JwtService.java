package za.ac.cput.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;

@Service
public class JwtService {

    private final String secret = "jxJ/0ud/X8Vj397IMat7uD290LrOJNIxCxnC+6AlRAM=";
    private final Key secretKey;

    public JwtService() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    private static final long JWT_TOKEN_VALIDITY = 10 * 60 * 60; // 10 hours

    // Generate token for customer (username or email) with roles
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // Add roles to claims
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Retrieve username from token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("sub").toString());  // "sub" is the subject
    }

    // Retrieve roles from token
    public List<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, claims -> (List<String>) claims.get("roles")); // Extract roles
    }

    // Extract claims from token
    public <T> T getClaimFromToken(String token, Function<Map<String, Object>, T> claimsResolver) {
        final Map<String, Object> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, claims -> (Date) claims.get("exp"));
        return expiration.before(new Date());
    }
}
