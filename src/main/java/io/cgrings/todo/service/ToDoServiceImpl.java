package io.cgrings.todo.service;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cgrings.todo.exception.TaskNotFoundProblem;
import io.cgrings.todo.model.ToDo;
import io.cgrings.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {
    
    private final ToDoRepository toDoRepository;

    @Override
    public Page<ToDo> search(final Pageable pageable) {
        log.info("Retrieving ToDo list.");
        return toDoRepository.findAll(pageable);
    }

    @Override
    public ToDo get(final UUID id) {
        log.info("Retrieving ToDo id '{}'.", id);
        return toDoRepository.findById(id).orElseThrow(() -> new TaskNotFoundProblem(id));
    }

    @Override
    @Transactional
    public ToDo create(final ToDo todo) {
        final UUID id = Generators.timeBasedGenerator().generate();
        log.info("Creating new ToDo with id '{}'.", id);
        todo.setId(id);
        todo.setDone(Boolean.FALSE);
        return toDoRepository.save(todo);
    }

    @Override
    @Transactional
    public ToDo update(ToDo todo) {
        log.info("Updating ToDo with id '{}'.", todo.getId());
        return toDoRepository.save(todo);
    }

    @Override
    @Transactional
    public void remove(final UUID id) {
        log.info("Removing ToDo with id '{}'.", id);
        toDoRepository.deleteById(id);
    }

}
