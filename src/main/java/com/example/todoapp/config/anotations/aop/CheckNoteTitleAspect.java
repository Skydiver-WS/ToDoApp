package com.example.todoapp.config.anotations.aop;

import com.example.todoapp.repository.NoteRepository;
import com.example.todoapp.web.request.note.CreateNoteRequest;
import com.example.todoapp.web.request.note.UpdateNoteRequest;

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
public class CheckNoteTitleAspect {
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
            if (arg instanceof CreateNoteRequest) {
                title = ((CreateNoteRequest) arg).getTitle();
            } else if (arg instanceof UpdateNoteRequest) {
                title = ((UpdateNoteRequest) arg).getTitle();
            }
            if (title != null && noteRepository.findNoteByTitle(title).isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new Error(HttpStatus.BAD_REQUEST.toString(),
                                String.format("Note %s already exists", title), Instant.now()));
            }
        }
        return pjp.proceed();
    }
}
