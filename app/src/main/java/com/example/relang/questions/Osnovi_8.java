package com.example.relang.questions;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.relang.R;

public class Osnovi_8 extends AppCompatActivity implements View.OnClickListener{
    TextView check_s,progress;
    Button help,var_1,var_2,var_3,var_4,next;
    ImageView im;
    String flag="a boy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_edit);

        check_s = (TextView) findViewById(R.id.translate);
        check_s.setText(R.string.ru_a_boy);
        progress = (TextView) findViewById(R.id.progress);
        progress.setText(R.string.progress_8);

        help = (Button) findViewById(R.id.help);
        help.setOnClickListener(this);
        var_1 = (Button) findViewById(R.id.first);
        var_1.setOnClickListener(this);
        var_1.setText(R.string.a_boy);
        var_2 = (Button) findViewById(R.id.second);
        var_2.setOnClickListener(this);
        var_2.setText(R.string.a_girl);
        var_3 = (Button) findViewById(R.id.third);
        var_3.setOnClickListener(this);
        var_3.setText(R.string.a_woman);
        var_4 = (Button) findViewById(R.id.fourth);
        var_4.setOnClickListener(this);
        var_4.setText(R.string.an_apple);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);

        im = (ImageView) findViewById(R.id.image);
        im.setImageResource(R.drawable.a_boy);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.help:
                Help(v);
                break;
            case R.id.first:
                Check(var_1);
                var_2.setBackgroundResource(R.drawable.border_btn);
                var_3.setBackgroundResource(R.drawable.border_btn);
                var_4.setBackgroundResource(R.drawable.border_btn);
                break;
            case R.id.second:
                Check(var_2);
                var_1.setBackgroundResource(R.drawable.border_btn);
                var_3.setBackgroundResource(R.drawable.border_btn);
                var_4.setBackgroundResource(R.drawable.border_btn);
                break;
            case R.id.third:
                Check(var_3);
                var_1.setBackgroundResource(R.drawable.border_btn);
                var_2.setBackgroundResource(R.drawable.border_btn);
                var_4.setBackgroundResource(R.drawable.border_btn);
                break;
            case R.id.fourth:
                Check(var_4);
                var_2.setBackgroundResource(R.drawable.border_btn);
                var_3.setBackgroundResource(R.drawable.border_btn);
                var_1.setBackgroundResource(R.drawable.border_btn);
                break;
            case R.id.next:
                Intent intent = new Intent(Osnovi_8.this,Osnovi_9.class);
                startActivity(intent);
                break;
        }
    }

    public void Check(Button btn){
        if(btn.getText().toString().equals(flag))
        {
            btn.setBackgroundResource(R.drawable.border_btn_rigth);
            next.setVisibility(View.VISIBLE);
        }else{
            btn.setBackgroundResource(R.drawable.border_btn_flase);
        }
    }
    public void Help(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(Osnovi_8.this);
        builder.setTitle("Подсказка").setMessage(flag + " – " + check_s.getText().toString());
        AlertDialog alert = builder.create();
        alert.show();

    }
}
