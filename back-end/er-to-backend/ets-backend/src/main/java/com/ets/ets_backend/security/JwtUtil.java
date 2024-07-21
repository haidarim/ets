package com.ets.ets_backend.security;

import com.ets.ets_backend.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This {@code @Component} class is responsible to take care of JWT token operations.
 * for example generating token, checking for validation of a token, etc.
 *
 * @author Mehdi haidari
 * */

@Component
public class JwtUtil {

    @Value("${jwt.secret.privateKeyPath}")
    private String privateKeyPath;

    @Value("${jwt.secret.publicKeyPath}")
    private String publicKeyPath;

    private static final long JWT_TOKEN_VALIDITY = 3600*1000; // 1h

    private KeyPair keyPair;

    public JwtUtil(){
        try {
            // Load private and public key bytes from files
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath));

            // Create KeyFactory for RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // Convert bytes to private key using PKCS8EncodedKeySpec
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Convert bytes to public key using X509EncodedKeySpec
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            // Create a KeyPair from the private and public keys
            this.keyPair = new KeyPair(publicKey, privateKey);
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException("Failed to load keys", e);
        }
    }

    /**
     * @param user, User
     * @return String, a newly created JwtToken
     * @throws  IllegalAccessException if the username is invalid i.e. user == null
     * */
    public String generateToken(Client user) throws IllegalAccessException {
        if (user == null)
            throw new IllegalAccessException("invalid username");
        Map<String, Object> claims = new HashMap<>();
        claims.put("exited", user.isExited());
        return createToken(claims, user.getUsername());
    }

    // set up the token properties e.g. ExpirationTime, created tie, ...
    // this method deals with JWT as well
    private String createToken(Map<String, Object> claims, String subject){
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    /**
     * @param token String, which is the token to be checked if expired or not
     * */
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // private method to retrieve the expiration time of the specified token
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // private helper method to get an specific property of the token by passing token as string
    // and the getter of the Claim,
    // Note: the claim object itself can be gotten by the next below method `extractAllClaims`
    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }


    // return the parsed all Claims, using the specified token
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * get the username property, Claim.getSubject()
     * @return String, the username of the token
     * */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * checks if the token i valid
     * @return Boolean
     * */
    public Boolean validateToken(String token, String username){
        final String extractedUsername = extractUsername(token);
        final Boolean userExited = extractClaim(token, claims -> claims.get("exited", Boolean.class));
        return extractedUsername.equals(username)
                && !isTokenExpired(token)
                && !userExited;
    }



    /**
     * get the Private Key
     * */
    public Key  getPrivateKey(){
        return this.keyPair.getPrivate();
    }

    /**
     * get the Public Key
     * */
    public Key getPublicKey(){
        return this.keyPair.getPublic();
    }
}
