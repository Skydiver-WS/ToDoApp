package com.example.todoapp.service.secure;

import com.example.todoapp.security.AppUserPrincipal;
import com.example.todoapp.service.simple.UserService;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateJwtToken(AppUserPrincipal userPrincipal);
    String parseTokenForResponse(String token);
    boolean validToken(String token, UserDetails userDetails);

}
