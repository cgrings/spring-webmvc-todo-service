package io.cgrings.todo.api.v1.mapper;

import java.util.UUID;

import io.cgrings.todo.api.v1.input.ToDoCreateInput;
import io.cgrings.todo.api.v1.input.ToDoUpdateInput;
import io.cgrings.todo.api.v1.output.ToDoOutput;
import io.cgrings.todo.model.ToDo;

public class ToDoMapper {

    public static ToDo map(final ToDoCreateInput dto) {
        final ToDo entity = new ToDo();
        entity.setName(dto.getName());
        entity.setNotes(dto.getNotes());
        entity.setStart(dto.getStart());
        entity.setDuration(dto.getDuration());
        return entity;
    }

    public static ToDo map(final UUID id, final ToDoUpdateInput dto) {
        final ToDo entity = new ToDo();
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setNotes(dto.getNotes());
        entity.setStart(dto.getStart());
        entity.setDuration(dto.getDuration());
        entity.setDue(dto.getDue());
        entity.setDone(dto.getDone());
        return entity;
    }

    public static ToDoOutput map(final ToDo entity) {
        return ToDoOutput.builder()
        .id(entity.getId())
        .name(entity.getName())
        .notes(entity.getNotes())
        .start(entity.getStart())
        .duration(entity.getDuration())
        .due(entity.getDue())
        .done(entity.getDone())
        .build();
    }

}
