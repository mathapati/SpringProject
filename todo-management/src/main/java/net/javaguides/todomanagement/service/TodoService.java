package net.javaguides.todomanagement.service;

import net.javaguides.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

   List<TodoDto>  getAllTodo();

   TodoDto getToDoById(Long id);

   TodoDto updateTodo(Long id , TodoDto todoDto);

   void deleteTodo(Long id);

   TodoDto completeTodo(Long id);

   TodoDto inCompleteTodo(Long id);
}
