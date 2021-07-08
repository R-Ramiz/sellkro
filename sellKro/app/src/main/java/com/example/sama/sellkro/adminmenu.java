package com.example.sama.sellkro;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class adminmenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmenu);
        getActionBar().hide();
    }


    public void accounts(View view)
    {
        Intent intent = new Intent(adminmenu.this,allusers.class);
        startActivity(intent);
//        finish();
    }

    public void allads(View view)
    {
        Intent intent = new Intent(adminmenu.this,allads.class);
        intent.putExtra("uid","-1");
        startActivity(intent);
//        finish();
    }

    public void categories(View view)
    {
        Intent intent = new Intent(adminmenu.this,categories.class);
        startActivity(intent);
//        finish();
    }

    public void adminedit(View view)
    {
        Intent intent = new Intent(adminmenu.this,adminedit.class);
        startActivity(intent);
//        finish();
    }

    public void logout(View view)
    {
        //this method will remove session and open login screen
        SessionManagment sessionManagment = new SessionManagment(adminmenu.this);
        sessionManagment.removeSessiona();

        moveToLog();
    }

    private void moveToLog() {

        Intent intent = new Intent(adminmenu.this,login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adminmenu, menu);
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
