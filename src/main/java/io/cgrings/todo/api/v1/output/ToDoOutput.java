package io.cgrings.todo.api.v1.output;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ToDoOutput {

    private UUID id;
    private String name;
    private String notes;
    private LocalDate start;
    private Integer duration;
    private LocalDate due;
    private Boolean done;

}
