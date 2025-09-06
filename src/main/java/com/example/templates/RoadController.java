package com.example.templates;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RoadController {

    private final TemplateRepository templateRepository;


    public RoadController(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currentDate",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "home";
    }

    @GetMapping("/templates")
    public String showTemplates(Model model) {
        model.addAttribute("templates", templateRepository.findAll());
        return "templates";
    }

    @PostMapping("/templates")
    public String addTemplate(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam String content
    ) {
        Template template = new Template();
        template.setName(name.trim());
        template.setDescription(description != null ? description.trim() : "");
        template.setContent(content.trim());
        templateRepository.save(template);
        return "redirect:/templates";
    }
}
