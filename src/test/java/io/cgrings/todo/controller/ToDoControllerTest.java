package io.cgrings.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.cgrings.todo.model.ToDo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ToDoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	// TODO Error when try to serialize Page
	public void testList() {
		final ParameterizedTypeReference<Page<ToDo>> typeRef = new ParameterizedTypeReference<Page<ToDo>>() {};
		final ResponseEntity<Page<ToDo>> todos = restTemplate.exchange("/todos", HttpMethod.GET, null, typeRef);
		assertEquals(3, todos.getBody().getTotalElements());
	}

	@Test
	public void testGet() {
		final ResponseEntity<ToDo> todo = restTemplate.getForEntity("/todos/{id}", ToDo.class, "5dd3c6ca-8e80-4a41-9efc-59f03a1e5dbf");
		assertEquals(4, todo.getBody().getDuration().intValue());
	}

}
