package io.cgrings.todo.service;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cgrings.todo.model.ToDo;
import io.cgrings.todo.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService {
	
	private final Logger logger = LoggerFactory.getLogger(ToDoService.class);

	@Autowired
	private ToDoRepository toDoRepository;

	@Autowired
	private BusinessDateClient businessDateClient;

	@Override
	public Page<ToDo> search(final Pageable pageable) {
		logger.info("Retrieving ToDo list.");
		return toDoRepository.findAll(pageable);
	}

	@Override
	public ToDo get(final String id) {
		logger.info("Retrieving ToDo id '{}'.", id);
		return toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	@Transactional
	public ToDo create(final ToDo todo) {
		final String id = UUID.randomUUID().toString();
		logger.info("Creating new ToDo with id '{}'.", id);
		todo.setId(id);
		updateDueDate(todo);
		return toDoRepository.save(todo);
	}

	@Override
	@Transactional
	public ToDo update(final String id, ToDo todo) {
		logger.info("Updating ToDo with id '{}'.", id);
		updateDueDate(todo);
		return toDoRepository.save(todo);
	}

	@Override
	@Transactional
	public void remove(final String id) {
		logger.info("Removing ToDo with id '{}'.", id);
		toDoRepository.deleteById(id);
	}

	private void updateDueDate(final ToDo todo) {
		final LocalDate due = businessDateClient.endDate(todo.getStart(), todo.getDuration());
		todo.setDue(due);
	}
}
