package com.example.sama.sellkro;

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

/**
 * Created by Ramiz on 4/27/2021.
 */





/**
 * Created by sama on 4/9/2021.
 */
public class PostAd extends ArrayAdapter<post>
{
    Context mCtx;
    int layoutRes;
    List<post> postList;
    ListView listView;
    SQLiteDatabase mDba;

    public PostAd(Context mCtx,int layoutRes,List<post> postList,SQLiteDatabase mDba)
    {
        super(mCtx,layoutRes,postList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.postList = postList;
        this.mDba = mDba;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes,null);

        TextView textViewTitle = (TextView)view.findViewById(R.id.textViewTitle);
        TextView textViewCategory = (TextView)view.findViewById(R.id.textViewCategory);
        TextView textViewPrice = (TextView)view.findViewById(R.id.textViewPrice);
        TextView textViewJoinDate = (TextView)view.findViewById(R.id.textViewJoiningDate);

        final post post = postList.get(position);

        String catId = post.getCategory();
        String mys = "SELECT catname FROM categories WHERE id = '"+catId+"'";

        Cursor dd = mDba.rawQuery(mys,null);
        dd.moveToFirst();
        String rd = dd.getString(0);

        textViewTitle.setText(post.getTitle());
        textViewCategory.setText("Type: "+rd);
        textViewPrice.setText("Price: "+post.getPrice());
        textViewJoinDate.setText(post.getJoiningdate());




            try {
                view.findViewById(R.id.buttonDeletePost).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        deletePostAdmin(post);
                    }

                });
            }catch (NullPointerException e)
            {

            }



//User Position
        try {
            view.findViewById(R.id.userPosition).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPost(post);
                }
            });
        }catch (NullPointerException e)
        {

        }

        return view;
    }

//------------------------------------------------------------------------------



    private void deletePostAdmin(final post post)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String sqlb = "SELECT Seller FROM postb WHERE id = "+post.getId();
                Cursor rd = mDba.rawQuery(sqlb,null);

                if (rd.moveToFirst());
                int tg = rd.getInt(0);
                String ff = String.valueOf(tg);

                String sql = "DELETE FROM postb WHERE id = ?";
                mDba.execSQL(sql,new Integer[]{post.getId()});



                try
                {
                    Intent intent = new Intent(mCtx.getApplicationContext(),allads.class);
                    intent.putExtra("uid",ff);

                    mCtx.startActivity(intent);
                    ((allads)mCtx).finish();

                }
                catch (CursorIndexOutOfBoundsException e)
                {

                    Intent intent = new Intent(mCtx.getApplicationContext(),userProfile.class);
                    mCtx.startActivity(intent);
                    ((usavedads)mCtx).finish();

                }


            }



        });

        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //-------------------------------------------------------------------------------
//  View Post Code -When cilck by rendom user -- start
    private void viewPost(post post)
    {
        int postId = post.getId();


        String sentence = "String"+mCtx;
        String search  = "postList";
        String search2  = "allads";
        String search3  = "usavedads";

        if ( sentence.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {

            loadPosta(postId);

        } else if (sentence.toLowerCase().indexOf(search2.toLowerCase()) != -1)
        {

            Log.d("this","allads");
            String postid = String.valueOf(postId);

            Intent intent = new Intent(mCtx.getApplicationContext(), randomPostadmin.class);
            Bundle bundle = new Bundle();
            bundle.putString("post_id", postid);
            intent.putExtras(bundle);
            mCtx.startActivity(intent);



        }else if (sentence.toLowerCase().indexOf(search3.toLowerCase()) != -1){


            Log.d("this","allads");
            String postid = String.valueOf(postId);

            Intent intent = new Intent(mCtx.getApplicationContext(), randomPost.class);
            Bundle bundle = new Bundle();
            bundle.putString("post_id", postid);
            intent.putExtras(bundle);
            mCtx.startActivity(intent);



        }else
        {
            loadPostb(postId);
        }
    }

    private void loadPosta(int id)
    {


        String postid = String.valueOf(id);
        Log.d("this","this");
        Intent intent = new Intent(mCtx.getApplicationContext(), randomPost.class);
        Bundle bundle = new Bundle();
        bundle.putString("post_id", postid);
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
