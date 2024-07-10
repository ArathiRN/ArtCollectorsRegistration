package com.arathin.artcollectorsregistration.controller;

import com.arathin.artcollectorsregistration.dto.ArtCollectorDto;
import com.arathin.artcollectorsregistration.entity.ArtCollector;
import com.arathin.artcollectorsregistration.services.ArtCollectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorizationController {
    final private ArtCollectorService artCollectorService;

    @Autowired
    public AuthorizationController(ArtCollectorService artCollectorService) {
        this.artCollectorService = artCollectorService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    //handler method will handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        ArtCollectorDto user = new ArtCollectorDto();
        model.addAttribute("user", user);
        return "art-collector-registration";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") ArtCollectorDto user, BindingResult result, Model model) {
        ArtCollector existing = artCollectorService.findArtCollectorByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "art-collector-registration";
        }

        artCollectorService.saveArtCollector(user);
        return "redirect:/art-collector-registration?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<ArtCollectorDto> artCollectors = artCollectorService.findAllArtCollectors();
        model.addAttribute("users", artCollectors);
        return "registered-art-collectors";

    }
}
