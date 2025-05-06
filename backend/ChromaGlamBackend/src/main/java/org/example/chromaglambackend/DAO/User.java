package org.example.chromaglambackend.DAO;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Setter
@Getter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "preferences")
    private String preferences;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private byte[] avatar;

    public User() {}

    public User(String name,String username, String password, String email)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // getters and setters


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
