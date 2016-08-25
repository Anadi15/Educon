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

public class TeacherPersonalDetail2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextTeacherId;
    private EditText editTextTeacherName;
    private  EditText editTextTeacherEmail;
    private EditText editTextTeacherFatherName;
    private EditText editTextTeacherMotherName;
    private EditText editTextTeacherGender;
    private EditText editTextTeacherBdate;
    private EditText editTextTeacherBmonth;
    private EditText editTextTeacherByear;
    private EditText editTextTeacherAddress;
    private EditText editTextTeacherEnrollemnt;
    private Button buttonUpdateTeacher;
    private Button buttonDeleteTeacher;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_personal_detail2);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.TEACHER_ID);

        editTextTeacherId=(EditText)findViewById(R.id.editTextStudId);
        editTextTeacherName=(EditText)findViewById(R.id.editTextStudName);
        editTextTeacherEmail=(EditText)findViewById(R.id.editTextStudEmail);
        editTextTeacherFatherName=(EditText)findViewById(R.id.editTextStudFather);
        editTextTeacherMotherName=(EditText)findViewById(R.id.editTextStudMother);
        editTextTeacherGender=(EditText)findViewById(R.id.editTextStudGender);
        editTextTeacherBdate=(EditText)findViewById(R.id.editTextStudDate);
        editTextTeacherBmonth=(EditText)findViewById(R.id.editTextStudMonth);
        editTextTeacherByear=(EditText)findViewById(R.id.editTextYear);
        editTextTeacherAddress=(EditText)findViewById(R.id.editTextStudAddress);
        editTextTeacherEnrollemnt=(EditText)findViewById(R.id.editTextStudEnrollment);

        buttonUpdateTeacher = (Button) findViewById(R.id.buttonUpdate);
        buttonDeleteTeacher = (Button) findViewById(R.id.buttonDelete);

        buttonUpdateTeacher.setOnClickListener(this);
        buttonDeleteTeacher.setOnClickListener(this);

        editTextTeacherId.setText(id);

        getTeacher();
    }

    private void getTeacher(){
        class GetTeacher extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TeacherPersonalDetail2.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(Config.URL_GET_TEACHERS,id);
                return s;
            }
        }
        GetTeacher ge = new GetTeacher();
        ge.execute();
    }

    private void showTeacher(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String email = c.getString(Config.TAG_EMAIL);
            String father_name = c.getString(Config.TAG_FATHERNAME);
            String mother_name = c.getString(Config.TAG_MOTHERNAME);
            String gender = c.getString(Config.TAG_GENDER);
            String date = c.getString(Config.TAG_DATE);
            String month = c.getString(Config.TAG_MONTH);
            String year = c.getString(Config.TAG_YEAR);
            String address = c.getString(Config.TAG_ADDRESS);
            String enrollment = c.getString(Config.TAG_ENROLLMENT);


            editTextTeacherName.setText(name);
            editTextTeacherEmail.setText(email);
            editTextTeacherFatherName.setText(father_name);
            editTextTeacherMotherName.setText(mother_name);
            editTextTeacherGender.setText(gender);
            editTextTeacherBdate.setText(date);
            editTextTeacherBmonth.setText(month);
            editTextTeacherByear.setText(year);
            editTextTeacherAddress.setText(address);
            editTextTeacherEnrollemnt.setText(enrollment);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateteacher(){
        final String name = editTextTeacherName.getText().toString().trim();
        final String email = editTextTeacherEmail.getText().toString().trim();
        final String father_name = editTextTeacherFatherName.getText().toString().trim();
        final String mother_name = editTextTeacherMotherName.getText().toString().trim();
        final String gender = editTextTeacherGender.getText().toString().trim();
        final String date = editTextTeacherBdate.getText().toString().trim();
        final String month = editTextTeacherBmonth.getText().toString().trim();
        final String year = editTextTeacherByear.getText().toString().trim();
        final String address = editTextTeacherAddress.getText().toString().trim();
        final String enrollment = editTextTeacherEnrollemnt.getText().toString().trim();

        class UpdateTeacher extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TeacherPersonalDetail2.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TeacherPersonalDetail2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_TEACHER_ID,id);
                hashMap.put(Config.KEY_TEACHER_NAME,name);
                hashMap.put(Config.KEY_EMAIL ,email);
                hashMap.put(Config.KEY_FATHERNAME,father_name);
                hashMap.put(Config.KEY_MOTHERNAME,mother_name);
                hashMap.put(Config.KEY_GENDER,gender);
                hashMap.put(Config.KEY_DATE,date);
                hashMap.put(Config.KEY_MONTH,month);
                hashMap.put(Config.KEY_YEAR,year);
                hashMap.put(Config.KEY_ADDRESS,address);
                hashMap.put(Config.KEY_ENROLLMENT,enrollment);

                RequestHandler2 rh = new RequestHandler2();

                String s = rh.sendPostRequest(Config.URL_UPDATE_TEACHERS,hashMap);

                return s;
            }
        }

        UpdateTeacher ue = new UpdateTeacher();
        ue.execute();
    }

    private void deleteteacher(){
        class DeleteTeacher extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TeacherPersonalDetail2.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TeacherPersonalDetail2.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_TEACHERS, id);
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
                        deleteteacher();
                        startActivity(new Intent(TeacherPersonalDetail2.this,ViewAllTeacher.class));
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
        if(v == buttonUpdateTeacher){
            updateteacher();
        }

        if(v == buttonDeleteTeacher){
            confirmDeleteTeacher();
        }
    }
}