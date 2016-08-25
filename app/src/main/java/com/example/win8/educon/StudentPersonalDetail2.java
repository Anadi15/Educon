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

public class StudentPersonalDetail2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextStudentId;
    private EditText editTextStudentName;
    private  EditText editTextEmail;
    private EditText editTextStudentFatherName;
    private EditText editTextStudentMotherName;
    private EditText editTextStudentGender;
    private EditText editTextStudentBdate;
    private EditText editTextStudentBmonth;
    private EditText editTextStudentByear;
    private EditText editTextStudentAddress;
    private EditText editTextStudentEnrollemnt;
    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personal_detail2);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.STUD_ID);

        editTextStudentId=(EditText)findViewById(R.id.editTextStudId);
        editTextStudentName=(EditText)findViewById(R.id.editTextStudName);
        editTextEmail=(EditText)findViewById(R.id.editTextStudEmail);
        editTextStudentFatherName=(EditText)findViewById(R.id.editTextStudFather);
        editTextStudentMotherName=(EditText)findViewById(R.id.editTextStudMother);
        editTextStudentGender=(EditText)findViewById(R.id.editTextStudGender);
        editTextStudentBdate=(EditText)findViewById(R.id.editTextStudDate);
        editTextStudentBmonth=(EditText)findViewById(R.id.editTextStudMonth);
        editTextStudentByear=(EditText)findViewById(R.id.editTextYear);
        editTextStudentAddress=(EditText)findViewById(R.id.editTextStudAddress);
        editTextStudentEnrollemnt=(EditText)findViewById(R.id.editTextStudEnrollment);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextStudentId.setText(id);

        getStudent();
    }

    private void getStudent(){
        class GetStudent extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StudentPersonalDetail2.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showStudent(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequestParam(Config.URL_GET_STUD,id);
                return s;
            }
        }
        GetStudent ge = new GetStudent();
        ge.execute();
    }

    private void showStudent(String json){
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


            editTextStudentName.setText(name);
            editTextEmail.setText(email);
            editTextStudentFatherName.setText(father_name);
            editTextStudentMotherName.setText(mother_name);
            editTextStudentGender.setText(gender);
            editTextStudentBdate.setText(date);
            editTextStudentBmonth.setText(month);
            editTextStudentByear.setText(year);
            editTextStudentAddress.setText(address);
            editTextStudentEnrollemnt.setText(enrollment);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updatestudent(){
        final String name = editTextStudentName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String father_name = editTextStudentFatherName.getText().toString().trim();
        final String mother_name = editTextStudentMotherName.getText().toString().trim();
        final String gender = editTextStudentGender.getText().toString().trim();
        final String date = editTextStudentBdate.getText().toString().trim();
        final String month = editTextStudentBmonth.getText().toString().trim();
        final String year = editTextStudentByear.getText().toString().trim();
        final String address = editTextStudentAddress.getText().toString().trim();
        final String enrollment = editTextStudentEnrollemnt.getText().toString().trim();

        class UpdateStudent extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StudentPersonalDetail2.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(StudentPersonalDetail2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_STUD_ID,id);
                hashMap.put(Config.KEY_STUD_NAME,name);
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

                String s = rh.sendPostRequest(Config.URL_UPDATE_STUD,hashMap);

                return s;
            }
        }

        UpdateStudent ue = new UpdateStudent();
        ue.execute();
    }

    private void deletestudent(){
        class DeleteStudent extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StudentPersonalDetail2.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(StudentPersonalDetail2.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_STUD, id);
                return s;
            }
        }

        DeleteStudent de = new DeleteStudent();
        de.execute();
    }

    private void confirmDeleteStudent(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deletestudent();
                        startActivity(new Intent(StudentPersonalDetail2.this,StudentPersonalDetail.class));
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
            updatestudent();
        }

        if(v == buttonDelete){
            confirmDeleteStudent();
        }
    }
}