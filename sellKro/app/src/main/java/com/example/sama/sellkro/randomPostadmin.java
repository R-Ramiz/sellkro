package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class randomPostadmin extends Activity
{
    SQLiteDatabase mDba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_postadmin);

        getActionBar().hide();
        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

//        SessionManagment sessionManagment = new SessionManagment(this);
//        int userID = sessionManagment.getSession();
////        final String randomUserId = String.valueOf(userID);


        Intent intent = getIntent();
        final  String Post_id = intent.getStringExtra("post_id");


        loadThisPost(Post_id);


    }


    private void loadThisPost(String id)
    {
        String sql = "SELECT * FROM postb WHERE id ='"+id+"'";
        Cursor curso = mDba.rawQuery(sql,null);
        curso.moveToFirst();

        String Seller = curso.getString(1);
        String Title = curso.getString(2);
        String Category = curso.getString(3);
        String Details = curso.getString(4);
        String Address = curso.getString(5);
        String Price = curso.getString(6);
        String joiningdate = curso.getString(7);



        TextView textViewTitle =(TextView)findViewById(R.id.textViewTitle);
        TextView textViewJoinDate = (TextView)findViewById(R.id.textViewDate);
        TextView textViewDetails = (TextView)findViewById(R.id.textViewDetails);
        TextView textViewPrice = (TextView)findViewById(R.id.textViewPrice);
        TextView textViewAddress =(TextView)findViewById(R.id.textViewAddress);
        TextView textViewSeller = (TextView)findViewById(R.id.textViewSeller);
        TextView textViewContact = (TextView)findViewById(R.id.textViewContact);



        textViewTitle.setText(Title);
        textViewJoinDate.setText("Posted On: "+joiningdate);
        textViewDetails.setText("Details: \n"+Details);
        textViewAddress.setText("Address: \n"+Address);
        textViewPrice.setText("Price: "+Price);

        String sqlCont = "SELECT * FROM userb WHERE id ='"+Seller+"'";
        Cursor curMob = mDba.rawQuery(sqlCont,null);
        curMob.moveToFirst();

        String Fname = curMob.getString(1);
        String Lname = curMob.getString(2);
        String Contact = curMob.getString(3);

        textViewSeller.setText("Seller: "+Fname+" "+Lname);
        textViewContact.setText("Contact: "+Contact);



    }


    public void adminmenu(View view)
    {

        startActivity(new Intent(randomPostadmin.this,adminmenu.class));
        finish();
    }



    //-----------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_postadmin, menu);
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
