package com.almo.gestionUtilisateur.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    private String phone;
    private String adress;
    @Size(min = 6, message = "Entrez minimum 6 caractères")
    private String password;
    private String confirmPassword;

}
