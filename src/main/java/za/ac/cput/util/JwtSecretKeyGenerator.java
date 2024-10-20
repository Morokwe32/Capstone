package za.ac.cput.util;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {
    public static void main(String[] args) {
        // Generate a 256-bit (32-byte) secret key
        byte[] secretKey = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretKey);

        // Encode the secret key in Base64
        String base64SecretKey = Base64.getEncoder().encodeToString(secretKey);
        System.out.println("Generated JWT Secret Key: " + base64SecretKey);
    }
}
