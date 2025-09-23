package com.example.templates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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


}