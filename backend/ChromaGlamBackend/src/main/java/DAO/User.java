package DAO;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@Table(name = "users")
@Data

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







}
