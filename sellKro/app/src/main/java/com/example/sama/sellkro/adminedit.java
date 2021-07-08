package com.example.sama.sellkro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class adminedit extends Activity implements View.OnClickListener
{

    SQLiteDatabase mDba;
    EditText editTextLname,editTextMobile,
            editTextPass,editTextCpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminedit);

        mDba = openOrCreateDatabase(signup.DATABASE_NAME,MODE_PRIVATE,null);


        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        editTextCpass = (EditText)findViewById(R.id.editTextCpass);

        findViewById(R.id.updateBtn).setOnClickListener(this);
        loadAdmin();
    }

    private void loadAdmin() {
        String sql = "SELECT * FROM adminramiz WHERE Mobile = 8980140878 ";
        Cursor cursor = mDba.rawQuery(sql,null);

        cursor.moveToFirst();


        String lname = cursor.getString(1);
        String Mobile = cursor.getString(2);

        TextView v2 = (TextView)findViewById(R.id.editTextLname);
        TextView v3 = (TextView)findViewById(R.id.editTextMobile);

        v2.setText(lname);
        v3.setText(Mobile);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.updateBtn:
                updateAdmin();
                break;
        }
    }

    private void updateAdmin()
    {
        String Lname = editTextLname.getText().toString().trim();
        String Mobilet = editTextMobile.getText().toString().trim();
        String Pass = editTextPass.getText().toString().trim();
        String Cpass = editTextCpass.getText().toString().trim();


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

        String sql = "UPDATE adminramiz SET Lname = ?, Pass = ? WHERE Mobile = 8980140878";
        mDba.execSQL(sql,new String[]{Lname,Pass});

        Toast.makeText(getApplicationContext(), "Admin Updated Successfully.....", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,adminmenu.class));
        finish();

    }

    public void adminmenu(View view)
    {
        startActivity(new Intent(adminedit.this,adminmenu.class));
        finish();
    }


//-------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adminedit, menu);
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
