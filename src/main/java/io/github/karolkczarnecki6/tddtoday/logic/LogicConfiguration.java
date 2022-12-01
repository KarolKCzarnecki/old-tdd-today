package io.github.karolkczarnecki6.tddtoday.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.karolkczarnecki6.tddtoday.TaskConfigurationProperties;
import io.github.karolkczarnecki6.tddtoday.model.ProjectRepository;
import io.github.karolkczarnecki6.tddtoday.model.TaskGroupRepository;
import io.github.karolkczarnecki6.tddtoday.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService service(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,


             final TaskConfigurationProperties config
    ) {
        return new ProjectService(repository, taskGroupRepository,config );
    }

    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            final TaskRepository taskRepository

    ) {
        return new TaskGroupService(taskGroupRepository,taskRepository);
    }
}
