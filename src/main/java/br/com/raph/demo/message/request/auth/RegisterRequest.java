package br.com.raph.demo.message.request.auth;

import br.com.raph.demo.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @StrongPassword
    private String password;
}
