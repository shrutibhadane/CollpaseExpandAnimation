package com.projects.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn1:
                Intent intent1 = new Intent(this, UnspecifiedLayoutHeightAnimationActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn2:
                Intent intent2 = new Intent(this, ImageExpandAnimationActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn3:
                Intent intent3 = new Intent(this, ImageExpandCollapseActivity.class);
                startActivity(intent3);
                break;

        }
    }
}