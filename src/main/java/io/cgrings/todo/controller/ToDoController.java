package io.cgrings.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cgrings.todo.model.ToDo;
import io.cgrings.todo.service.ToDoService;

@RestController
@CrossOrigin
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@GetMapping("/todos")
	public ResponseEntity<Page<ToDo>> search(final Pageable pageable) {
		final Page<ToDo> body = toDoService.search(pageable);
		return new ResponseEntity<Page<ToDo>>(body, HttpStatus.OK);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<ToDo> get(@PathVariable final String id) {
		final ToDo body = toDoService.get(id);
		return new ResponseEntity<ToDo>(body, HttpStatus.OK);
	}

	@PostMapping("/todos")
	public ResponseEntity<ToDo> create(@RequestBody ToDo todo) {
		final ToDo body = toDoService.create(todo);
		return new ResponseEntity<ToDo>(body, HttpStatus.CREATED);
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<ToDo> update(@RequestBody ToDo todo, @PathVariable final String id) {
		final ToDo body = toDoService.update(id, todo);
		return new ResponseEntity<ToDo>(body, HttpStatus.OK);
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> delete(@PathVariable final String id) {
		toDoService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
