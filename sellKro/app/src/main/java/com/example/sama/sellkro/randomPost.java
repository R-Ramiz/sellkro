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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class randomPost extends Activity
{
    SQLiteDatabase mDba;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_post);

        getActionBar().hide();
        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

        SessionManagment sessionManagment = new SessionManagment(this);
        int userID = sessionManagment.getSession();
        final String randomUserId = String.valueOf(userID);


        Intent intent = getIntent();
        final  String Post_id = intent.getStringExtra("post_id");


        loadThisPost(Post_id);

        final Button saveBtn = (Button) findViewById(R.id.savePost);
        final Button deleteBtn = (Button) findViewById(R.id.deletePost);


try
{
    String sqlt = "SELECT id FROM saved WHERE userid = '" + randomUserId + "' AND postid = '" + Post_id + "'";
    Cursor cursor = mDba.rawQuery(sqlt, null);

    if (cursor.moveToFirst())
    {
        saveBtn.setVisibility(View.INVISIBLE);
        deleteBtn.setVisibility(View.VISIBLE);



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sqldt = "DELETE FROM saved WHERE userid = '" + randomUserId + "' AND postid = '" + Post_id + "'";
                mDba.execSQL(sqldt);

                saveBtn.setVisibility(View.VISIBLE);
                deleteBtn.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), "Post Un-Saved Successfully", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(randomPost.this,randomPost.class);
                refresh.putExtra("post_id",Post_id);
                startActivity(refresh);
                finish();

            }
        });
    }
    else
    {

        saveBtn.setVisibility(View.VISIBLE);
        deleteBtn.setVisibility(View.INVISIBLE);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String sqlc = "SELECT Category FROM postb WHERE id = "+Post_id;
                Cursor ak  = mDba.rawQuery(sqlc,null);

                if(ak.moveToFirst());
                int rt = ak.getInt(0);

                String rs = String.valueOf(rt);

                String sql = "INSERT INTO saved(userid,postid,catid)"+
                        "VALUES (?,?,?)";
                mDba.execSQL(sql,new String[]{randomUserId,Post_id,rs});

                saveBtn.setVisibility(View.INVISIBLE);
                deleteBtn.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Post Saved Successfully", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(randomPost.this,randomPost.class);
                refresh.putExtra("post_id",Post_id);
                startActivity(refresh);
                finish();
            }
        });

    }
}
catch (CursorIndexOutOfBoundsException e)
{
    Toast.makeText(this,"Catch Part",Toast.LENGTH_LONG).show();
}


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

        startActivity(new Intent(randomPost.this,menu.class));
        finish();
    }


//-----------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_post, menu);
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
