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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class postList extends Activity {

    SQLiteDatabase mDba;
    List<post> postList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);

        postList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewUsers2);

        loadMyPost();


    }


    //    String sql = "SELECT id, Title,Category, Details, Address,Price, joiningdate FROM postb";
    private void loadMyPost()
    {
        SessionManagment sessionManagment = new SessionManagment(this);
        int userID = sessionManagment.getSession();
        String rdd = String.valueOf(userID);


        Intent intent = getIntent();
        String cat_id = intent.getStringExtra("cat_id");


        String ff = "SELECT * FROM categories WHERE id = '"+cat_id+"'";
        Cursor cursor = mDba.rawQuery(ff,null);
        cursor.moveToFirst();
        String jh = cursor.getString(0);

       try{
            String sql = "SELECT id,Title,Category,Address,Price,joiningdate FROM postb WHERE Category = '"+cat_id+"'";
            Cursor curso = mDba.rawQuery(sql,null);

            if(curso.moveToFirst())
                do{

                    postList.add(new post(
                                    curso.getInt(0),
                                    curso.getString(1),
                                    curso.getString(2),
                                    curso.getString(3),
                                    curso.getString(4),
                                    curso.getString(5))
                    );
                }while (curso.moveToNext());

            PostAdapter adapter;
            adapter = new PostAdapter(this,R.layout.list_layout_allpost,postList,mDba);
            listView.setAdapter(adapter);


        }catch (CursorIndexOutOfBoundsException e)
        {

            Toast.makeText(getApplicationContext(),"No Post Found",Toast.LENGTH_LONG).show();

        }

    }

    public void adminmenu(View view)
    {
        startActivity(new Intent(this,menu.class));
        finish();
    }
    public void addnew(View view)
    {
        startActivity(new Intent(this,selectadcat.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_list, menu);
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
