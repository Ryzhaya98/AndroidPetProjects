package com.example.testvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_read;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btn_read = (Button)findViewById(R.id.btn_read_DB);
        dbHelper = new DBHelper(this);

    }


    @Override
    public void onClick(View v) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelper.TABLE_USER, null,null,null,null,null,null);

            if(cursor.moveToFirst()){
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nicknameIndex = cursor.getColumnIndex(DBHelper.KEY_NICKNAME);
                int tokenIndex = cursor.getColumnIndex(DBHelper.KEY_TOKEN);
                int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASS);

                do {
                    Log.d("=========mLog=======", "ID = " + cursor.getInt(idIndex) + ", name - " + cursor.getString(nicknameIndex) + ", token = " + cursor.getString(tokenIndex) + ", password - " + cursor.getString(passIndex));
                    database.delete(DBHelper.TABLE_USER, null,null);
                } while (cursor.moveToNext());
            }else
                Log.d("!!!!!!!!!mLog!!","0 rows");
            cursor.close();
        }
}
