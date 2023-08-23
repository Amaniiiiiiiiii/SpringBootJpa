package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.boot.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

    private final RestTemplate restTemplate;

    @Autowired
    public TaskServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getBaseUrl() {
        return "http://localhost:8080/task"; // Change this URL based on your service endpoint
    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        ParameterizedTypeReference<List<Task>> responseType = new ParameterizedTypeReference<List<Task>>() {};
        ResponseEntity<List<Task>> responseEntity = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, null, responseType);
        return responseEntity;
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        return restTemplate.getForEntity(getBaseUrl() + "/" + id, Task.class);
    }

    @Override
    public ResponseEntity<Task> createTask(Task task) {
        return restTemplate.postForEntity(getBaseUrl(), task, Task.class);
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return restTemplate.exchange(getBaseUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(task), Task.class);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        restTemplate.delete(getBaseUrl() + "/" + id);
        return ResponseEntity.ok().build();
    }
}
