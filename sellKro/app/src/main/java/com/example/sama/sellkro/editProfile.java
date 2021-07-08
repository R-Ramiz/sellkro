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
import android.widget.TextView;
import android.widget.Toast;


public class editProfile extends Activity implements View.OnClickListener
{
    SQLiteDatabase mDba;
    EditText editTextFname,editTextLname,editTextMobile,
            editTextAddress,editTextTown,editTextPin,editTextPass,editTextCpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        loadEmployeesFromDatabase();

        editTextFname = (EditText)findViewById(R.id.editTextFname);
        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextTown = (EditText)findViewById(R.id.editTextTown);
        editTextPin = (EditText)findViewById(R.id.editTextPin);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        editTextCpass = (EditText)findViewById(R.id.editTextCpass);

        findViewById(R.id.updateBtn).setOnClickListener(this);

    }

    private void loadEmployeesFromDatabase()
    {

        SessionManagment sessionManagment = new SessionManagment(editProfile.this);
        int userID = sessionManagment.getSession();
        final String uid = String.valueOf(userID);

        String sql = "SELECT * FROM userb WHERE id = "+uid;
        Cursor cursor = mDba.rawQuery(sql,null);

        cursor.moveToFirst();


        String fname = cursor.getString(1);
        String lname = cursor.getString(2);
        String mobile = cursor.getString(3);
        String address = cursor.getString(4);
        String town = cursor.getString(5);
        String pin = cursor.getString(6);
//        String pass = cursor.getString(7);
//        String cpass = cursor.getString(8);



        TextView v2 = (TextView)findViewById(R.id.editTextFname);
        TextView v3 = (TextView)findViewById(R.id.editTextLname);
        TextView v4 = (TextView)findViewById(R.id.editTextMobile);
        TextView v5 = (TextView)findViewById(R.id.editTextAddress);
        TextView v6 = (TextView)findViewById(R.id.editTextTown);
        TextView v7 = (TextView)findViewById(R.id.editTextPin);
//        TextView v8 = (TextView)findViewById(R.id.pass);
//        TextView v9 = (TextView)findViewById(R.id.cpass);


        v2.setText(fname);
        v3.setText(lname);
        v4.setText(mobile);
        v5.setText(address);
        v6.setText(town);
        v7.setText(pin);
//        v8.setText(pass);
//        v9.setText(cpass);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.updateBtn:
                updateUser();
                break;
        }
    }

    private void updateUser()
    {
        SessionManagment sessionManagment = new SessionManagment(editProfile.this);
        int userID = sessionManagment.getSession();
        final String uid = String.valueOf(userID);

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
            Log.d("pass", Pass);

            Log.d("pass",Cpass);

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

        String sql = "UPDATE userb SET Fname = ?, Lname = ?, Address = ?, Town = ?, Pin = ?, Pass = ?, Cpass = ? WHERE id = ?";
        mDba.execSQL(sql,new String[]{Fname,Lname,Address,Town,Pin,Pass,Cpass,uid});

        Toast.makeText(this,"Profile Updated...",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,myprofile.class));
        finish();
    }
//-----------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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
