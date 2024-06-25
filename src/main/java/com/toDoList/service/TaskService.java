package com.toDoList.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toDoList.model.Task;
import com.toDoList.repository.TaskRepository;

@Service
public class TaskService {

	private Logger logger = Logger.getLogger(TaskService.class.getName());

	@Autowired
	TaskRepository repository;

	public List<Task> findAll() {
		logger.info("Finding all tasks");
		return repository.findAll();
	}

	public Task findById(Long id) {
		logger.info("Finding one task!");
		return repository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID")); //ResourceNotFoundException
	}

	public Task create(Task task) {
		logger.info("Creating one task!");
		
		return repository.save(task);
	}

	public Task update(Task task) {
		logger.info("Updating one task");
		Task entity = repository.findById(task.getId())
				.orElseThrow(() -> new RuntimeException("No records found for this id"));//ResourceNotFoundException

		entity.setDescription(task.getDescription());
		entity.setDueDate(task.getDueDate());
		entity.setCompleted(task.getCompleted());

		return repository.save(entity);
	}

	public void delete(Long id) {
		logger.info("Deleting one task");

		Task entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("No records found for this ID!"));//ResourceNotFoundException
		repository.delete(entity);
	}

}
