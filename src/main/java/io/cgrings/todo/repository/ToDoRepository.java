package io.cgrings.todo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.cgrings.todo.model.ToDo;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, String> {

}
