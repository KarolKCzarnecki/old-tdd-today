package io.github.karolkczarnecki6.tddtoday.model;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findAll();

    Optional<Project> findById(Integer id);

    Project save(Project entity);


//    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
