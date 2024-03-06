package com.example.todoapp.security;

import com.example.todoapp.entity.User;
import com.example.todoapp.security.UserDetailsServiceImpl;
import com.example.todoapp.service.secure.JwtService;
import com.example.todoapp.service.simple.CommentService;
import com.example.todoapp.service.simple.NoteService;
import com.example.todoapp.service.simple.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.regex.Matcher;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String USER_URI = "/user";
    private static final String NOTE_URI = "/note";
    private static final String COMMENT_URI = "/comment";
    private static final String DELETE_METHOD = "DELETE";
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    private final NoteService noteService;
    private final CommentService commentService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            log.warn(StringUtils.isEmpty(authHeader) ? "Header is Empty: " + authHeader : "In correct header: " + authHeader);
            filterChain.doFilter(request, response);
            return;
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        var username = jwtService.parseTokenForResponse(jwt);


        if (StringUtils.isNoneEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.warn(StringUtils.isNoneEmpty(username) ? "Username is empty" : SecurityContextHolder.getContext().getAuthentication() == null ?
                    "Security context is null" : "");

            /*участок для поверки пользователей запрашиваемого и удаляемого
            т.е. Если пользователь с Ником Test1 захочет удалить пользователя с TEst2  то ничего не произойдёт
             */
            String requestURI = request.getRequestURI();
//Действия с пользователем user
            UserDetails userDetailsFromRequestUserController = getUserFromRequest(request, requestURI, username);
            if (userDetailsFromRequestUserController != null &&
                    !jwtService.validToken(username, userDetailsFromRequestUserController)) {
                log.warn("Trying to access the USER");
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                filterChain.doFilter(request, response);
                return;
            }

//Действия с записями note
            UserDetails userDetailsFromRequestNoteController = getNoteFromRequest(request, requestURI, username);
            if (userDetailsFromRequestNoteController != null &&
                    !jwtService.validToken(username, userDetailsFromRequestNoteController)) {
                log.warn("Trying to access the NOTE");
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                response.setContentType("application/json");
                String errorMessage = error406Messages(request);
                response.getWriter().println(errorMessage);
                return;
            }
//Действия с комментариями comment
            UserDetails userDetailsFromRequestCommentController = getCommentFromRequest(request, requestURI, username);
            if (userDetailsFromRequestCommentController != null &&
                    !jwtService.validToken(username, userDetailsFromRequestCommentController)) {
                log.warn("Trying to access the COMMENT");
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                response.setContentType("application/json");
                String errorMessage = error406Messages(request);
                response.getWriter().println(errorMessage);
                return;
            }


            // Если токен валиден, то аутентифицируем пользователя
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validToken(username, userDetails)) {
                log.info("Auth successful.");
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
            log.warn("No Auth");
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Метод ищет пользователя в зависимости от типа запроса и контроллера
     *
     * @param request
     * @return
     */
    private UserDetails getUserFromRequest(HttpServletRequest request, String uri, String username) {
        return uri.startsWith(USER_URI) ? switch (request.getMethod()) {
            case PUT_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/user/update/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case DELETE_METHOD -> {
                String userNameFromRequest1 = uri.replaceAll("/user/delete/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest1);
            }
            default -> null;
        } : null;
    }

    private UserDetails getNoteFromRequest(HttpServletRequest request, String uri, String username) {
        return uri.startsWith(NOTE_URI) ?  switch (request.getMethod()) {
            case POST_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/note/create/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case PUT_METHOD, DELETE_METHOD -> {
                String[] uriArray = uri.split("/");
                String title = uriArray[4].trim();
                String nikName = uriArray[3].trim();
                String authorNote = noteService.findUserByTitle(title).getNikName();
                boolean checkUserName = username.equals(nikName)
                        && username.equals(authorNote);
                yield userDetailsService.loadUserByUsername(checkUserName ? nikName : authorNote);
            }
            default -> null;
        } : null;
    }

    private UserDetails getCommentFromRequest(HttpServletRequest request, String uri, String username) {
        return uri.startsWith(COMMENT_URI) ?
                switch (request.getMethod()) {
            case POST_METHOD -> {
                String userNameFromRequest = uri.replaceAll("/comment/create/", "").trim();
                yield userDetailsService.loadUserByUsername(userNameFromRequest);
            }
            case PUT_METHOD, DELETE_METHOD -> {
                String[] uriArray = uri.split("/");
                String title = uriArray[4].trim();
                String nikName = uriArray[3].trim();
                String authorComment = commentService.findUserByCommentId(Long.valueOf(title)).getNikName();
                boolean checkUserName = username.equals(nikName)
                        && username.equals(authorComment);
                yield userDetailsService.loadUserByUsername(checkUserName ? nikName : authorComment);
            }
            default -> null;
        } : null;
    }


    private String  error406Messages(HttpServletRequest request){
        return String.format("""
                {
                \ttimestamp : %s
                \tstatus : %d
                \terror : %s
                \tpath : %s
                }""", Instant.now(), 406, "Content not acceptable", request.getRequestURI());
    }
}
