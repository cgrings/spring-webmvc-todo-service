package io.cgrings.todo.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Entity
@Table(name = "TODO")
public class ToDo {

    @Id
    @Column(name = "id_todo")
    private UUID id;

    @NotBlank
    @Column(name = "nm_todo")
    private String name;

    @NotBlank
    @Column(name = "tx_notes")
    private String notes;

    @NotNull
    @Column(name = "dt_start")
    private LocalDate start;

    @NotNull
    @Positive
    @Column(name = "nr_duration")
    private Integer duration;

    @PastOrPresent
    @Column(name = "dt_due")
    private LocalDate due;

    @NotNull
    @Column(name = "bl_done")
    private Boolean done;

}
