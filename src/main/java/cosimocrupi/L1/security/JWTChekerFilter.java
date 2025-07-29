package cosimocrupi.L1.security;

import cosimocrupi.L1.exceptions.UnauthorizedException;
import cosimocrupi.L1.tools.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JWTChekerFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHed = request.getHeader("Authorization");
        if (authHed == null || !authHed.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization nel modo corretto!");
        String accT = authHed.replace("Bearer ", "");
        jwtTools.verifyToken(accT);
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest req){
        return new AntPathMatcher().match("/employee/**", req.getServletPath());
    }
}
