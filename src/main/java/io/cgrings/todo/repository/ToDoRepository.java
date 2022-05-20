package io.cgrings.todo.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.cgrings.todo.model.ToDo;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, UUID> {

}
