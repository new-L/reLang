package com.example.relang;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceLanguage extends AppCompatActivity implements View.OnClickListener{
    DBHelper dbHelper;
    Button btn;
    ImageButton english, german;
    ContentValues contentValues = new ContentValues();//для добавления новых строк(1 объет = 1 строка)

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);

        english = (ImageButton) findViewById(R.id.imageButton2);
        english.setOnClickListener(this);

        german = (ImageButton) findViewById(R.id.imageButton);
        german.setOnClickListener(this);
        dbHelper = new DBHelper(this);


        String email_acc = getIntent().getStringExtra("email_acc");
        SQLiteDatabase database = dbHelper.getWritableDatabase();//и для чтения и для записи
        Cursor cursor = database.query(DBHelper.TABLE_COURSE, null, null, null, null, null, null);
        Cursor cursor_1 = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL_ADD);
        int name_Index  = cursor_1.getColumnIndex(DBHelper.KEY_EMAIL);
        boolean flag=true;
        if(cursor.moveToFirst() && cursor_1.moveToFirst()){
            do {
                if(email_acc.equals(cursor.getString(nameIndex))){flag=false;  break;}

            }while(cursor.moveToNext());
            if(flag){
                do {
                if(email_acc.equals(cursor_1.getString(name_Index))) {
                            Toast.makeText(getApplicationContext(), "Всё ОК!", Toast.LENGTH_SHORT).show();
                            contentValues.put(DBHelper.KEY_EMAIL_ADD, email_acc);
                            database.insert(DBHelper.TABLE_COURSE, null, contentValues);
                            break;
                        }
                }while(cursor_1.moveToNext());
            }
        }else{
            Toast.makeText(getApplicationContext(), "Всё ОК!", Toast.LENGTH_SHORT).show();
            contentValues.put(DBHelper.KEY_EMAIL_ADD, email_acc);
            contentValues.put(DBHelper.KEY_PROGRESS_ENG, 0);
            contentValues.put(DBHelper.KEY_PROGRESS_GER, 0);
            database.insert(DBHelper.TABLE_COURSE,null,contentValues);
        }
        cursor.close();
        cursor_1.close();








    }

    @Override
    public void onClick(View v){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String email_acc = getIntent().getStringExtra("email_acc");
        switch (v.getId()){
            case R.id.button:
                Check();break;


            case R.id.imageButton2:
                Intent intent = new Intent(ChoiceLanguage.this,English.class);
                intent.putExtra("email_acc",email_acc);
                startActivity(intent);
                break;


            case R.id.imageButton:
                Intent intent1 = new Intent(ChoiceLanguage.this,German.class);
                intent1.putExtra("email_acc",email_acc);
                startActivity(intent1);
                break;



        }

    }










    public void Check(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_COURSE,null,null,null,null,null,null);
        Cursor cursor1 = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if(cursor1.moveToFirst()) {
            int nameIndex = cursor1.getColumnIndex(DBHelper.KEY_EMAIL);
            int passIndex = cursor1.getColumnIndex(DBHelper.KEY_PASSWORD);
            int idIndex = cursor1.getColumnIndex(DBHelper.KEY_ID);

            do {
                Log.d("mLog","id = " + cursor1.getString(idIndex) + "name = " + cursor1.getString(nameIndex) + ", pass = " + cursor1.getString(passIndex));
            } while (cursor1.moveToNext());
        }else{
            Log.d("mLog", "0 rows");
        }
        cursor1.close();
        if(cursor!=null && cursor.getCount()> 0) {
            if (cursor.moveToFirst()) {
                int idAUTH = cursor.getColumnIndex(DBHelper.KEY_EMAIL_ADD);
                int pro_ger = cursor.getColumnIndex(DBHelper.KEY_PROGRESS_GER);
                int pro_eng = cursor.getColumnIndex(DBHelper.KEY_PROGRESS_ENG);

                do {
                    Log.d("mLog","email_auth = " + cursor.getString(idAUTH) + ", progress_english = " + cursor.getString(pro_eng) + ", progress_ger = " + cursor.getString(pro_ger));
                } while (cursor.moveToNext());
            } else {
                Log.d("mLog", "0 rows");
            }
            cursor.close();
        }else{
            Log.d("mLog", "0 rows");
        }
    }



}
