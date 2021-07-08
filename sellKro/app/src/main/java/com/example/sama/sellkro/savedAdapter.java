package com.example.sama.sellkro;

/**
 * Created by Ramiz on 4/24/2021.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class savedAdapter extends ArrayAdapter<post>
{
        Context mCtx;
        int layoutRes;
        List<post> postList;
        ListView listView;
        SQLiteDatabase mDba;

public savedAdapter(Context mCtx,int layoutRes,List<post> postList,SQLiteDatabase mDba)
        {
        super(mCtx,layoutRes,postList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.postList = postList;
        this.mDba = mDba;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = LayoutInflater.from(mCtx);
    View view = inflater.inflate(layoutRes, null);

    TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
    TextView textViewCategory = (TextView) view.findViewById(R.id.textViewCategory);
    TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
    TextView textViewJoinDate = (TextView) view.findViewById(R.id.textViewJoiningDate);

    final post post = postList.get(position);

    String catId = post.getCategory();
    String mys = "SELECT catname FROM categories WHERE id = '" + catId + "'";

    Cursor dd = mDba.rawQuery(mys, null);
    dd.moveToFirst();
    String rd = dd.getString(0);

    textViewTitle.setText(post.getTitle());
    textViewCategory.setText("Type: " + rd);
    textViewPrice.setText("Price: " + post.getPrice());
    textViewJoinDate.setText(post.getJoiningdate());

//User Position
    try {
        view.findViewById(R.id.userPosition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPost(post);
            }
        });
    } catch (NullPointerException e) {

    }

    return view;
}


//-------------------------------------------------------------------------------
//  View Post Code -When cilck by rendom user -- start
private void viewPost(post post)
        {
        int postId = post.getId();


        String sentence = "String"+mCtx;
        String search  = "usavedads";

        if ( sentence.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {

        loadPosta(postId);

        } else {

        loadPostb(postId);

        }
        }

private void loadPosta(int id)
        {
        String postid = String.valueOf(id);

        Intent intent = new Intent(mCtx.getApplicationContext(),randomPost.class);
        Bundle bundle = new Bundle();
        bundle.putString("post_id",postid);
        intent.putExtras(bundle);
        mCtx.startActivity(intent);

        }

private void loadPostb(int id)
        {
        String postid = String.valueOf(id);

        Intent intent = new Intent(mCtx.getApplicationContext(),thispost.class);
        Bundle bundle = new Bundle();
        bundle.putString("post_id",postid);
        intent.putExtras(bundle);
        mCtx.startActivity(intent);

        }

//  view Post Code ---- End

//------------------------------------------------------------------------------
// Code to get all Post of User --- Start

private void loadPostFromDatabase()
        {
        String sql = "SELECT id, Title, Details,Address,Price,joiningdate FROM postb";
        Cursor cursor = mDba.rawQuery(sql,null);

        if(cursor.moveToFirst())

        postList.clear();
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

        notifyDataSetChanged();
        }

        // Code to get all Post of User --- End
//-------------------------------------------------------------------------------
        }


