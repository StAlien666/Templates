package com.example.templates;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // запись в БД нужных шаблонов.
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

    //удаление
    @PostMapping("/templates/delete")
    public String deleteTemplate(@RequestParam("id") Long id, RedirectAttributes ra) {
        templateRepository.deleteById(id);
        return "redirect:/templates";
    }


    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

}
