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

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class sell extends Activity
{
    SQLiteDatabase mDba;
    EditText editTextTitle,
            editTextDetails,editTextAddress,editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        createTable();

        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextDetails = (EditText)findViewById(R.id.editTextDetails);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextPrice = (EditText)findViewById(R.id.editTextPrice);


    }

    private void createTable()
    {
        String sqlpost = "CREATE TABLE IF NOT EXISTS postb (\n" +
                "    id INTEGER NOT NULL CONSTRAINT post_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Seller varchar(12) NOT NULL,\n" +
                "    Title varchar(20) NOT NULL,\n" +
                "    Category varchar(20) NOT NULL,\n" +
                "    Details varchar(200) NOT NULL,\n" +
                "    Address varchar(100) NOT NULL,\n" +
                "    Price varchar(7) NOT NULL,\n" +
                "    joiningdate datetime NOT NULL\n" +
                ");";

        mDba.execSQL(sqlpost);
    }


    public void addpost(View view)
    {

        SessionManagment sessionManagment = new SessionManagment(sell.this);
        int userID = sessionManagment.getSession();
        String Seller = String.valueOf(userID);


        String Title = editTextTitle.getText().toString().trim();
        String Details = editTextDetails.getText().toString().trim();
        String Address = editTextAddress.getText().toString().trim();
        String Price = editTextPrice.getText().toString().trim();



        if(Title.isEmpty()){
            editTextTitle.setError("Title Can't be Empty");
            editTextTitle.requestFocus();
            return;
        }


        if(Title.length()<3)
        {
            editTextTitle.setError("Title must be greter then 3  ");
            editTextTitle.requestFocus();
            return;
        }


        if (Address.isEmpty()){
            editTextAddress.setError("Address cam't be Empty");
            editTextAddress.requestFocus();
            return;
        }

        if (Details.isEmpty()){
            editTextDetails.setError("Details cam't be Empty");
            editTextDetails.requestFocus();
            return;
        }

        if (Price.isEmpty()){
            editTextPrice.setError("Price cam't be Empty");
            editTextPrice.requestFocus();
            return;
        }



        if (Price.length()<2){
            editTextPrice.setError("Price must grater than 100");
            editTextPrice.requestFocus();
            return;
        }



        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        String joiningDate = sdf.format(cal.getTime());


        String cateId = getIntent().getStringExtra("itemId");
        String sql = "INSERT INTO postb(seller,Title,Category,Details,Address,Price,joiningdate)"+
                "VALUES (?,?,?,?,?,?,?)";
        mDba.execSQL(sql,new String[]{Seller,Title,cateId,Details,Address,Price,joiningDate});

        Toast.makeText(getApplicationContext(),"Post Added Successfully !!"+cateId,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(sell.this, umyads.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sell, menu);
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
