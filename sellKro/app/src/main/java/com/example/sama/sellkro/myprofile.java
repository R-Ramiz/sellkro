package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class myprofile extends Activity
{
    SQLiteDatabase mDba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        getActionBar().hide();

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        loadEmployeesFromDatabase();
    }


    private void loadEmployeesFromDatabase()
    {

        SessionManagment sessionManagment = new SessionManagment(myprofile.this);
        int userID = sessionManagment.getSession();
        String uid = String.valueOf(userID);
        String sql = "SELECT * FROM userb WHERE id = "+uid;

        Cursor cursor = mDba.rawQuery(sql,null);

        cursor.moveToFirst();

        String id = cursor.getString(0);
        String fname = cursor.getString(1);
        String lname = cursor.getString(2);
        String mobile = cursor.getString(3);
        String address = cursor.getString(4);
        String town = cursor.getString(5);
        String pin = cursor.getString(6);
//        String pass = cursor.getString(7);
//        String cpass = cursor.getString(8);


        TextView v1 = (TextView)findViewById(R.id.id);
        TextView v2 = (TextView)findViewById(R.id.fname);
        TextView v3 = (TextView)findViewById(R.id.lname);
        TextView v4 = (TextView)findViewById(R.id.mobile);
        TextView v5 = (TextView)findViewById(R.id.address);
        TextView v6 = (TextView)findViewById(R.id.town);
        TextView v7 = (TextView)findViewById(R.id.pin);
//        TextView v8 = (TextView)findViewById(R.id.pass);
//        TextView v9 = (TextView)findViewById(R.id.cpass);

        v1.setText(id);
        v2.setText(fname);
        v3.setText(lname);
        v4.setText(mobile);
        v5.setText(address);
        v6.setText(town);
        v7.setText(pin);
//        v8.setText(pass);
//        v9.setText(cpass);

    }

    public void adminmenu(View view)
    {
        startActivity(new Intent(myprofile.this,menu.class));
        finish();
    }

    public void editBtn(View view)
    {
        startActivity(new Intent(myprofile.this,editProfile.class));
        finish();
    }
//---------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myprofile, menu);
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
