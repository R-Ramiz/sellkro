package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class signup extends Activity implements View.OnClickListener
{
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submitbtn:
                addUser();
                break;
        }
    }

    public static final String DATABASE_NAME = "sella";

    SQLiteDatabase mDba;
    EditText editTextFname,editTextLname,editTextMobile,
            editTextAddress,editTextTown,editTextPin,editTextPass,editTextCpass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mDba = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        createTable();

        editTextFname = (EditText)findViewById(R.id.editTextFname);
        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextTown = (EditText)findViewById(R.id.editTextTown);
        editTextPin = (EditText)findViewById(R.id.editTextPin);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        editTextCpass = (EditText)findViewById(R.id.editTextCpass);

        findViewById(R.id.submitbtn).setOnClickListener(this);


    }
    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS userb (\n" +
                "    id INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Fname varchar(20) NOT NULL,\n" +
                "    Lname varchar(20) NOT NULL,\n" +
                "    Mobile varchar(10) NOT NULL,\n" +
                "    Address varchar(200) NOT NULL,\n" +
                "    Town varchar(20) NOT NULL,\n" +
                "    Pin varchar(6) NOT NULL,\n" +
                "    Pass varchar(10) NOT NULL,\n" +
                "    Cpass varchar(10) NOT NULL,\n" +
                "    joiningdate datetime NOT NULL\n" +
                ");";

        mDba.execSQL(sql);
    }

    private void addUser()
    {

        String Fname = editTextFname.getText().toString().trim();
        String Lname = editTextLname.getText().toString().trim();
        String Mobilet = editTextMobile.getText().toString().trim();
        String Address = editTextAddress.getText().toString().trim();
        String Town = editTextTown.getText().toString().trim();
        String Pin = editTextPin.getText().toString().trim();
        String Pass = editTextPass.getText().toString().trim();
        String Cpass = editTextCpass.getText().toString().trim();


        if (Pass.compareTo(Cpass)==1)
        {
            Log.d("pass",Pass);

            Log.d("pass",Cpass);

        }


        String sqlm = "SELECT Mobile FROM userb WHERE Mobile = "+Mobilet;
        Cursor cursor = mDba.rawQuery(sqlm,null);

        if (cursor.moveToFirst())
        {
            editTextMobile.setError("User is available with this Mobile number");
            editTextMobile.requestFocus();
            return;
        }

        if(Fname.isEmpty()){
            editTextFname.setError("First Name Can't be Empty");
            editTextFname.requestFocus();
            return;
        }


        if(Fname.length()<3)
        {
            editTextFname.setError("First Name > 3  ");
            editTextFname.requestFocus();
            return;
        }


        if(Fname.length()>15)
        {
            editTextFname.setError("First Name < 15");
            editTextFname.requestFocus();
            return;
        }

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


        if (Address.isEmpty()){
            editTextAddress.setError("Address cam't be Empty");
            editTextAddress.requestFocus();
            return;
        }

        if (Town.isEmpty()){
            editTextTown.setError("Town cam't be Empty");
            editTextTown.requestFocus();
            return;
        }

        if (Pin.isEmpty()){
            editTextPin.setError("Pin cam't be Empty");
            editTextPin.requestFocus();
            return;
        }


        if (Pin.length()>6){
            editTextPin.setError("Pin must be 6 digit");
            editTextPin.requestFocus();
            return;
        }


        if (Pin.length()<6){
            editTextPin.setError("Pin must be 6 digit");
            editTextPin.requestFocus();
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


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        String joiningDate = sdf.format(cal.getTime());


        String sql = "INSERT INTO userb(Fname,Lname,Mobile,Address,Town,Pin,Pass,Cpass,joiningdate)"+
                "VALUES (?,?,?,?,?,?,?,?,?)";
        mDba.execSQL(sql,new String[]{Fname,Lname,Mobilet,Address,Town,Pin,Pass,Cpass,joiningDate});

        Toast.makeText(getApplicationContext(), "User added Successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,menu.class));
        finish();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
