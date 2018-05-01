package com.example.relang.questions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.relang.R;

import static android.view.View.GONE;

public class Osnovi_1 extends AppCompatActivity implements View.OnClickListener{
    RadioButton one_r,two_r,three_r,four_r;
    Button check_b,next_b;
    String check_s = "a boy",first,second,third,fourth;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._osnovi_1);


        one_r = (RadioButton) findViewById(R.id.one);
        one_r.setOnClickListener(this);
        one_r.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.a_boy),null,null);
        two_r = (RadioButton) findViewById(R.id.two);
        two_r.setOnClickListener(this);
        two_r.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.a_man),null,null);
        three_r = (RadioButton) findViewById(R.id.three);
        three_r.setOnClickListener(this);
        three_r.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.a_girl),null,null);
        four_r = (RadioButton) findViewById(R.id.fourth);
        four_r.setOnClickListener(this);
        four_r.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.a_woman),null,null);

        check_b = (Button) findViewById(R.id.check);
        check_b.setOnClickListener(this);
        next_b = (Button) findViewById(R.id.next);
        next_b.setOnClickListener(this);

point = 0;
    }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.one:
                    one_r.setChecked(true);
                    two_r.setChecked(false);
                    three_r.setChecked(false);
                    four_r.setChecked(false);
                    break;
                case R.id.two:
                    one_r.setChecked(false);
                    two_r.setChecked(true);
                    three_r.setChecked(false);
                    four_r.setChecked(false);
                    break;
                case R.id.three:
                    one_r.setChecked(false);
                    two_r.setChecked(false);
                    three_r.setChecked(true);
                    four_r.setChecked(false);
                    break;
                case R.id.fourth:
                    one_r.setChecked(false);
                    two_r.setChecked(false);
                    three_r.setChecked(false);
                    four_r.setChecked(true);
                    break;
                default:
                    break;
            }
            switch(v.getId()){
                case R.id.check:
                    Checked(v);
                    Log.d("mLog", Integer.toString(point));
                    break;
                case R.id.next:
                    Intent intent = new Intent(Osnovi_1.this,Osnovi_2.class);
                    intent.putExtra("point",point);
                    startActivity(intent);
                    Log.d("mLog", Integer.toString(point));
            }
        }
        public void Checked(View v){
            first = one_r.getText().toString();
            second = two_r.getText().toString();
            third = three_r.getText().toString();
            fourth = four_r.getText().toString();
            if(one_r.isChecked() && check_s.equals(first)){
                check_b.setVisibility(View.GONE);
                next_b.setVisibility(View.VISIBLE);
                point= point + 10;
                Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_LONG).show();
            }else{
                if(two_r.isChecked() && check_s.equals(second)){
                    check_b.setVisibility(View.GONE);
                    next_b.setVisibility(View.VISIBLE);
                    point= point + 10;
                    Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_LONG).show();
                }else{
                    if(three_r.isChecked() && check_s.equals(third)){
                        check_b.setVisibility(View.GONE);
                        next_b.setVisibility(View.VISIBLE);
                        point= point + 10;
                        Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Неверно!", Toast.LENGTH_SHORT).show();
                        if(four_r.isChecked() && check_s.equals(fourth)){
                            check_b.setVisibility(View.GONE);
                            next_b.setVisibility(View.VISIBLE);
                            point= point + 10;
                            Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        }


}
