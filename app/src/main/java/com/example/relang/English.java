package com.example.relang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.relang.questions.Osnovi_1;

public class English extends AppCompatActivity implements View.OnClickListener {


    ImageButton imgOsnovi;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            imgOsnovi = (ImageButton) findViewById(R.id.osnovi);
            switch (item.getItemId()) {
                case R.id.navigation_education:
                    imgOsnovi.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_profile:
                    imgOsnovi.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_shop:
                    imgOsnovi.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        imgOsnovi = (ImageButton) findViewById(R.id.osnovi);
        imgOsnovi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.osnovi:
                Intent intent = new Intent(English.this,Osnovi_1.class);
                startActivity(intent);
                break;
        }
    }

}
