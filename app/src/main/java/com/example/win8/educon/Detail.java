package com.example.win8.educon;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Detail extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextfathername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    private EditText editTextMothername;
    private EditText editTextAddress;
    private EditText editTextEnrollment;

    private Spinner spinnerGender;
    private Spinner spinnerDate;
    private Spinner spinnerMonth;
    private Spinner spinnerYear;

    public static final String UPLOAD_URL = "http://anadi.esy.es/upload.php";

    // public static final String UPLOAD_URL = "http://10.0.2.2/PhotoUpload/upload.php";
    public static final String UPLOAD_KEY = "image";



    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;

    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;
    //   private static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/register.php";
    private static final String REGISTER_URL = "http://anadi.esy.es/newregister.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextName = (EditText) findViewById(R.id.editTexttName);
        editTextEmail = (EditText) findViewById(R.id.editTexttEmail);
        editTextfathername=(EditText)findViewById(R.id.editTexttFathername);
        editTextMothername=(EditText)findViewById(R.id.editTexttMothername);
        spinnerGender=(Spinner)findViewById(R.id.spinnertGender);
        spinnerDate=(Spinner)findViewById(R.id.spinnertDate);
        spinnerMonth=(Spinner)findViewById(R.id.spinnertMonth);
        spinnerYear=(Spinner)findViewById(R.id.spinnertYear);
        editTextAddress=(EditText)findViewById(R.id.editTexttAddress);
        editTextEnrollment=(EditText)findViewById(R.id.editTextEnrollment);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);



        imageView = (ImageView) findViewById(R.id.timageView);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imageView.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }



    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Detail.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put(UPLOAD_KEY, uploadImage);
                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }


    private void confirmAddStudent(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Is this Your final Submit and you uploaded your pic??");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                registerUser();

                        //startActivity(new Intent(ViewStudent.this,ViewAllStudent.class));
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



        switch (v.getId())
        {
            case R.id.timageView:
                showFileChooser();
                break;
            case R.id.buttonRegister:
                confirmAddStudent();
                break;
            case R.id.buttonUpload:
                uploadImage();
                break;


        }
        // if(v == buttonRegister){
        //   registerUser();
        //}
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String fathername=editTextfathername.getText().toString().trim().toLowerCase();
        String mothername=editTextMothername.getText().toString().trim().toLowerCase();
        String gender=spinnerGender.getSelectedItem().toString();
        String date=spinnerDate.getSelectedItem().toString();
        String month=spinnerMonth.getSelectedItem().toString();
        String year=spinnerYear.getSelectedItem().toString();
        String address=editTextAddress.getText().toString();
        String enrollment=editTextEnrollment.getText().toString();
        register(name,email,fathername,mothername,gender,date,month,year,address,enrollment);
    }

    private void register(String name, String email, String fathername,String  mothername,String gender,String date,String month,String year,String address,String enrollment) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Detail.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
//name,email,fathername,mothername,gender,date,month,year,address,enrollment
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("email",params[1]);
                data.put("fathername",params[2]);
                data.put("mothername",params[3]);
                data.put("gender",params[4]);
                data.put("date",params[5]);
                data.put("month",params[6]);
                data.put("year",params[7]);
                data.put("address",params[8]);
                data.put("enrollment",params[9]);



                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,email,fathername,mothername,gender,date,month,year,address,enrollment);
    }

}
