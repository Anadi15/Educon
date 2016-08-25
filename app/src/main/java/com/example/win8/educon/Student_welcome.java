package com.example.win8.educon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Student_welcome extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
private Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);
        textView = (TextView) findViewById(R.id.textViewUserName);
        b1=(Button)findViewById(R.id.buttonEnterdetail);
        b2=(Button)findViewById(R.id.buttonLog);
        b3=(Button)findViewById(R.id.buttonStudChangepassword);
        b4=(Button)findViewById(R.id.button13);
        Intent intent = getIntent();

        String username = intent.getStringExtra(Login.USER_NAME);

        textView.setText("Welcome User " + username);

b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    b3.setOnClickListener(this);
b4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == b1)
        {
            Intent intent1= new Intent(getApplicationContext(),Detail.class);
            startActivity(intent1);
        }
    else if(v == b2)
        {

            Intent intent2= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent2);
            finish();

        }
        else if(v == b3)
        {
            Intent intent3= new Intent(getApplicationContext(),ChangeStudPass.class);
            startActivity(intent3);

        }

        else if(v == b4)
        {

            Intent intent4= new Intent(getApplicationContext(),View_Added_notice.class);
            startActivity(intent4);

        }
    }
}
