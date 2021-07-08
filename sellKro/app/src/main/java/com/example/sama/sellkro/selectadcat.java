package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class selectadcat extends Activity
{
    private Spinner spinner;
    SQLiteDatabase mDba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectadcat);
        getActionBar().hide();
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinner = (Spinner) findViewById(R.id.spinner);
        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose Category");


        String sql = "SELECT * FROM categories";
        Cursor cursor = mDba.rawQuery(sql,null);

        if(cursor.moveToFirst())

            do{
                categories.add(cursor.getString(1));

            }while (cursor.moveToNext());


//        Styele and populate the spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);

//        Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Category")) {
//                    //DO NOTHING
                } else {
//                    on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

//                    show selected spinner item
//                    Toast.makeText(parent.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();
                    parent.getItemAtPosition(position);


                    String rd = "SELECT id FROM categories WHERE catname = '"+item+"'";
                    Cursor hjj = mDba.rawQuery(rd,null);

                    hjj.moveToFirst();
                    String ss = String.valueOf(hjj.getInt(0));


                    Intent intent = new Intent(selectadcat.this,sell.class);
                    intent.putExtra("itemId",ss);
                    startActivity(intent);
                    finish();


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selectadcat, menu);
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
