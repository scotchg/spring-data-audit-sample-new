package com.github.scotchg.sample.Repository;

import com.github.scotchg.sample.entity.TodoEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends BaseRepository<TodoEntity, String>{
    Optional<TodoEntity> findOneById(@NonNull String id);

}
