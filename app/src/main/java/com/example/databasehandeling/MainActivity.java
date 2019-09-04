package com.example.databasehandeling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databasehandeling.database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtPassword;
    Button btnadd, btnselect, btndelete, btnupdate, btnsign;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.editUserName);
        txtPassword = findViewById(R.id.editPassword);

        btnselect = findViewById(R.id.btnSelectAll);
        btnadd = findViewById(R.id.btnAdd);
        btnsign = findViewById(R.id.btnSignIn);
        btndelete = findViewById(R.id.btnDelete);
        btnupdate = findViewById(R.id.btnUpdate);

        context.getApplicationContext();

        final DBHelper dbHelper = new DBHelper(this);

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List data = dbHelper.readInfo();

                for(int i = 0; i < ((List)data).size(); i++){
                    Log.v("Data"+i ,data.get(i).toString());
                }
                Toast.makeText(getApplicationContext(),"Data Added Successfully", Toast.LENGTH_LONG);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addInfo(txtUser.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(getApplicationContext(),"Data Added successfully!",Toast.LENGTH_LONG);
            }
        });

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.readInfo(txtUser.getText().toString(), txtPassword.getText().toString(), context);
                Toast.makeText(getApplicationContext(), "SingedIn successfully!", Toast.LENGTH_LONG);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteInfo(txtUser.getText().toString());
                Toast.makeText(getApplicationContext(), "Deleted User successfully!", Toast.LENGTH_LONG);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateInfo(txtUser.getText().toString(),txtPassword.getText().toString());
                Toast.makeText(getApplicationContext(), "Updated User details successfully!", Toast.LENGTH_LONG);
            }
        });
    }
}
