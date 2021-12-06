package com.example.taskplanner.network.dto;

import com.example.taskplanner.network.enums.TaskStatus;

import java.util.Date;

public class TaskDto {
    private final String name;
    private final String description;
    private final TaskStatus status;
    private final String assignedTo;
    private final Date dueDate;
    private final Date createdAt;

    public TaskDto(String name, String description, TaskStatus status, String assignedTo, Date dueDate, Date createdAt) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}