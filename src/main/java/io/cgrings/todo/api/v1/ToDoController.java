package io.cgrings.todo.api.v1;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cgrings.todo.api.v1.input.ToDoCreateInput;
import io.cgrings.todo.api.v1.input.ToDoUpdateInput;
import io.cgrings.todo.api.v1.mapper.ToDoMapper;
import io.cgrings.todo.api.v1.output.ToDoOutput;
import io.cgrings.todo.model.ToDo;
import io.cgrings.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public ResponseEntity<Page<ToDo>> search(final Pageable pageable) {
        final Page<ToDo> body = toDoService.search(pageable);
        return new ResponseEntity<Page<ToDo>>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoOutput> create(@RequestBody @Valid ToDoCreateInput dto) {
        final ToDo entity = toDoService.create(ToDoMapper.map(dto));
        return new ResponseEntity<>(ToDoMapper.map(entity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoOutput> get(@PathVariable final UUID id) {
        final ToDo entity = toDoService.get(id);
        return new ResponseEntity<>(ToDoMapper.map(entity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoOutput> update(@RequestBody @Valid ToDoUpdateInput dto, @PathVariable final UUID id) {
        final ToDo entity = toDoService.update(ToDoMapper.map(id, dto));
        return new ResponseEntity<>(ToDoMapper.map(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        toDoService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
