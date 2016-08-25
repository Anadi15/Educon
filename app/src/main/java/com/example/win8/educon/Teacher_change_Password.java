package com.example.win8.educon;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Teacher_change_Password extends AppCompatActivity implements View.OnClickListener {

    EditText TeacherRegMail,TeacherOldPass,TeacherNewPass;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_change__password);

       TeacherRegMail=(EditText)findViewById(R.id.editTextTeahcerRegEmail);
       TeacherOldPass=(EditText)findViewById(R.id.editTextTeacherOldpass);
        TeacherNewPass=(EditText)findViewById(R.id.editTextTeacherNewPass);

 button=(Button)findViewById(R.id.buttonTeacherChangPassSub);
    button.setOnClickListener(this);
    }

 private void changeTeacherPassword() {
        final String email =TeacherRegMail.getText().toString().trim();
        final String oldpassword =TeacherOldPass.getText().toString().trim();
        final String newpassword =TeacherNewPass.getText().toString().trim();


        class changeStudPassword extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Teacher_change_Password.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Teacher_change_Password.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();


                hashMap.put(Config.KEY_EMP_EMAIL, email);
                hashMap.put(Config.KEY_TEACHER_OLDPASSWORD, oldpassword);
                hashMap.put(Config.KEY_TEACHER_NEWPASSWORD, newpassword);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_CHANGE_TEACHERPASS, hashMap);

                return s;
            }
        }

        changeStudPassword ue = new changeStudPassword();
        ue.execute();
    }







    @Override
    public void onClick(View v) {
if(v==button)
{
    changeTeacherPassword();

}


    }
}
