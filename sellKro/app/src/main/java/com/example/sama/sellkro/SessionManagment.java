package com.example.sama.sellkro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sama on 4/3/2021.
 */
public class SessionManagment
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";


    public SessionManagment(Context context)
    {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(user user)
    {
        // save session of user whenever user is logged in
        int id = user.getId();
        editor.putInt(SESSION_KEY,id).commit();
    }

    public int getSession()
    {
        // Return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY,-1);
    }

    public void removeSession()

    {
        editor.putInt(SESSION_KEY,-1).commit();
    }


                    //    Admin
//
//    public SessionManagmenta(Context context)
//    {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//    }

    public void saveSessiona(admin admin)
    {
        // save session of user whenever user is logged in
        int id = admin.getId();
        editor.putInt(SESSION_KEY,id).commit();
    }

    public int getSessiona()
    {
        // Return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY,-2);
    }

    public void removeSessiona()


    {
        editor.putInt(SESSION_KEY,-1).commit();
    }

}
