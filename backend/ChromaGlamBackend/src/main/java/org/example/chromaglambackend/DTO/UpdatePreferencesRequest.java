package org.example.chromaglambackend.DTO;

public class UpdatePreferencesRequest {
    private String username;
    private String preferences;

    public String getUsername() {
        return username;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
