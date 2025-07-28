package noemibaglieri.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import noemibaglieri.exceptions.UnauthorisedException;
import noemibaglieri.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

     @Autowired
     private JWTTools jwtTools;

     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String authHeader = request.getHeader("Authorization");
         if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorisedException("Insert right format token");

         String accessToken = authHeader.replace("Bearer ", "");

         jwtTools.verifyToken(accessToken);

         filterChain.doFilter(request, response);
     }

     @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
         return new AntPathMatcher().match("/auth/**", request.getServletPath());
     }
}
