package com.example.demoproject.jwtsecurity;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {
//	5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437
	private static final String SECRET = "ThisisaSecureDSecretKeytogenerateJWTtokenForAuthenticationandAuthorization";
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String generateJWTToken(String username) {
				
		System.out.println("Fun: generateJWTToken ");
		
		/*claims are the different sections within the token
		 * three different sections are
		 * header: which consists of header value
		 * Pay-load: which consists of user name and the content
		 * Signature: consists of header and pay-load with their base encoder*/
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims,username);
		
	}
	
	 public String extractUsername(String token) {
		 	 
			 System.out.println("Fun: extractUsername ");
	        return extractClaim(token, Claims::getSubject);
	    }

    public Date extractExpiration(String token) {
    	    	 
	    	 System.out.println("Fun: extractExpiration ");
    	
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
         
	    	 System.out.println("Fun: extractClaim ");
    	
    	final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	  private Claims extractAllClaims(String token) {
		  	  
		  System.out.println("Fun: extractAllClaims ");
		  
			Claims val = Jwts.parserBuilder()
					   .setSigningKey(getSignKey())
					   .build()
					   .parseClaimsJws(token)
					   .getBody();
			
			if (val.isEmpty()) {
				System.out.println("AllClaims has Null value");
			}
			
			return val;
		}

	  	private Boolean isTokenExpired(String token) {
	        
	  		return extractExpiration(token).before(new Date());
	  		
	    }
	
	    public Boolean validateToken(String token, UserDetails userDetails) {
	    		
	    	System.out.println("Fun: validateToken ");
	    	
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }


	    public String generateToken(String userName){
	    		    	
	        Map<String,Object> claims=new HashMap<>();
	        return createToken(claims,userName);
	    }

	protected String createToken(Map<String, Object> claims,String username) {
		
		System.out.println("Fun: createToken ");
		
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(username)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				   .signWith(getSignKey(),SignatureAlgorithm.HS256)
				   .compact();
		
				   
	}

	public Key getSignKey() {
			
		System.out.println("Fun: getSignKey ");
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	

}

