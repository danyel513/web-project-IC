package org.example.chromaglambackend.cg_service;


import org.mindrot.jbcrypt.BCrypt;



public class PasswordEncryption
{
    public static String hashPassword(String plainPassword)
    {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(45));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword)
    {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
