package net.javaguides.todomanagement.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.dto.TodoDto;
import net.javaguides.todomanagement.entity.Todo;
import net.javaguides.todomanagement.exception.ResourceNotFoundException;
import net.javaguides.todomanagement.repository.TodoRepository;
import net.javaguides.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = mapToEntity(todoDto);
        Todo Todosaved = todoRepository.save(todo);
        return mapToDto(Todosaved);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> mapToDto(todo)).collect(Collectors.toList());
    }

    @Override
    public TodoDto getToDoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with Id :" + id + " not found"));
        return mapToDto(todo);
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with Id :" + id + " not found"));
        BeanUtils.copyProperties(todoDto, todo, "id");
        return mapToDto(todoRepository.save(todo));
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with Id :" + id + " not found"));
        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with Id :" + id + " not found"));
        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo = todoRepository.save(todo);
        return mapToDto(updateTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with Id :" + id + " not found"));
        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = todoRepository.save(todo);
        return mapToDto(updateTodo);
    }

    //convert todoDto in entity
    private TodoDto mapToDto(Todo todo) {
        return modelMapper.map(todo, TodoDto.class);
    }

    private Todo mapToEntity(TodoDto todoDto) {
        return modelMapper.map(todoDto, Todo.class);
    }
}
