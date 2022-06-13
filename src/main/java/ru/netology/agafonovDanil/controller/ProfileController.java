package ru.netology.agafonovDanil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.agafonovDanil.systemProfile.SystemProfile;

@RestController
@RequestMapping("/")

public class ProfileController {
    private SystemProfile profile;

    @Autowired
    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }


    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }
}