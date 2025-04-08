package DAO;

public class User
{
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;

    public User() {}


    public User(String name,String username, String password, String email)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getName()
    {
        return this.name;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getEmail()
    {
        return this.email;
    }

    public int getId()
    {
        return this.id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }




}
