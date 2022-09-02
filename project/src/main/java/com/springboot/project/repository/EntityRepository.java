package com.springboot.project.repository;

import com.springboot.project.model.DatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityRepository<T extends DatabaseEntity> extends JpaRepository<T, Long> {

    default T get(Long id) {
        Optional<T> optionalEntity = findById(id);
        return optionalEntity.orElse(null);
    }

    default boolean exists(Long id) {
        return get(id) != null;
    }

}
