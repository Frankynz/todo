package com.todo.model.dto;

public class UserTodoDto {
    private Long id;
    private String description;
    private boolean isActive;

    public UserTodoDto() {
    }

    public UserTodoDto(Long id, String description, boolean isActive) {
        this.id = id;
        this.description = description;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
