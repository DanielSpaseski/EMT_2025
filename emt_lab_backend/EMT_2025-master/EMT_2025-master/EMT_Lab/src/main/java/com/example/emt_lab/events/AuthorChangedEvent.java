package com.example.emt_lab.events;

import com.example.emt_lab.model.domain.Author;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorChangedEvent(Author source){
        super(source);
        this.when = LocalDateTime.now();
    }
    public AuthorChangedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
