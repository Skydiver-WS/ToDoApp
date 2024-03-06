package com.example.todoapp.config.anotations.aop;

import com.example.todoapp.repository.NoteRepository;
import com.example.todoapp.web.request.comment.CommentRequest;
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
public class CheckCommentAspect {
    private final NoteRepository noteRepository;

    @Around("@annotation(com.example.todoapp.config.anotations.aop.Check)")
    @SneakyThrows
    public Object checkTitle(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            String title = null;
            if (arg instanceof CommentRequest) {
                title = ((CommentRequest) arg).getTitleNote();
            }
            if (title != null && noteRepository.findNoteByTitle(title).isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Error(HttpStatus.NOT_FOUND.toString(),
                                String.format("Note %s not found", title), Instant.now()));
            }
        }
        return pjp.proceed();
    }
}
