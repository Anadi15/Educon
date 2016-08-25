package com.example.win8.educon;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewTeacher extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextTeacherId;
    private EditText editTextTeacherName;
    private EditText editTextTeacherUserName;
    private EditText editTextTeacherPassword;
    private  EditText editTextTeahcerEmail;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teacher);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);

        editTextTeacherId = (EditText) findViewById(R.id.editTextTeacherID);
        editTextTeacherName = (EditText) findViewById(R.id.editTextTname);
        editTextTeacherUserName = (EditText) findViewById(R.id.editTextTusername);
        editTextTeacherPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextTeahcerEmail=(EditText)findViewById(R.id.editTextTemail);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdateTeacher);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteTeacher);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextTeacherId.setText(id);

        getTeacher();

    }
    private void getTeacher(){
        class GetTeacher extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTeacher.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTeacher(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequestParam(Config.URL_GET_TEACHER,id);
                return s;
            }
        }
        GetTeacher ge = new GetTeacher();
        ge.execute();
    }
    private void showTeacher(String json){
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            JSONArray result = jsonObject1.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String username = c.getString(Config.TAG_USERNAME);
            String password = c.getString(Config.TAG_PASSWORD);
            String email=c.getString(Config.TAG_EMAIL);



            editTextTeacherName.setText(name);
            editTextTeacherUserName.setText(username);
            editTextTeacherPassword.setText(password);
            editTextTeahcerEmail.setText(email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateTeacher(){
        final String name = editTextTeacherName.getText().toString().trim();
        final String username = editTextTeacherUserName.getText().toString().trim();
        final String password = editTextTeacherPassword.getText().toString().trim();
        final String email = editTextTeahcerEmail.getText().toString().trim();


        class UpdateTeacher extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTeacher.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewTeacher.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,id);
                hashMap.put(Config.KEY_EMP_NAME,name);
                hashMap.put(Config.KEY_EMP_USERNAME,username);
                hashMap.put(Config.KEY_EMP_PASSWORD,password);
                hashMap.put(Config.KEY_EMP_EMAIL,email);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_TEACHER,hashMap);

                return s;
            }
        }

        UpdateTeacher ue = new UpdateTeacher();
        ue.execute();
    }

    private void deleteTeacher(){
        class DeleteTeacher extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTeacher.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewTeacher.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_TEACHER, id);
                return s;
            }
        }

        DeleteTeacher de = new DeleteTeacher();
        de.execute();
    }

    private void confirmDeleteTeacher(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Teacher?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteTeacher();
                        startActivity(new Intent(ViewTeacher.this,ViewAllTeacher.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    @Override
    public void onClick(View v) {

        if(v == buttonUpdate){
            updateTeacher();
        }

        if(v == buttonDelete){
            confirmDeleteTeacher();
        }
    }
    }

