package io.cgrings.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.cgrings.todo.model.ToDo;

public interface ToDoService {

	Page<ToDo> search(final Pageable pageable);

	ToDo get(final String id);

	ToDo create(final ToDo todo);

	ToDo update(final String id, final ToDo todo);

	void remove(final String id);

}
