package com.example.win8.educon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_welcomepage extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
    b1=(Button) findViewById(R.id.buttonStudentRegis);
b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.buttonTeacherRegDetail);
   b2.setOnClickListener(this);

        b5=(Button)findViewById(R.id.button11);
        b3=(Button)findViewById(R.id.button9);
        b3.setOnClickListener(this);
        b4=(Button)findViewById(R.id.button8);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1) {
            Intent intent = new Intent(getApplicationContext(), Main22Activity.class);
            startActivity(intent);
        }

        if(v==b2)
        {
            Intent intent1 = new Intent(getApplicationContext(), AddTeacher.class);
            startActivity(intent1);
        }

        if(v==b3)
        {

            Intent intent2 = new Intent(getApplicationContext(), StudentPersonalDetail.class);
            startActivity(intent2);

        }
        if(v==b4)
        {

            Intent intent3 = new Intent(getApplicationContext(),TeacherPersonalDetail.class);
            startActivity(intent3);

        }

        if(v==b5)
        {

            Intent intent4 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent4);
            finish();

        }

        }
}
