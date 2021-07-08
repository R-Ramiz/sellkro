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

public class allusers extends Activity {

    SQLiteDatabase mDba;
    List<user> userList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allusers);
        getActionBar().hide();

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

        userList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewUsers);

        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase()
    {
         String sql = "SELECT id, Fname, Town, Mobile, joiningdate FROM userb";
        Cursor cursor = mDba.rawQuery(sql,null);

        if(cursor.moveToFirst())
            do{

                userList.add(new user(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getColumnIndex("Pass") ,
                                cursor.getString(4)

                                       )
                );
            }while (cursor.moveToNext());

        UserAdapter adapter;
        adapter = new UserAdapter(this,R.layout.list_layout_users,userList,mDba);
        listView.setAdapter(adapter);


    }


    public void adminmenu(View view)
    {
        startActivity(new Intent(allusers.this, adminmenu.class));
        finish();
    }

    public void msg(View view)
    {
        Toast.makeText(getApplication(),"hello",Toast.LENGTH_LONG).show();
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_allusers, menu);
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
