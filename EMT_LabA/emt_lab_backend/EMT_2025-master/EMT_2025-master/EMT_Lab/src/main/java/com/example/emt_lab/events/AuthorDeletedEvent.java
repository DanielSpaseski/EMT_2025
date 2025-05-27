package com.example.emt_lab.events;

import com.example.emt_lab.model.domain.Author;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorDeletedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorDeletedEvent(Object source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorDeletedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
