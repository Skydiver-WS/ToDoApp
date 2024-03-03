package com.example.todoapp.config.anotations.aop;

import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.request.user.UpdateUserRequest;
import com.example.todoapp.web.response.error.Error;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckUserAspect {

    private final UserRepository userRepository;

    @Around("@annotation(com.example.todoapp.config.anotations.aop.Check))")
    @SneakyThrows
    public Object checkCreateNikName(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            String user = null;
            if (arg instanceof CreateUserRequest) {
                user = ((CreateUserRequest) arg).getNikName();
            } else if (arg instanceof UpdateUserRequest) {
                user = (((UpdateUserRequest) arg).getNikName());
            }
            if (user != null && userRepository.findUserByNikName(user).isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new Error(HttpStatus.BAD_REQUEST.toString(),
                                String.format("Nik %s already exists", user), Instant.now()));
            }
        }
        return pjp.proceed();
    }
}


