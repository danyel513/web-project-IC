package org.example.chromaglambackend.DTO;

public class RegisterRequest
{
    private String name;
    private String email;
    private String password;
    private String username;
    private String preferences;

    public RegisterRequest(String name, String username, String email, String password, String preferences)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

}
