package com.example.templates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Clue {             //не использую ломбок намеренно(хочу видеть разницу).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название обязательно")
    private String name;

    @NotBlank(message = "Содержимое не может быть пустым")
    @Lob
    private String content;


    public Clue() {
    }


    public Clue(String name, String content) {
        this.name = name;
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setContent(String content) {
        this.content = content;
    }
}
