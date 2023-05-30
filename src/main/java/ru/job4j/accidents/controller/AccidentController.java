package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/list";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        Optional<Accident> accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        return "accidents/editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        try {
            boolean isUpdated = accidentService.update(accident);
            if (!isUpdated) {
                model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
                return "errors/404";
            }
            return "redirect:/accidents";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }
}
