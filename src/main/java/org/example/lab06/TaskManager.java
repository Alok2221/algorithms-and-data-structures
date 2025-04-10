package org.example.lab06;

import org.example.lab06.lib.TQueue;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final TQueue<Task> taskQueue;

    public TaskManager() {
        this.taskQueue = new TQueue<>();
    }

    public TaskManager(TQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public TQueue<Task> getTaskTQueue() {
        return taskQueue;
    }

    public boolean addTask(Task task) {
        return taskQueue.put(task);
    }

    public boolean addTask(String name, String description) {
        return addTask(new Task(name, description));
    }

    public Task deleteTask() {
        return taskQueue.get();
    }

    public Task peekFirstTask() {
        return taskQueue.top();
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public int taskCount() {
        return taskQueue.size();
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskQueue) {
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public String toString() {
        if (taskQueue.isEmpty()) {
            return "Brak zadań";
        }

        StringBuilder builder = new StringBuilder();
        int counter = 1;

        for (Task task : taskQueue) {
            builder.append(counter++).append(". ").append(task.toString()).append("\n");
        }

        return builder.toString();
    }
}