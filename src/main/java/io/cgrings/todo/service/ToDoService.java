package io.cgrings.todo.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.cgrings.todo.model.ToDo;

public interface ToDoService {

    Page<ToDo> search(final Pageable pageable);

    ToDo get(final UUID id);

    ToDo create(final ToDo todo);

    ToDo update(final ToDo todo);

    void remove(final UUID id);

}
