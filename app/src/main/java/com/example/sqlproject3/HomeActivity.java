package com.example.sqlproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button delete,update,select;
    DBHelper DB;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DB = new DBHelper(this);

        //activity = new LoginActivity();
        //username = activity.username.getText().toString();

        update  = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        select = (Button) findViewById(R.id.select);

        select.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor dis=DB.selectdata(LoginActivity.user1);
                        if(dis.getCount()==0) {
                            showmsg("error","no data found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();

                        while(dis.moveToNext()){
                            if(dis.getString(0).equals(LoginActivity.user1)){
                                buffer.append("First Name:"+dis.getString(2)+"\n");
                                buffer.append("Last Name:"+dis.getString(3)+"\n");
                                buffer.append("username:"+dis.getString(0)+"\n");
                                buffer.append("password:"+dis.getString(1)+"\n");

                                break;
                            }
                        }
                        //show selected data
                        showmsg("selected data",buffer.toString());
                    }
                }
        );

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor dis=DB.selectdata(LoginActivity.user1);
                        if(dis.getCount()==0) {
                            showmsg("error","no data found");
                            return;
                        }
                        boolean tv = true;
                        while(dis.moveToNext()){
                            if(dis.getString(0).equals(LoginActivity.user1)){
                                Integer deletedRows=DB.deletedata(dis.getString(0));
                                if(deletedRows>0) {
                                    Toast.makeText(HomeActivity.this,"data deleted successfuly",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    tv =false;
                                    break;
                                }
                            }
                        }
                        if(tv)
                            Toast.makeText(HomeActivity.this,"data not deleted successfuly",Toast.LENGTH_LONG).show();


                    }
                }
        );


        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
                        startActivity(intent);
                    }

                }
        );

    }

    public  void  showmsg(String Title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getTitle());
        builder.setMessage(message);
        builder.show();
    }



}