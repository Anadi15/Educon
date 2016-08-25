package com.example.win8.educon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener{

    public static final String USER_NAME = "USER_NAME";

    public static final String PASSWORD = "PASSWORD";
   // private static final String ADMIN_LOGIN_URL = "http://10.0.2.2/Educondb/admin.php";
   private static final String ADMIN_LOGIN_URL = "http://anadi.esy.es/admin.php";

    private static final String LOGIN_URL = "http://anadi.esy.es/login.php";
    private static final String TEACHER_LOGIN_URL = "http://anadi.esy.es/teacher_login.php";
   // private static final String REGISTER_URL ="http://10.0.2.2/Educondb/register.php";
    private EditText editTextUserName;
    private EditText editTextPassword;
    private CheckBox ch1;
    private Spinner s1;
    String sp;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.editText2);
        editTextPassword = (EditText) findViewById(R.id.editText4);
        ch1=(CheckBox) findViewById(R.id.checkBox);
        s1=(Spinner) findViewById(R.id.spinner);
        buttonLogin = (Button) findViewById(R.id.button3);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final String username = editTextUserName.getText().toString();
                if (!isValidUser_name(username)) {
                    editTextUserName.setError("Invalid Email");
                }

                final String pass = editTextPassword.getText().toString();
                if (!isValidPassword(pass)) {
                    editTextPassword.setError("Invalid Password");
                }

            }
        });




        sp=s1.getSelectedItem().toString();
        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //Show password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //Hide password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        buttonLogin.setOnClickListener(this);
    }

    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        sp=s1.getSelectedItem().toString();
        //if(s=="Admin")
    userLogin(username, password);
  }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){



                    if(sp.equals("Student")) {

                        Intent intent = new Intent(Login.this, Student_welcome.class);
                        intent.putExtra(USER_NAME, username);
                        startActivity(intent);

                        editTextUserName.setText("");
                        editTextPassword.setText("");
                    }
                else if(sp.equals("Teacher"))
                    {
                        Intent intent = new Intent(Login.this, teacher_welcome.class);
                        intent.putExtra(USER_NAME, username);
                        startActivity(intent);

                        editTextUserName.setText("");
                        editTextPassword.setText("");

                    }
                else if(sp.equals("Admin"))
                    {
                        Intent intent = new Intent(Login.this, Admin_welcomepage.class);
                        intent.putExtra(USER_NAME, username);

                        startActivity(intent);


                        editTextUserName.setText("");
                        editTextPassword.setText("");
                    }
                }
                else{
                    Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);


                RegisterUserClass ruc = new RegisterUserClass();
if(sp.equals("Student")) {

    String result = ruc.sendPostRequest(LOGIN_URL, data);

    return result;
}
                else if(sp.equals("Teacher"))
{
    String result = ruc.sendPostRequest(TEACHER_LOGIN_URL , data);

    return result;

}
else if(sp.equals("Admin"))
{
    String result = ruc.sendPostRequest(ADMIN_LOGIN_URL , data);


    return result;
}
                return "Please select your Login Type(Student or Teacher)";
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username, password);
    }

    @Override
    public void onClick(View v) {


        if(v == buttonLogin){


            login();
        }
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidUser_name(String username) {
     //   String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
   //             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

       // Pattern pattern = Pattern.compile(EMAIL_PATTERN);
       // Matcher matcher = pattern.matcher(email);
        //return matcher.matches();
        if (username != null && username.length() > 0) {
            return true;
        }
        return false;


    }

}


