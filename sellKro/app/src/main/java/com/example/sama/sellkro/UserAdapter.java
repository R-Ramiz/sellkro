package com.example.sama.sellkro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
 * Created by sama on 3/28/2021.
 */
public class UserAdapter extends ArrayAdapter<user>
{
    Context mCtx;
    int layoutRes;
    List<user> userList;
    ListView listView;
    SQLiteDatabase mDba;
    public UserAdapter(Context mCtx,int layoutRes,List<user> userList,SQLiteDatabase mDba)
    {
        super(mCtx,layoutRes,userList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.userList = userList;
        this.mDba = mDba;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes,null);

        TextView textViewName = (TextView)view.findViewById(R.id.textViewName);
        TextView textViewDept = (TextView)view.findViewById(R.id.textViewDepartment);
        TextView textViewSalary = (TextView)view.findViewById(R.id.textViewSalary);
        TextView textViewJoinDate = (TextView)view.findViewById(R.id.textViewJoiningDate);

        final user user = userList.get(position);

        textViewName.setText(user.getFname());
        textViewDept.setText(user.getTown());
        textViewSalary.setText(user.getPass());
        textViewJoinDate.setText(user.getJoiningdate());

//        view.findViewById(R.id.buttonEditEmployee).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateEmployee(user);
//            }
//
//        });
//
//
//        view.findViewById(R.id.buttonDeleteEmployee).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                deleteUser(user);
//            }
//        });

        view.findViewById(R.id.userPosition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewUser(user);
            }
        });


        return view;
    }



    private void updateEmployee(final user user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_user,null);
        builder.setView(view);

        final AlertDialog alertdialog = builder.create();
        alertdialog.show();

        final EditText editTextFname = (EditText)view.findViewById(R.id.editTextFname);
        final EditText editTextLname = (EditText)view.findViewById(R.id.editTextFname);

        final EditText editTextAddress = (EditText)view.findViewById(R.id.editTextAddress);
        final EditText editTextTown = (EditText)view.findViewById(R.id.editTextTown);

        final EditText editTextPin = (EditText)view.findViewById(R.id.editTextPin);
        final EditText editTextPass = (EditText)view.findViewById(R.id.editTextPass);
        final EditText editTextCpass = (EditText)view.findViewById(R.id.editTextCpass);

         editTextFname.setText(user.getFname());
         editTextLname.setText(user.getLname());
         editTextAddress.setText(user.getAddress());
         editTextTown.setText(user.getTown());
         editTextPin.setText(String.valueOf(user.getPin()));
         editTextPass.setText(user.getPass());
         editTextCpass.setText(user.getCpass());




        view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String Fname = editTextFname.getText().toString().trim();
                String Lname = editTextLname.getText().toString().trim();
                String Address = editTextAddress.getText().toString().trim();
                String Town = editTextTown.getText().toString().trim();
                String Pin = editTextPin.getText().toString().trim();
                String Pass = editTextPass.getText().toString().trim();
                String Cpass = editTextCpass.getText().toString().trim();



                if(Fname.isEmpty()){
                    editTextFname.setError("First Name Can't be Empty");
                    editTextFname.requestFocus();
                    return;
                }


                if(Fname.length()<3)
                {
                    editTextFname.setError("First Name Must be greter then 3  ");
                    editTextFname.requestFocus();
                    return;
                }


                if(Fname.length()>15)
                {
                    editTextFname.setError("First Name < 15");
                    editTextFname.requestFocus();
                    return;
                }

                if (Lname.isEmpty()){
                    editTextLname.setError("Last Name cam't be Empty");
                    editTextLname.requestFocus();
                    return;
                }


                if(Lname.length()<3){
                    editTextLname.setError("First Name >=3");
                    editTextLname.requestFocus();
                    return;
                }


                if(Lname.length()>15){
                    editTextLname.setError("First Name Can't be Empty");
                    editTextLname.requestFocus();
                    return;
                }

                if (Address.isEmpty()){
                    editTextAddress.setError("Address cam't be Empty");
                    editTextAddress.requestFocus();
                    return;
                }

                if (Town.isEmpty()){
                    editTextTown.setError("Town cam't be Empty");
                    editTextTown.requestFocus();
                    return;
                }

                if (Pin.isEmpty()){
                    editTextPin.setError("Pin cam't be Empty");
                    editTextPin.requestFocus();
                    return;
                }


                if (Pin.length()>6){
                    editTextPin.setError("Pin must be 6 digit");
                    editTextPin.requestFocus();
                    return;
                }


                if (Pin.length()<6){
                    editTextPin.setError("Pin must be 6 digit");
                    editTextPin.requestFocus();
                    return;
                }

                if (Pass.isEmpty()){
                    editTextPass.setError("Pass  cam't be Empty");
                    editTextPass.requestFocus();
                    return;
                }


                if (Pass.length()<4){
                    editTextPass.setError("Pass  must be 4 digit or 8");
                    editTextPass.requestFocus();
                    return;
                }


                if (Pass.length()>8){
                    editTextPass.setError("Pass  must be 4 digit or 8");
                    editTextPass.requestFocus();
                    return;
                }

                if (Cpass.isEmpty()){
                    editTextCpass.setError("Confirm Password  cam't be Empty");
                    editTextCpass.requestFocus();
                    return;
                }


                if (!Cpass.equals(Pass))
                {
                    editTextCpass.setError("Password & Confirm Password  must be Same");
                    editTextCpass.requestFocus();
                    return;
                }



                String sql = "UPDATE userb SET Fname = ?, Lname = ?, Address = ?, Town = ?, Pin = ?, Pass = ?, Cpass = ? WHERE id = ?";
                mDba.execSQL(sql,new String[]{Fname,Lname,Address,Town,Pin,Pass,Cpass,String.valueOf(user.getId())});

                Toast.makeText(mCtx,"User Updated",Toast.LENGTH_LONG).show();
                alertdialog.dismiss();
                loadUserFromDatabase();

            }

        });

    }

    private void deleteUser(final user user)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            String sql = "DELETE FROM userb WHERE id = ?";
            mDba.execSQL(sql,new Integer[]{user.getId()});
                loadUserFromDatabase();
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

    private void viewUser(user user)
    {
      int userId = user.getId();

       loadUser(userId);
    }

    private void loadUser(int id)
    {

        String userid = String.valueOf(id);

        Intent intent = new Intent(mCtx.getApplicationContext(),userProfile.class);
        Bundle bundle = new Bundle();
        bundle.putString("user_id",userid);
        intent.putExtras(bundle);
        mCtx.startActivity(intent);

    }

    private void loadUserFromDatabase()
    {
        String sql = "SELECT id, Fname, Town, Mobile, joiningdate FROM userb";
        Cursor cursor = mDba.rawQuery(sql,null);

        if(cursor.moveToFirst())
            userList.clear();
            do{

                userList.add(new user(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getDouble(3),
                                cursor.getString(4)

                        )
                );
            }while (cursor.moveToNext());

        notifyDataSetChanged();
    }
}
