package io.cgrings.todo.exception;

import java.net.URI;
import java.util.UUID;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class TaskNotFoundProblem extends AbstractThrowableProblem {

    private static final URI TYPE
      = URI.create("https://cgrings.io/problem/not-found");

    public TaskNotFoundProblem(final UUID taskId) {
        super(
          TYPE,
          "Not found",
          Status.NOT_FOUND,
          String.format("Task '%s' not found", taskId));
    }

}
