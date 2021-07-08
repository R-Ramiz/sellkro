package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class uallads extends Activity {


    SQLiteDatabase mDba;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uallads);
        getActionBar().hide();

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);


        Intent intent = getIntent();
        String cat_id = intent.getStringExtra("cat_id");


        String ff = "SELECT * FROM categories WHERE id ="+cat_id;
        Cursor cursor = mDba.rawQuery(ff,null);
        cursor.moveToFirst();
        String jh = cursor.getString(1);
        Toast.makeText(getApplicationContext(),jh, Toast.LENGTH_SHORT).show();

    }


    public void adminmenu(View view)
    {
        startActivity(new Intent(uallads.this,menu.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uallads, menu);
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
