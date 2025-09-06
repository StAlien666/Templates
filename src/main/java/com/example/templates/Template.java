package com.example.templates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название обязательно")
    private String name;

    private String description;

    @NotBlank(message = "Содержимое не может быть пустым")
    @Lob
    private String content;


    public Template() {
    }


    public Template(String name, String description, String content) {
        this.name = name;
        this.description = description;
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }
}