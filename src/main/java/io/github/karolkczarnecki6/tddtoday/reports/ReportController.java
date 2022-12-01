package io.github.karolkczarnecki6.tddtoday.reports;


import io.github.karolkczarnecki6.tddtoday.model.Task;
import io.github.karolkczarnecki6.tddtoday.model.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("/reports")
class ReportController{
    private TaskRepository taskRepository;
    private PersistedTaskEventRepository eventRepository;

    ReportController(final  TaskRepository taskRepository, final PersistedTaskEventRepository eventRepository ){
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
    }



    @GetMapping("/count/{id}")
    ResponseEntity<TaskWithChangesCount>readTaskWithCount(@PathVariable int id){
        return taskRepository.findById(id)
                .map(task -> new TaskWithChangesCount(task, eventRepository.findByTaskId(id)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    private static class TaskWithChangesCount{
        public String description;
        public boolean done;
        public int changesCount;

        TaskWithChangesCount(final Task task, final List<PersistedTaskEvent> events ){
            description = task.getDescription();
            done = task.isDone();
            changesCount = events.size();
        }
    }

}