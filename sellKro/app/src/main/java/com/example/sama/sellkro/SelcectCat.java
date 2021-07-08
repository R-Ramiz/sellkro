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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SelcectCat extends Activity
{
    SQLiteDatabase mDba;
    List<CatClass> catList;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selcect_cat);

      getActionBar().hide();
      mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
      catList = new ArrayList<>();
      listView = (ListView)findViewById(R.id.listViewUsers);


        loadEmployeesFromDatabase();
    }


    private void loadEmployeesFromDatabase()
    {
        SessionManagment sessionManagment = new SessionManagment(this);
        int userID = sessionManagment.getSession();

        String uid = String.valueOf(userID);

        String sql = "SELECT * FROM categories";
        Cursor cursor = mDba.rawQuery(sql,null);

        if(cursor.moveToFirst())
            do{
                catList.add(new CatClass(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2)
                                )
                );

            }while (cursor.moveToNext());

        CatAdapter adapter;
        adapter = new CatAdapter(this,R.layout.normal_cat,catList,mDba);
        listView.setAdapter(adapter);

    }



    public void adminmenu(View view)
    {
        startActivity(new Intent(this,adminmenu.class));
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selcect_cat, menu);
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
