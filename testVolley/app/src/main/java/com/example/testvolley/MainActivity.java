package com.example.testvolley;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {
    private static final String LG = "http://markokovt.ru/registr_biov/login.php";
    EditText editemail,editpass;
    Button btnLogin;
    TextView tv;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textSignUp);
        editemail=(EditText)findViewById(R.id.etemail);
        editpass=(EditText)findViewById(R.id.etpass);
        btnLogin=(Button)findViewById(R.id.btnlogin);
        dbHelper = new  DBHelper(this);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickname=editemail.getText().toString().trim();
                final String pass=editpass.getText().toString().trim();


                StringRequest stringRequest=new StringRequest(Request.Method.POST, LG, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            Boolean res=object.getString("result").equals("user_found");
                            Log.d("====== RESPONSE ===== " , response);
                            if (res){
                                final String name=object.getString("result");//исправить
                                final String emaildata=object.getString("token_key");//исправить
                                String nickname = editemail.getText().toString().trim();
                                String pass = editpass.getText().toString().trim();

                                Intent intent=new Intent(MainActivity.this,UserActivity.class);

                                String token = object.getString("token_key");
                                Log.d("",token);
                                ContentValues contentValues = new ContentValues();
                                SQLiteDatabase database = dbHelper.getWritableDatabase();

                                contentValues.put(DBHelper.KEY_NICKNAME,nickname);
                                contentValues.put(DBHelper.KEY_TOKEN, token);
                                contentValues.put(DBHelper.KEY_PASS, pass);
                                database.insert(DBHelper.TABLE_USER, null, contentValues);

                                intent.putExtra("username",name);
                                intent.putExtra("email",emaildata);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"User Login UnSucssesFull", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String>parms=new HashMap<String, String>();
                        parms.put("nickname",nickname);
                        parms.put("password",pass);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,activity_signup.class));
            }
        });
    }


//    @Override
//    public void onClick(View v) {
//        ContentValues contentValues = new ContentValues();
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        Cursor cursor = database.query(DBHelper.TABLE_USER, null,null,null,null,null,null);
//
//                if(cursor.moveToFirst()){
//                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
//                    int nicknameIndex = cursor.getColumnIndex(DBHelper.KEY_NICKNAME);
//                    int tokenIndex = cursor.getColumnIndex(DBHelper.KEY_TOKEN);
//                    int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASS);
//
//                    do {
//                        Log.d("=========mLog=======", "ID = " + cursor.getInt(idIndex) + ", name - " + cursor.getString(nicknameIndex) + ", email = " + cursor.getString(tokenIndex) + ", password - " + cursor.getString(passIndex));
//                    } while (cursor.moveToNext());
//                }else
//                    Log.d("!!!!!!!!!mLog!!","0 rows");
//                cursor.close();
//    }
}