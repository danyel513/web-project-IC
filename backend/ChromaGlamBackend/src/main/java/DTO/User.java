package DTO;

public class User
{
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;


    public User(final int id, final String name, final String username, String password, final String email)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
