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

public class Add_Notice extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNotice;

    private Button buttonAddNotice;
    private Button buttonViewNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__notice);

        editTextNotice = (EditText) findViewById(R.id.editTextNotice);

        buttonAddNotice = (Button) findViewById(R.id.buttonAddnotice);
        buttonViewNotice = (Button) findViewById(R.id.buttonViewNotice);
        buttonAddNotice.setOnClickListener(this);
        buttonViewNotice.setOnClickListener(this);

    }

    private void addNotices(){

        final String message = editTextNotice.getText().toString().trim();

        class AddNotices extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Add_Notice.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Add_Notice.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NOTICE,message);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_NOTICE, params);
                return res;
            }
        }

        AddNotices ae = new AddNotices();
        ae.execute();
    }


    @Override
    public void onClick(View v) {

        if(v==buttonAddNotice)
        {
         addNotices();
        }
        if(v==buttonViewNotice)
        {

            startActivity(new Intent(this,View_Added_notice.class));

        }


    }
}
