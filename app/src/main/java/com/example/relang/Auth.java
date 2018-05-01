package com.example.relang;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relang.questions.Osnovi_14;

public class Auth extends AppCompatActivity implements View.OnClickListener {
    Button btnIn, btnUp;
    EditText etEmail,etPassword;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        btnIn = (Button) findViewById(R.id.buttonIn);
        btnIn.setOnClickListener(this);

        btnUp = (Button) findViewById(R.id.buttonUp);
        btnUp.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        dbHelper = new DBHelper(this);

    }


    @Override
    public void onClick(View v){

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();//и для чтения и для записи

        ContentValues contentValues = new ContentValues();//для добавления новых строк(1 объет = 1 строка)

        switch(v.getId()) {
            case R.id.buttonIn:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                    if (email.length() != 0 && password.length() != 0) {
                        if (validate(email)) {
                            if (cursor.moveToFirst()) {//если не пусто
                                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
                                int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                                do{
                                    if (email.equals(cursor.getString(nameIndex)) && password.equals(cursor.getString(passIndex))) {
                                       Intent intent_1 = new Intent(Auth.this,Osnovi_14.class);
                                       Intent intent = new Intent(Auth.this,ChoiceLanguage.class);
                                       intent.putExtra("email_acc",email);
                                       startActivity(intent);

                                       break;
                                    }
                                }while(cursor.moveToNext());

                                cursor.close();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Адерс введен некорректно!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Строка адреса и/или пароля пуста!", Toast.LENGTH_SHORT).show();
                    }
                break;
            case R.id.buttonUp:
                Cursor cursor_1 = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if(email.length() != 0 && password.length() != 0) {
                    if (validate(email)) {
                        if (cursor_1.moveToFirst()) {//если не пусто
                            boolean flag=true;
                            int nameIndex = cursor_1.getColumnIndex(DBHelper.KEY_EMAIL);
                            do {
                                if (email.equals(cursor_1.getString(nameIndex))) {
                                    flag=false;
                                    Toast.makeText(getApplicationContext(), "Такой e-mail адрес зарегестрирован", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            } while (cursor_1.moveToNext());
                            do{if(flag){
                                contentValues.put(DBHelper.KEY_EMAIL, email);
                                contentValues.put(DBHelper.KEY_PASSWORD, password);
                                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                                Toast.makeText(getApplicationContext(), "Вы успешно зарегестрировались!", Toast.LENGTH_SHORT).show();
                                break;
                            }} while (cursor_1.moveToNext());
                        }else{
                            contentValues.put(DBHelper.KEY_EMAIL, email);
                            contentValues.put(DBHelper.KEY_PASSWORD, password);
                            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                            Toast.makeText(getApplicationContext(), "Вы успешно зарегестрировались!", Toast.LENGTH_SHORT).show();
                            break;

                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Адерс введен некорректно!", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Строка адреса и/или пароля пуста!", Toast.LENGTH_SHORT).show();
                }

                cursor_1.close();
                break;
        }
        dbHelper.close();
    }

    public static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String email){

        Matcher matcher = VALID_EMAIL_REGEX .matcher(email);
        return matcher.find();
    }

}
