package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class umyads extends Activity {

    SQLiteDatabase mDba;
    List<post> postList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umyads);
        getActionBar().hide();

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

try{
    String sql = "SELECT id,Title,Category,Address,Price,joiningdate FROM postb WHERE Seller ="+rdd;
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

    PostAdapter adapter;
    adapter = new PostAdapter(this,R.layout.list_layout_post,postList,mDba);
    listView.setAdapter(adapter);


}catch (CursorIndexOutOfBoundsException e)
{

}

    }
//------------------------------------------------------------
    public void adminmenu(View view)
    {
        startActivity(new Intent(umyads.this,menu.class));
        finish();
    }
    public void addnew(View view)

     {
        startActivity(new Intent(umyads.this,selectadcat.class));
         finish();
     }

//----------------------------Don't Go Below This Line ---------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_umyads, menu);
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
