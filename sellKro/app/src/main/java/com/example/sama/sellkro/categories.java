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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class categories extends Activity
{
    SQLiteDatabase mDba;
    List<CatClass> catList;
    ListView listView;

    int id;
    String catTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        catList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewUsers);


        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase()
    {

        SessionManagment sessionManagment = new SessionManagment(categories.this);
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
        adapter = new CatAdapter(this,R.layout.list_layout_cat,catList,mDba);
        listView.setAdapter(adapter);


    }



    public void adminmenu(View view)
    {
        startActivity(new Intent(categories.this,adminmenu.class));
        finish();

    }

    public void addCat(View view)
    {
        startActivity(new Intent(categories.this,newcat.class));
        finish();
    }


    public void msg(View view)
    {
        Toast.makeText(getApplication(), "Done", Toast.LENGTH_LONG).show();
    }
//public void extra(){
//
//
//
//    cursor.moveToFirst();
//
//    String id = cursor.getString(0);
//    String fname = cursor.getString(1);
//
//
//    TextView v1 = (TextView)findViewById(R.id.id);
//    TextView v2 = (TextView)findViewById(R.id.fname);
//
//    v1.setText(id);
//    v2.setText(fname);
//
//}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
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
