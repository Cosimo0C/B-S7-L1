package cosimocrupi.L1.tools;

import cosimocrupi.L1.entities.Employee;
import cosimocrupi.L1.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Employee employee){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*15))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public void verifyToken(String accT){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accT);
        }catch (Exception e){
            throw new UnauthorizedException("Errore token!");
        }
    }
}
