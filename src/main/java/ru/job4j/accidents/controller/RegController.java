package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.data.AuthorityRepository;
import ru.job4j.accidents.service.data.DataUserService;

@Controller
@AllArgsConstructor
public class RegController {

    private final PasswordEncoder encoder;
    private final DataUserService userService;
    private final AuthorityRepository authorities;

    @GetMapping("/register")
    public String regPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String regSave(Model model, @ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        var userOptional = userService.save(user);
        if (userOptional.isEmpty()) {
            String errorMessage = user.getUsername() + " пользователь с таким именем уже существует";
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
        return "redirect:/login";
    }
}
