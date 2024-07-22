package com.almo.gestionUtilisateur.controllers;

import com.almo.gestionUtilisateur.entity.AppUser;
import com.almo.gestionUtilisateur.entity.RegisterDto;
import com.almo.gestionUtilisateur.repository.AppUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AppUserRepository appUserRepository;


    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto", "confirmpassword", "Password and Confirm Password do not match")
            );
        }
        AppUser appUser = appUserRepository.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            result.addError(
                    new FieldError("registerDto", "email", "Email adress is already used")
            );
        }
        if (result.hasErrors()) {
            return "register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();
            AppUser newUser = new AppUser();

            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAdress(registerDto.getAdress());
            newUser.setRole("client");
            newUser.setCreateAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
            appUserRepository.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);

        }catch (Exception ex){
            result.addError(
                    new FieldError("registerDto", "firstName", ex.getMessage())
            );

        }


        return "register";
    }


}
