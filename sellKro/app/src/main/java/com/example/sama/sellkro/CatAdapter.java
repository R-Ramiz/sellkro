package com.example.sama.sellkro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sama on 4/4/2021.
 */
public class CatAdapter extends ArrayAdapter<CatClass>
{

    Context mCtx;
    int layoutRes;
    List<CatClass> catList;
    SQLiteDatabase mDba;
    public CatAdapter(Context mCtx,int layoutRes,List<CatClass> catList,SQLiteDatabase mDba)
    {
        super(mCtx,layoutRes,catList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.catList = catList;
        this.mDba = mDba;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes,null);

        TextView textViewName = (TextView)view.findViewById(R.id.textViewName);

        final CatClass cat = catList.get(position);
        textViewName.setText(cat.getCatname());


try {
        view.findViewById(R.id.nxtpost).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer id = cat.getId();
                String catId = String.valueOf(id);

                Intent intent = new Intent(mCtx.getApplicationContext(),postList.class);
                Bundle bundle = new Bundle();
                bundle.putString("cat_id",catId);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }


        });
}catch (NullPointerException e)
{

}

        try {
            view.findViewById(R.id.thisId).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    Integer id = cat.getId();
                    String catId = String.valueOf(id);

                    Toast.makeText(mCtx.getApplicationContext(),catId,Toast.LENGTH_SHORT).show();

                }


            });
        }catch (NullPointerException e)
        {

        }




        try
{
    TextView textViewJoinDate = (TextView)view.findViewById(R.id.textViewJoiningDate);
    textViewJoinDate.setText(cat.getRegdate());

    view.findViewById(R.id.buttonDeleteEmployee).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            deleteUser(cat);
        }
    });



}catch (NullPointerException e)
    {


    }
        return view;
}


    private void test()

    {
        Toast.makeText(mCtx,"Done", Toast.LENGTH_LONG).show();
    }


    private void deleteUser(final CatClass cat)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure To Delete ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String sql = "DELETE FROM categories WHERE id = ?";
                mDba.execSQL(sql,new Integer[]{cat.getId()});

                String sqlb = "DELETE FROM postb WHERE Category = ?";
                mDba.execSQL(sqlb,new Integer[]{cat.getId()});

                String sqlc = "DELETE FROM saved WHERE catid = ?";
                mDba.execSQL(sqlc,new Integer[]{cat.getId()});



                loadb();
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


//    public void nexttoPost (View view)
//    {
//       int id = view.getId();
//       String kj = String.valueOf(id);
//
//    }

    private void loadb()
    {

    String sql = "SELECT * FROM categories";
    Cursor cursor = mDba.rawQuery(sql,null);

    if(cursor.moveToFirst())
        catList.clear();
        do{

            catList.add(new CatClass(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    )
            );
        }while (cursor.moveToNext());

        notifyDataSetChanged();
    }
}
