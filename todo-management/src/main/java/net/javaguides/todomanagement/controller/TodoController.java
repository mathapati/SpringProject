package net.javaguides.todomanagement.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.dto.TodoDto;
import net.javaguides.todomanagement.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoByID(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(todoService.getToDoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable(value = "id") Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo with id -> "+ id +"  deleted successfully",HttpStatus.OK);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> completedTodo(@PathVariable(value = "id") Long id){
     return new ResponseEntity<>(todoService.completeTodo(id),HttpStatus.OK);
    }
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<?> inCompleted(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(todoService.inCompleteTodo(id),HttpStatus.OK);
    }

}
