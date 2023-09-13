package com.example.sqlproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword,fname,lname;
    Button signup,signin;
    DBHelper DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        username =(EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.signup);
        signin =(Button)findViewById(R.id.signin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();

                if (lastname.equals("") || firstname.equals("") || user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkUserName(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass,firstname,lastname);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}