package com.westlakefinancial.technology.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT tools
 *
 * @author jiapeng.wu
 */
public class JwtUtils {
    /**
     * Token expiration time, unit: second. This value means 30 days
     */
    private static final long TOKEN_EXPIRED_TIME = 30L * 24L * 60L * 60L * 1000L;

    public static final String jwtId = "tokenId";

    /**
     * jwt encryption and decryption key (you can fill in by yourself)
     */
    private static final String JWT_SECRET = "test";

    private static Key jwtKey;

    static {
        jwtKey = generateKey();
    }

    /**
     * Create JWT
     *
     * @param claims
     * @param time
     * @return
     */
    public static String createJwt(Map<String, Object> claims, Long time) {
        Date now = new Date(System.currentTimeMillis());
        // Time to generate JWT
        long nowMillis = System.currentTimeMillis();
        // The following is to add various standard declarations and private declarations to the payload
        // Here is actually a new Jwt Builder, set the body of jwt
        JwtBuilder builder = Jwts.builder()
                // If there is a private statement, you must first set up the private statement that you created. This is to assign a value to the builder’s claim. Once written in the standard statement assignment, it will overwrite those standard statements.
                .setClaims(claims)
                // Set jti (JWT ID): It is the unique identification of JWT. According to business needs, this can be set to a non-repetitive value, which is mainly used as a one-time token to avoid replay attacks.
                .setId(jwtId)
                // iat: jwt issuance time
                .setIssuedAt(now)
                // Set the signature algorithm used for signature and the secret key used for signature
                .signWith(SignatureAlgorithm.HS512, jwtKey);
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            // Set expiration time
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * Verify jwt
     *
     * @param token
     * @return
     */
    public static Claims verifyJwt(String token) {
        Claims claims;
        try {
            //Get Default Jwt Parser
            claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;

    }

    /**
     * Generate encryption key from string
     *
     * @return
     */
    private static Key generateKey() {
        String key = JWT_SECRET;
        Key signingKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return signingKey;
    }

    /**
     * Generate token based on user Id and openid
     *
     * @param openId
     * @param userId
     * @return
     */
    public static String generateToken(String openId, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("openId", openId);
        map.put("sub", openId);
        return createJwt(map, TOKEN_EXPIRED_TIME);
    }

    /**
     * Parse the token
     *
     * @param token
     * @param salt
     * @return
     */
    public static String parseToken(String token, String salt) {
        String subject = null;
        Claims claims = verifyJwt(token);
        if (claims != null) {
            subject = claims.getSubject();
        }
        return subject;
    }

    /**
     * TODO
     * Can be made into a registration function later
     * The test method is used to generate user passwords and store them in the database, otherwise the security password comparison will be wrong, and the encryption methods are the same
     *
     * @param args
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode("wanghao");
        System.out.println(encodePassword);
    }
}
