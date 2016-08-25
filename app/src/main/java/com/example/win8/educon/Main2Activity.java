package com.example.win8.educon;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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




public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private CheckBox ch;
    private Spinner sp;

    private String s11;

    private Button buttonRegister;
    private static final String REGISTER_URL = "http://anadi.esy.es/register.php";
   // private static final String TEACHER_REGISTER_URL ="http://10.0.2.2/Educondb/teacher_reg.php";
   private static final String TEACHER_REGISTER_URL ="http://anadi.esy.es/teacher_reg.php";

   // private static final String REGISTER_URL ="http://10.0.2.2/Educondb/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextName = (EditText) findViewById(R.id.editText3);
        editTextUsername = (EditText) findViewById(R.id.editText5);
        editTextPassword = (EditText) findViewById(R.id.editText6);
        editTextEmail = (EditText) findViewById(R.id.editText7);

        buttonRegister = (Button) findViewById(R.id.button4);
        ch=(CheckBox)findViewById(R.id.checkBox2);
        buttonRegister.setOnClickListener(this);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

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
    }
    private boolean isValid_name(String username)
    {
        if (username != null && username.length() > 0) {
            return true;
        }
        return false;


    }

    private boolean  isValidMail(String mail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
        //if (mail != null && mail.length() > 0) {
        //  return true;




    }
    private boolean isValidPassword(String password)
    {
        if (password != null && password.length() > 2) {
            return true;
        }
        return false;


    }

    private boolean isValidUser_name(String username) {
        if (username != null && username.length() > 0) {
            return true;
        }
        return false;


    }
    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();

        sp=(Spinner)findViewById(R.id.spinner2);
        s11=sp.getSelectedItem().toString();

//        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            //@Override
  //          public void onClick(View arg0) {
     //           final String password = editTextPassword.getText().toString();
       //         final String email = editTextEmail.getText().toString();
         //       final String name = editTextName.getText().toString();
           //     final String username = editTextUsername.getText().toString();
                if (!isValidUser_name(username)) {
                    editTextUsername.setError("User Name can't be blanked");
                } else if (!isValid_name(name)) {
                    editTextName.setError("Name can't be blanked");
                } else if (!isValidPassword(password)) {
                    editTextPassword.setError("Invalid Password");
                } else if (!isValidMail(email)) {
                    editTextEmail.setError("Invalid Email");
                }

else{

        register(name, username, password, email);


                     editTextName.setText("");
        editTextUsername.setText("");
        editTextPassword.setText("");
        editTextEmail.setText("");



                }

        //});






    }

    private void register(String name, String username, String password, String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();


                loading = ProgressDialog.show(Main2Activity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);

                if(s11.equals("Student"))
                {
                    String result = ruc.sendPostRequest(REGISTER_URL,data);
                    return  result;

                }
                else if(s11.equals("Teacher")) {

                    String result = ruc.sendPostRequest(TEACHER_REGISTER_URL, data);

                    return result;

                }

                else
                {
                    return "Please select your Login Type(Student or Teacher)";

                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email);
    }
}