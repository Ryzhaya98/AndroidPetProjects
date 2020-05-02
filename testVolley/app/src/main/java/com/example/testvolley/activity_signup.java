package com.example.testvolley;

//import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class activity_signup extends AppCompatActivity implements View.OnClickListener {
    private static final String RG ="http://markokovt.ru/registr_biov/Register.php";
    EditText etanme,etemail,etpass;
    Button signupbtn, btn_add_DB, btn_clear_DB, btn_read_DB;


    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etanme=(EditText)findViewById(R.id.editname);
        etemail=(EditText)findViewById(R.id.editEmail);
        etpass=(EditText)findViewById(R.id.editPass);
        signupbtn=(Button)findViewById(R.id.btnsignup);
        btn_add_DB=(Button)findViewById(R.id.btn_add_DB);
        btn_clear_DB=(Button)findViewById(R.id.btn_clear_DB);
        btn_read_DB=(Button)findViewById(R.id.btn_read_DB);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etanme.getText().toString().trim();
                String email=etemail.getText().toString().trim();
                String pass=etpass.getText().toString().trim();

                createSignup(name,email,pass);
                startActivity(new Intent(activity_signup.this, MainActivity.class));

            }
        });




        //dbHelper = new  DBHelper(this);

    }
    @Override
    public void onClick(View v) {
//        String name=etanme.getText().toString().trim();
//        String email=etemail.getText().toString().trim();
//        String pass=etpass.getText().toString().trim();
//
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        switch (v.getId()){
//            case R.id.btn_add_DB:
//                contentValues.put(DBHelper.KEY_NAME, name);
//                contentValues.put(DBHelper.KEY_EMAIL, email);
//                contentValues.put(DBHelper.KEY_PASS, pass);
//
//                database.insert(DBHelper.TABLE_USER, null, contentValues);
//
//
//                break;
//
//            case R.id.btn_clear_DB:
//                database.delete(DBHelper.TABLE_USER, null,null);
//                break;
//
//            case R.id.btn_read_DB:
//                Cursor cursor = database.query(DBHelper.TABLE_USER, null,null,null,null,null,null);
//
//                if(cursor.moveToFirst()){
//                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
//                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
//                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
//                    int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASS);
//
//                    do {
//                        Log.d("=========mLog=======", "ID = " + cursor.getInt(idIndex) + ", name - " + cursor.getString(nameIndex) + ", email = " + cursor.getString(emailIndex) + ", password - " + cursor.getString(passIndex));
//                    } while (cursor.moveToNext());
//                }else
//                    Log.d("!!!!!!!!!mLog!!","0 rows");
//                cursor.close();
//                break;
//        }
//
//
    }



    private void createSignup(final String name, final String email,final String pass) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, RG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>parms=new HashMap<String, String>();
                parms.put("name",name);
                parms.put("email",email);
                parms.put("password",pass);

                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
