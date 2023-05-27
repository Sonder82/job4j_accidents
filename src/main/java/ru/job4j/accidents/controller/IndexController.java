package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    /**
     * Метод используется для отображения начальной страницы
     *
     * @return возвращает начальную страницу
     */
    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        return "index";
    }
}
