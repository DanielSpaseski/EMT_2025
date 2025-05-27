package com.example.emt_lab.listeners;

import com.example.emt_lab.events.AuthorChangedEvent;
import com.example.emt_lab.events.AuthorCreatedEvent;
import com.example.emt_lab.events.AuthorDeletedEvent;
import com.example.emt_lab.service.application.CountryApplicationService;
import com.example.emt_lab.service.domain.AuthorService;
import com.example.emt_lab.service.domain.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandlers(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event){
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event){
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event){
        this.countryApplicationService.refreshMaterializedView();
    }
}
