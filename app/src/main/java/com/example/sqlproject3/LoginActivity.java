package com.example.sqlproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public  EditText username, password;
    Button signin;
    DBHelper DB;
    public static String user1 = "b";

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // EditText u   = (EditText)findViewById(R.id.username);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.signin);
        DB = new DBHelper(this);
        user1 = username.getText().toString();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                user1 = username.getText().toString();
                if (user.equals("")|| pass.equals(""))
                    Toast.makeText(LoginActivity.this, "All Fields required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkUserNamePassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}