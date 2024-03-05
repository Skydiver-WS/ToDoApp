package com.example.todoapp.security;

import com.example.todoapp.security.UserDetailsServiceImpl;
import com.example.todoapp.service.secure.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        var username = jwtService.parseTokenForResponse(jwt);


        if (StringUtils.isNoneEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

            /*участок для поверки пользователей запрашиваемого и удаляемого
            т.е. Если пользователь с Ником Test1 захочет удалить пользователя с TEst2  то ничего не произойдёт
             */

//            UserDetails userDetailsFromRequestUserController = getUserFromRequest(request);
//            if (userDetailsFromRequestUserController != null &&
//                    !jwtService.validToken(username, userDetailsFromRequestUserController)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//
            UserDetails userDetailsFromRequestNoteController = getNoteFromRequest(request);
            if (userDetailsFromRequestNoteController != null &&
                    !jwtService.validToken(username, userDetailsFromRequestNoteController)) {
                filterChain.doFilter(request, response);
                return;
            }

//            UserDetails userDetailsFromRequestCommentController = getCommentFromRequest(request);
//            if (userDetailsFromRequestCommentController != null &&
//                    !jwtService.validToken(username, userDetailsFromRequestCommentController)) {
//                filterChain.doFilter(request, response);
//                return;
//            }


            // Если токен валиден, то аутентифицируем пользователя
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validToken(username, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Метод ищет пользователя в зависимости от типа запроса
     * @param request
     * @return
     */
    private UserDetails getUserFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return switch (request.getMethod()) {
            case PUT_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/user/update/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case DELETE_METHOD -> {
                String userNameFromRequest1 = uri.replaceAll("/user/delete/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest1);
            }
            default -> null;
        };
    }

    private UserDetails getNoteFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return switch (request.getMethod()) {
            case POST_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/note/create/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case PUT_METHOD, DELETE_METHOD -> {
                String[] uriArray = uri.split("/");
                String nikName = uriArray[3].trim();
                yield userDetailsService.loadUserByUsername(nikName);
            }
            default -> null;
        };
    }

    private UserDetails getCommentFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return switch (request.getMethod()) {
            case POST_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/comment/create/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case PUT_METHOD, DELETE_METHOD -> {
                String[] uriArray = uri.split("/");
                String nikName = uriArray[3].trim();
                yield userDetailsService.loadUserByUsername(nikName);
            }
            default -> null;
        };
    }
}
