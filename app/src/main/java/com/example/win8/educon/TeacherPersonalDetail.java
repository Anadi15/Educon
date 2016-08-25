package com.example.win8.educon;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TeacherPersonalDetail extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_personal_detail);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showTeachers(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
  //              String enrollment=jo.getString(Config.TAG_ENROLLMENT);
                HashMap<String,String> teachers = new HashMap<>();
                teachers.put(Config.TAG_ID,id);
                teachers.put(Config.TAG_NAME,name);
//                teachers.put(Config.TAG_ENROLLMENT,enrollment);

                list.add(teachers);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TeacherPersonalDetail.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NAME},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TeacherPersonalDetail.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showTeachers();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler2 rh = new RequestHandler2();
                String s = rh.sendGetRequest(Config.URL_GET_ALL_TEACHERS);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TeacherPersonalDetail2.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String teacherid = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.TEACHER_ID, teacherid);
        startActivity(intent);
    }
}