package com.example.proxy.rest.handler;

import com.example.proxy.model.User;
import com.example.proxy.security.jwt.JwtResponse;
import com.example.proxy.security.jwt.JwtUtil;
import com.example.proxy.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class AuthHandler {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserService userService;


    public ResponseEntity<?> createToken(User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Error", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new JwtResponse(token));
    }

    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
