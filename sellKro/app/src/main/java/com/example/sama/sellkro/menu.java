package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class menu extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getActionBar().hide();

        run();
    }

public void run()
{
    SessionManagment sessionManagment = new SessionManagment(menu.this);
    int userID = sessionManagment.getSession();
    String rdd = String.valueOf(userID);
    Toast.makeText(getApplicationContext(), rdd, Toast.LENGTH_LONG).show();
}

    public void myprofile(View view)
    {
        Intent intent = new Intent(menu.this,myprofile.class);
        startActivity(intent);
//        finish();
    }

    public void allads(View view)
    {
        Intent intent = new Intent(menu.this,userui.class);
        startActivity(intent);
        finish();
    }

    public void savedads(View view)
    {
        Intent intent = new Intent(menu.this,usavedads.class);
        startActivity(intent);
//        finish();
    }

    public void myads(View view)
    {
        Intent intent = new Intent(menu.this,umyads.class);
        startActivity(intent);
        //finish();
    }





    public void logout(View view)
    {
        //this method will remove session and open login screen
        SessionManagment sessionManagment = new SessionManagment(menu.this);
        sessionManagment.removeSession();

        moveToLog();
    }

    private void moveToLog() {

        Intent intent = new Intent(menu.this,login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
}
