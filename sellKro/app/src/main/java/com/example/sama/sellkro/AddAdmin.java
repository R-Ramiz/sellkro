package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddAdmin extends Activity implements View.OnClickListener
{
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.submitbtn:
                addAdmin();
                break;
        }
    }


    SQLiteDatabase mDba;
    EditText editTextLname,editTextMobile,
            editTextPass,editTextCpass;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

        createTable();

        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        editTextCpass = (EditText)findViewById(R.id.editTextCpass);

        findViewById(R.id.submitbtn).setOnClickListener(this);
     }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS adminramiz (\n" +
                "    id INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Lname varchar(20) NOT NULL,\n" +
                "    Mobile varchar(10) NOT NULL,\n" +
                "    Pass varchar(10) NOT NULL\n" +
                ");";

        mDba.execSQL(sql);
    }

    private void addAdmin()
    {


        String Lname = editTextLname.getText().toString().trim();
        String Mobilet = editTextMobile.getText().toString().trim();
        String Pass = editTextPass.getText().toString().trim();
        String Cpass = editTextCpass.getText().toString().trim();


        if (Lname.isEmpty()){
            editTextLname.setError("Last Name cam't be Empty");
            editTextLname.requestFocus();
            return;
        }


        if(Lname.length()<3){
            editTextLname.setError("First Name >=3");
            editTextLname.requestFocus();
            return;
        }


        if(Lname.length()>15){
            editTextLname.setError("First Name Can't be Empty");
            editTextLname.requestFocus();
            return;
        }

        if (Mobilet.isEmpty()){
            editTextMobile.setError("Mobile cam't be Empty");
            editTextMobile.requestFocus();
            return;
        }

        if (Mobilet.length()<10){
            editTextMobile.setError("Mobile Number must be 10 Digit ");
            editTextMobile.requestFocus();

            return;
        }

        if (Mobilet.length()>10){
            editTextMobile.setError("Mobile Number must be 10 Digit ");
            editTextMobile.requestFocus();

            return;
        }

        if (Pass.isEmpty()){
            editTextPass.setError("Pass  cam't be Empty");
            editTextPass.requestFocus();
            return;
        }


        if (Pass.length()<4){
            editTextPass.setError("Pass  must be 4 digit or 8");
            editTextPass.requestFocus();
            return;
        }


        if (Pass.length()>8){
            editTextPass.setError("Pass  must be 4 digit or 8");
            editTextPass.requestFocus();
            return;
        }

        if (Cpass.isEmpty()){
            editTextCpass.setError("Confirm Password  cam't be Empty");
            editTextCpass.requestFocus();
            return;
        }


        if (!Cpass.equals(Pass))
        {
            editTextCpass.setError("Password & Confirm Password  must be Same");
            editTextCpass.requestFocus();
            return;
        }

        String sql = "INSERT INTO adminramiz(Lname,Mobile,Pass)"+
                "VALUES (?,?,?)";
        mDba.execSQL(sql,new String[]{Lname,Mobilet,Pass});

        Toast.makeText(getApplicationContext(), "Admin added Successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,menu.class));
        finish();


    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
