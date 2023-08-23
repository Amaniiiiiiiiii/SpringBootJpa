package demo.boot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.boot.model.Task;

public interface TaskService {

	 public ResponseEntity<List<Task>> getAllTasks();

    ResponseEntity<Task> getTaskById(Long id);

    ResponseEntity<Task> createTask(Task task);

    ResponseEntity<Task> updateTask(Long id, Task task);

    ResponseEntity<Void> deleteTask(Long id);
}
