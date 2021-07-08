package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class newcat extends Activity {
    SQLiteDatabase mDba;

    EditText editTextCatname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcat);
        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);
        createTable();

        editTextCatname = (EditText)findViewById(R.id.editTextName1);

    }

    private void createTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS categories (\n" +
                "    id INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Catname varchar(20) NOT NULL,\n" +
                "    Regdate datetime NOT NULL\n" +
                ");";

        mDba.execSQL(sql);
    }


    public void addcat(View view)
    {
        String Catname = editTextCatname.getText().toString().trim();

        if(Catname.isEmpty()){
            editTextCatname.setError("Category Name Can't be Empty");
            editTextCatname.requestFocus();
            return;
        }


        if(Catname.length()<3)
        {
            editTextCatname.setError("Category Name shouldn't be lower then 3");
            editTextCatname.requestFocus();
            return;
        }


        if(Catname.length()>15)
        {
            editTextCatname.setError("Category Name shouldn't be lower then 3");
            editTextCatname.requestFocus();
            return;
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        String RegDate = sdf.format(cal.getTime());




        String sqlc = "INSERT INTO categories(Catname,Regdate)"+
                "VALUES (?,?)";
        mDba.execSQL(sqlc,new String[]{Catname,RegDate});


        Toast.makeText(getApplicationContext(),"Categore added Successfully !!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(newcat.this, categories.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newcat, menu);
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
