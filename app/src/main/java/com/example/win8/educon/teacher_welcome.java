package com.example.win8.educon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class teacher_welcome extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1;
    private Button bd1,bd2,bd3,bd4,bd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_welcome);

        bd1=(Button)findViewById(R.id.button5);
        bd2=(Button)findViewById(R.id.button6);
        bd3=(Button)findViewById(R.id.buttonchngpass);
        bd4=(Button)findViewById(R.id.button7);
        bd5=(Button)findViewById(R.id.button10);


        bd1.setOnClickListener(this);
        bd2.setOnClickListener(this);
        bd3.setOnClickListener(this);
        bd4.setOnClickListener(this);
        bd5.setOnClickListener(this);

        textView1 = (TextView) findViewById(R.id.textViewteacherName);

        Intent intent = getIntent();

        String username = intent.getStringExtra(Login.USER_NAME);

        textView1.setText("Welcome User " + username);

    }

    @Override
    public void onClick(View v) {
        if(v == bd1)
        {
            Intent intent12=new Intent(getApplicationContext(),TeacherDetail.class);
            startActivity(intent12);

        }
    if(v== bd3)
    {
        Intent intent122=new Intent(getApplicationContext(),Teacher_change_Password.class);
        startActivity(intent122);


    }

        if(v== bd4)
        {
            Intent intent13=new Intent(getApplicationContext(),See_studentsEnrolled.class);
            startActivity(intent13);


        }

        if(v== bd5)
        {
            Intent intent13=new Intent(getApplicationContext(),Add_Notice.class);
            startActivity(intent13);


        }
        if(v== bd2)
        {
            Intent intent112=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent112);
finish();

        }



    }
}
