package io.cgrings.todo.api.v1.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ToDoCreateInput {

    @NotBlank
    private String name;

    @NotBlank
    private String notes;

    @NotNull
    private LocalDate start;

    @NotNull
    @Positive
    private Integer duration;

}
