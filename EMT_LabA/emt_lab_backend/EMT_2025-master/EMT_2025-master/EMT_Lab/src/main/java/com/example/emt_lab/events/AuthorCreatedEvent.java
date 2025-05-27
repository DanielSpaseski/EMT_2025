package com.example.emt_lab.events;

import com.example.emt_lab.model.domain.Author;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorCreatedEvent extends ApplicationEvent {

    private final LocalDateTime when;

    public AuthorCreatedEvent(Author source){
        super(source);
        this.when = LocalDateTime.now();
    }
    public AuthorCreatedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
