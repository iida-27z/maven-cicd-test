package io.github.test.app.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @GetMapping("/")
    public String getSettingsPage() {
        return "pages/coming-soon";
    }

}
