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


public class usavedads extends Activity
{
    SQLiteDatabase mDba;
    List<post> postList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usavedads);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        postList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewUsers2);
        SessionManagment sessionManagment = new SessionManagment(this);
        int userID = sessionManagment.getSession();
        String uid = String.valueOf(userID);

        createTable();
        loadMyPostb(userID);
    }

    private void loadMyPostb(int user_id)
    {

        try{
            String sql = "SELECT id,Title,Category,Address,Price,joiningdate FROM postb WHERE Seller ="+user_id;

            Cursor cursor = mDba.rawQuery(sql,null);

            if(cursor.moveToFirst())
                do{

                    postList.add(new post(
                                    cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getString(4),
                                    cursor.getString(5))
                    );
                }while (cursor.moveToNext());

            PostAd adapter;
            adapter = new PostAd(this,R.layout.list_layout_saved,postList,mDba);
            listView.setAdapter(adapter);


        }catch (NullPointerException e)
        {
            Toast.makeText(this, "Null....", Toast.LENGTH_LONG).show();
        }

    }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS saved (\n" +
                "    id INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    userid INTEGER NOT NULL,\n" +
                "    postid INTEGER NOT NULL,\n" +
                "    catid INTEGER NOT NULL\n" +
                ");";

        mDba.execSQL(sql);

    }

    public void adminmenu(View view)
    {
        startActivity(new Intent(this,menu.class));
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usavedads, menu);
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
