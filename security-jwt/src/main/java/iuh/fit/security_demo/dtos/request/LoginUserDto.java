package iuh.fit.security_demo.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUserDto {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;


    @NotBlank(message = "Password is required")
    private String password;

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
