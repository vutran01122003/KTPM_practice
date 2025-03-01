package iuh.fit.security_demo.dtos.response;

public class UserResponse {
    private String email;
    private String username;
    private String role;

    public UserResponse(String email, String username, String role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public UserResponse() {
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
