package io.cgrings.todo.api.v1.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ToDoUpdateInput {

    @NotBlank
    private String name;

    @NotBlank
    private String notes;

    @NotNull
    private LocalDate start;

    @Positive
    private Integer duration;

    @PastOrPresent
    private LocalDate due;

    private Boolean done;

}
