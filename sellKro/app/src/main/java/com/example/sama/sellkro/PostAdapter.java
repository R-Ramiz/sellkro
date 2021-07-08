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
 * Created by sama on 4/9/2021.
 */
public class PostAdapter extends ArrayAdapter<post>
{
    Context mCtx;
    int layoutRes;
    List<post> postList;
    ListView listView;
    SQLiteDatabase mDba;

    public PostAdapter(Context mCtx,int layoutRes,List<post> postList,SQLiteDatabase mDba)
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

//Edit Button
try {

    view.findViewById(R.id.buttonEditPost).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updatePost(post);
        }

    });

}catch (NullPointerException e)
{

}

        String sentence = "String"+mCtx;
        String search2  = "allads";
       if(sentence.toLowerCase().indexOf(search2.toLowerCase()) != -1 )
       {

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


       }else
       {

           try {
               view.findViewById(R.id.buttonDeletePost).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       deletePost(post);
                   }
               });
           }catch (NullPointerException e)
           {

           }

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

//Update Post COde -- Start
    private void updatePost(final post post)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_post,null);
        builder.setView(view);

        final AlertDialog alertdialog = builder.create();
        alertdialog.show();

        final EditText editTextTitle = (EditText)view.findViewById(R.id.editTextTitle);
        final EditText editTextAddress = (EditText)view.findViewById(R.id.editTextAddress);
        final EditText editTextDetails = (EditText)view.findViewById(R.id.editTextDetails);
        final EditText editTextPrice = (EditText)view.findViewById(R.id.editTextPrice);

        editTextTitle.setText(post.getTitle());
        editTextAddress.setText(post.getAddress());
        editTextDetails.setText(post.getDetails());
        editTextPrice.setText(String.valueOf(post.getPrice()));

        view.findViewById(R.id.buttonUpdatePost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
           {
               Calendar cal = Calendar.getInstance();
               SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
               String joiningDate = sdf.format(cal.getTime());

                String Title = editTextTitle.getText().toString().trim();
                String Details = editTextDetails.getText().toString().trim();
                String Address = editTextAddress.getText().toString().trim();
                String Price = editTextPrice.getText().toString().trim();


                if(Title.isEmpty()){
                    editTextTitle.setError("Title Can't be Empty");
                    editTextTitle.requestFocus();
                    return;
                }


                if(Title.length()<3)
                {
                    editTextTitle.setError("Title Must be greter then 3  ");
                    editTextTitle.requestFocus();
                    return;
                }


                if (Address.isEmpty()){
                    editTextAddress.setError("Address cam't be Empty");
                    editTextAddress.requestFocus();
                    return;
                }

                if (Details.isEmpty()){
                    editTextDetails.setError("Details cam't be Empty");
                    editTextDetails.requestFocus();
                    return;
                }


                if(Details.length()<10)
                {
                    editTextDetails.setError("Details Must be greter then 10 Charecters  ");
                    editTextDetails.requestFocus();
                    return;
                }

                if (Price.isEmpty()){
                    editTextPrice.setError("Price can't be Empty");
                    editTextPrice.requestFocus();
                    return;
                }


                if (Price.length()>7){
                    editTextPrice.setError("Price must be less then 8 digit");
                    editTextPrice.requestFocus();
                    return;
                }


                if (Price.length()<2){
                    editTextPrice.setError("Price must be Greter then 100");
                    editTextPrice.requestFocus();
                    return;
                }



                String sql = "UPDATE postb SET Title = ?,Details = ?, Address = ?,Price = ?,joiningdate = ? WHERE id = ?";
                mDba.execSQL(sql,new String[]{Title,Details,Address,Price,joiningDate,String.valueOf(post.getId())});

                Toast.makeText(mCtx, "Post Updated", Toast.LENGTH_LONG).show();
               alertdialog.dismiss();
               try {

                   Intent intent = new Intent(mCtx.getApplicationContext(),umyads.class);
                   mCtx.startActivity(intent);
                   ((umyads)mCtx).finish();
//                   loadPostFromDatabase();
               }catch (CursorIndexOutOfBoundsException e)
               {
                   Intent intent = new Intent(mCtx.getApplicationContext(),umyads.class);
                   mCtx.startActivity(intent);
               }
           }

        });

    }
//Update Post COde -- End

    private void deletePostAdmin(final post post)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String sql = "DELETE FROM postb WHERE id = ?";
                mDba.execSQL(sql,new Integer[]{post.getId()});


                    try
                    {
                        Intent intent = new Intent(mCtx.getApplicationContext(),allads.class);
                        intent.putExtra("uid","-3");

                        mCtx.startActivity(intent);
                        ((allads)mCtx).finish();

                    }
                    catch (CursorIndexOutOfBoundsException e)
                    {

                        Intent intent = new Intent(mCtx.getApplicationContext(),userProfile.class);
                        mCtx.startActivity(intent);
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
//--------------------------------------------------------------------------------
    //  Delete Post Code ---- Start
    private void deletePost(final post post)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String sql = "DELETE FROM postb WHERE id = ?";
                mDba.execSQL(sql,new Integer[]{post.getId()});
            try
            {
                Intent intent = new Intent(mCtx.getApplicationContext(),umyads.class);
                mCtx.startActivity(intent);
                ((umyads)mCtx).finish();
//                loadPostFromDatabase();
            }
            catch (CursorIndexOutOfBoundsException e)
            {
                Log.d("dddsss","sddss");
                Intent intent = new Intent(mCtx.getApplicationContext(),umyads.class);
                mCtx.startActivity(intent);
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
//  Delete Post Code ---- End

//-------------------------------------------------------------------------------
    //  View Post Code -When cilck by rendom user -- start
    private void viewPost(post post)
    {
        int postId = post.getId();


        String sentence = "String"+mCtx;
        String search  = "postList";
        String search2  = "allads";

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



        }else {
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
