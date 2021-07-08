package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class login extends Activity
{
    SQLiteDatabase mDba;

    EditText umobile,upass;

 @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        umobile = (EditText)findViewById(R.id.editTextName1);
        upass = (EditText)findViewById(R.id.editTextName2);

    }

    public void test(View view){
        Intent intent = new Intent(login.this,AddAdmin.class);
        startActivity(intent);
    }


    public void addCat(View view){
        Intent intent = new Intent(login.this,newcat.class);
        startActivity(intent);
    }

    public void sign(View view){
        Intent intent = new Intent(login.this,signup.class);
        startActivity(intent);
    }

    protected void onStart()
    {
        super.onStart();
        //check if user is logged in
        //if user is logged in --> move to mainActivity
//          checkSessiona();
        checkSession();

    }

    private void checkSessiona()
    {

        SessionManagment sessionManagment = new SessionManagment(login.this);
        int adminID = sessionManagment.getSessiona();

        if (adminID != -1)
        {
            // user id logged in and so move to main activity
            moveToMainActivityAdmin();
        }
        else
        {
            // do nothing
        }
    }

    private void checkSession()
    {

        SessionManagment sessionManagment = new SessionManagment(login.this);
        int userID = sessionManagment.getSession();

        if (userID != -1)
        {
            // user id logged in and so move to main activity
            moveToMainActivity();
        }
        else
        {
            // do nothing
        }
    }

    public void userui(View view)
    {

        String mobile = umobile.getText().toString().trim();
        String pass = upass.getText().toString().trim();


        if (mobile.isEmpty())
        {
            umobile.setError("Mobile Number Can't be Empty");
            umobile.requestFocus();
            return;
        }

        if (pass.isEmpty())
        {
            upass.setError("Password Can't be Empty");
            upass.requestFocus();
            return;

        }
try
{
    String sqlm = "SELECT * FROM userb WHERE Mobile ="+mobile;
    Cursor cursor = mDba.rawQuery(sqlm, null);

    if (cursor.moveToFirst())
    {


    }
    else
    {
        String msg ="User Not Found with "+mobile;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    String verify = cursor.getString(cursor.getColumnIndex("Pass"));
    String rd = cursor.getString(cursor.getColumnIndex("id"));


      if (verify.equals(pass))
       {
        //1.Log in app and save session of user
        int rdd = Integer.valueOf(rd.toString());
        user user = new user(rdd,"Ramiz");
        SessionManagment sessionManagment = new SessionManagment(login.this);
        sessionManagment.saveSession(user);

            //2.move to next activity
            moveToMainActivity();

        }
    else
        {
            upass.setError("Password not match");
            upass.requestFocus();
            return;
     }

   }catch (CursorIndexOutOfBoundsException e)
    {

        String msg ="User Not Found with "+mobile;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

    }

  }


    private void moveToMainActivity()
    {
        Intent intent = new Intent(login.this,userui.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    private void moveToMainActivityAdmin()
    {
        Intent intent = new Intent(login.this,adminmenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void admin(View view)
    {
        // Intent intent = new Intent(login.this,adminmenu.class);
        // startActivity(intent);

        String mobile = umobile.getText().toString().trim();
        String pass = upass.getText().toString().trim();


        if (mobile.isEmpty())
        {
            umobile.setError("Mobile Number Can't be Empty");
            umobile.requestFocus();
            return;
        }

        if (pass.isEmpty())
        {
            upass.setError("Password Can't be Empty");
            upass.requestFocus();
            return;

        }

       try{

           String sqlm = "SELECT * FROM adminramiz WHERE Mobile ='"+mobile+"'";
           Cursor cursor = mDba.rawQuery(sqlm, null);

           if(cursor.moveToFirst())
           {

           }

           String verify = cursor.getString(cursor.getColumnIndex("Pass"));

           String rde = cursor.getString(cursor.getColumnIndex("id"));

           if (verify.equals(pass))
           {
               //1.Log in app and save session of user
               int rdd = Integer.valueOf(rde.toString());
               admin admin  = new admin(rdd,"Ramiz");
               SessionManagment sessionManagment = new SessionManagment(login.this);
               sessionManagment.saveSessiona(admin);

               //2.move to next activity
               moveToMainActivityAdmin();

           }
           else
           {
               upass.setError("Password not match");
               upass.requestFocus();
               return;
           }

       }
            catch (CursorIndexOutOfBoundsException e)
        {

            String msg ="Admin Not Found with "+mobile;
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void runtest(View view)
    {
        Intent intent = new Intent(login.this,spintut.class);
        startActivity(intent);

    }
}
