package com.example.emt_lab.jobs;

import com.example.emt_lab.service.application.AuthorApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AuthorApplicationService authorApplicationService;
    public ScheduledTasks(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.authorApplicationService.refreshMaterializedView();
    }
}
