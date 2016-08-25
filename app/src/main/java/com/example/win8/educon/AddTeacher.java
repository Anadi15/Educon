package com.example.win8.educon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddTeacher extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextTeacherName;
    private EditText editTextTeacherUsername;
    private EditText editTextTeacherPassword;
    private EditText editTextTeacheremail;

    private Button buttonAddTeacher;
    private Button buttonViewTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        editTextTeacherName = (EditText) findViewById(R.id.editTextTname);
        editTextTeacherUsername = (EditText) findViewById(R.id.editTextTusername);
        editTextTeacherPassword = (EditText) findViewById(R.id.editTextTpassword);
        editTextTeacheremail=(EditText) findViewById(R.id.editTextTemail) ;

        buttonAddTeacher = (Button) findViewById(R.id.buttonAddTeacher);
        buttonViewTeacher = (Button) findViewById(R.id.buttonViewTeacher);
        buttonAddTeacher.setOnClickListener(this);
        buttonViewTeacher.setOnClickListener(this);

    }

    private void addTeacher(){

        final String name = editTextTeacherName.getText().toString().trim();
        final String username = editTextTeacherUsername.getText().toString().trim();
        final String password = editTextTeacherPassword.getText().toString().trim();
        final String email = editTextTeacheremail.getText().toString().trim();

        class AddTeachers extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddTeacher.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddTeacher.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_USERNAME,username);
                params.put(Config.KEY_EMP_PASSWORD,password);
                params.put(Config.KEY_EMP_EMAIL,email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADDTEACHER, params);
                return res;
            }
        }

        AddTeachers ae = new AddTeachers();
        ae.execute();
    }


    @Override
    public void onClick(View v) {

        if(v==buttonAddTeacher)
        {
            addTeacher();
        }
    if(v==buttonViewTeacher)
    {

        startActivity(new Intent(this,ViewAllTeacher.class));

    }


    }
}
