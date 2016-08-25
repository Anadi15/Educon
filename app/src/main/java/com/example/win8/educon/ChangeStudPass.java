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

public class ChangeStudPass extends AppCompatActivity implements View.OnClickListener {
    EditText RegisEmail,StudOldPass,StudNewPass;
Button Studchangpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_stud_pass);
        RegisEmail=(EditText)findViewById(R.id.editTextRegEmail);
        StudOldPass=(EditText)findViewById(R.id.editTextStudOldPass);
        StudNewPass=(EditText)findViewById(R.id.editTextStudNewPass);

      Studchangpass=(Button)findViewById(R.id.buttonChangeStudPass);
        Studchangpass.setOnClickListener(this);
    }


       private void changeStudPassword() {
        final String email = RegisEmail.getText().toString().trim();
        final String oldpassword =StudOldPass.getText().toString().trim();
        final String newpassword = StudNewPass.getText().toString().trim();


        class changeStudPassword extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChangeStudPass.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ChangeStudPass.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();


                hashMap.put(Config.KEY_EMP_EMAIL, email);
                hashMap.put(Config.KEY_STUDENT_OLDPASSWORD, oldpassword);
                hashMap.put(Config.KEY_STUDENT_NEWPASSWORD, newpassword);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_CHANGE_STUDENTPASS, hashMap);

                return s;
            }
        }

        changeStudPassword ue = new changeStudPassword();
        ue.execute();
    }



    @Override
    public void onClick(View v) {
        changeStudPassword();
    }
}
