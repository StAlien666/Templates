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

@Controller
public class ClueController {

    private final ClueRepository clueRepository;

    public ClueController(ClueRepository clueRepository) {
        this.clueRepository = clueRepository;
    }

    @GetMapping("/clues")
    public String showClues(Model model) {
        model.addAttribute("clues", clueRepository.findAll());
        return "clues";
    }

    @PostMapping("/clues")
    public String addClue(
            @RequestParam String name,
            @RequestParam String content,
            RedirectAttributes ra
    ) {
        Clue clue = new Clue(name.trim(), content.trim());
        clueRepository.save(clue);
        ra.addFlashAttribute("message", "Подсказка сохранена.");
        return "redirect:/clues";
    }

    @PostMapping("/clues/delete")
    public String deleteClue(@RequestParam("id") Long id, RedirectAttributes ra) {
        if (id != null && clueRepository.existsById(id)) {
            clueRepository.deleteById(id);
            ra.addFlashAttribute("message", "Подсказка удалена.");
        } else {
            ra.addFlashAttribute("error", "Подсказка не найдена.");
        }
        return "redirect:/clues";
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

}
