//package com.dangtai.backend.filter;
//
//import com.dangtai.backend.common.CommonResource;
//import com.dangtai.backend.dto.UserPrincipal;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private CommonResource commonResource;
//
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
//                                   CommonResource commonResource
//    ) {
//        this.authenticationManager = authenticationManager;
//        this.commonResource = commonResource;
//
//        setFilterProcessesUrl("/api/services/controller/user/login");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(
//            HttpServletRequest req,
//            HttpServletResponse res
//    ) throws AuthenticationException {
//        try {
//            User creds = new ObjectMapper()
//                    .readValue(req.getInputStream(), User.class);
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            creds.getUsername(),
//                            creds.getPassword(),
//                            new ArrayList<>())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            FilterChain chain,
//                                            Authentication auth
//    ) throws IOException {
//        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
//        Date expiryDate = new Date((new Date()).getTime() + commonResource.getJwtExpiration());
//
//        String token = Jwts.builder()
//                .setSubject(Long.toString(userPrincipal.getId()))
//                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, commonResource.getJwtSecret())
//                .compact();
//
//        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;
//
//        res.getWriter().write(body);
//        res.getWriter().flush();
//    }
//}
