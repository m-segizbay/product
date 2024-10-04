package kz.segizbay.spring_web.configs;

import io.jsonwebtoken.ExpiredJwtException;
import kz.segizbay.spring_web.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final HttpServletRequest httpServletRequest;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer")){
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsername(jwt);
            } catch (ExpiredJwtException e){
                logger.debug("Th token is expired");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
                    jwtTokenUtil.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList());
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }
}
