package com.example.sqlproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText username, password,fname,lname;
    Button update;
    DBHelper DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        fname = (EditText)findViewById(R.id.fname1);
        lname = (EditText)findViewById(R.id.lname1);
        username = (EditText)findViewById(R.id.username1);
        password = (EditText)findViewById(R.id.password1);
        update = (Button)findViewById(R.id.update1);
        DB = new DBHelper(this);



        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isupdate=DB.updatedata(username.getText().toString(),password.getText().toString(),LoginActivity.user1,fname.getText().toString(),lname.getText().toString());
                        if(isupdate==true) {
                            Toast.makeText(UpdateActivity.this, "data updated successfuly", Toast.LENGTH_LONG).show();
                            LoginActivity.user1 = username.getText().toString();
                        }else{
                            Toast.makeText(UpdateActivity.this,"data not updated successfuly",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }
                        //Toast.makeText(UpdateActivity.this,"data not updated successfuly",Toast.LENGTH_LONG).show();

                    }

                }
        );
    }
}