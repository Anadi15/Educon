package com.example.win8.educon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Main22Activity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextemail;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        //Initializing views
        editTextName = (EditText) findViewById(R.id.editTextsName);
        editTextUsername = (EditText) findViewById(R.id.editTextsUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextsPassword);
        editTextemail=(EditText) findViewById(R.id.editTextsEmail) ;

        buttonAdd = (Button) findViewById(R.id.buttonAddstud);
        buttonView = (Button) findViewById(R.id.buttonViewStud);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Adding an employee
    private void addStudent(){

        final String name = editTextName.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();

        class AddStudent extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main22Activity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Main22Activity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_USERNAME,username);
                params.put(Config.KEY_EMP_PASSWORD,password);
                params.put(Config.KEY_EMP_EMAIL,email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddStudent ae = new AddStudent();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addStudent();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllStudent.class));
        }
    }
}