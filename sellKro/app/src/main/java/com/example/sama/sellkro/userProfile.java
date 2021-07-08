package com.example.sama.sellkro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class userProfile extends Activity
{
    SQLiteDatabase mDba;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        Intent intent = getIntent();
        final  String User_id = intent.getStringExtra("user_id");

        Toast.makeText(getApplicationContext(),User_id,Toast.LENGTH_LONG).show();
        final int user = Integer.parseInt(User_id);
        loadUserFromDatabase(user);

        Button delete = (Button)findViewById(R.id.deletebtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deleteUser(user);
            }
        });

        Button posts = (Button)findViewById(R.id.posts);
        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                loadposts(user);
            }


    });

    }
    private void loadposts(int user)
    {
       String userid = Integer.toString(user);

        Intent th = new Intent(this,allads.class);
        th.putExtra("uid",userid);
        startActivity(th);


        finish();
    }


    private void deleteUser(final int userid)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(userProfile.this);
        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

               String sqla = "DELETE FROM userb WHERE id ="+userid;
               mDba.execSQL(sqla, null);

                String sqlb = "DELETE FROM postb WHERE Seller ="+userid;
                mDba.execSQL(sqlb);

                String sqlc = "DELETE FROM saved WHERE userid ="+userid;
                mDba.execSQL(sqlc);


                Intent next = new Intent(userProfile.this,allusers.class);
                startActivity(next);
                finish();
            }
        });

        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadUserFromDatabase(int User_id)
    {

        String sql = "SELECT * FROM userb WHERE id = "+User_id;
        Cursor cursor = mDba.rawQuery(sql,null);

        cursor.moveToFirst();

        String id = cursor.getString(0);
        String fname = cursor.getString(1);
        String lname = cursor.getString(2);
        String mobile = cursor.getString(3);
        String address = cursor.getString(4);
        String town = cursor.getString(5);
        String pin = cursor.getString(6);
        String pass = cursor.getString(7);
//        String cpass = cursor.getString(8);


        TextView v1 = (TextView)findViewById(R.id.id);
        TextView v2 = (TextView)findViewById(R.id.fname);
        TextView v3 = (TextView)findViewById(R.id.lname);
        TextView v4 = (TextView)findViewById(R.id.mobile);
        TextView v5 = (TextView)findViewById(R.id.address);
        TextView v6 = (TextView)findViewById(R.id.town);
        TextView v7 = (TextView)findViewById(R.id.pin);
        TextView v8 = (TextView)findViewById(R.id.pass);
//        TextView v9 = (TextView)findViewById(R.id.cpass);

        v1.setText(id);
        v2.setText(fname);
        v3.setText(lname);
        v4.setText(mobile);
        v5.setText(address);
        v6.setText(town);
        v7.setText(pin);
        v8.setText(pass);
//        v9.setText(cpass);

    }

    public void adminmenu(View view)
    {
        startActivity(new Intent(userProfile.this, adminmenu.class));
        finish();
    }























//-------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
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
