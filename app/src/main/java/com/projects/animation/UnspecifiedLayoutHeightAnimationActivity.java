package com.projects.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UnspecifiedLayoutHeightAnimationActivity extends AppCompatActivity {

    private LinearLayout contentLayout;
    private ImageView arrowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unspecified_layout_height_animation);

        contentLayout = findViewById(R.id.contentLayout);
        arrowImageView = findViewById(R.id.arrowImageView);

        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (contentLayout.getVisibility() == View.VISIBLE) {
                    arrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24));
                    collapse(contentLayout);
                } else {
                    arrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24));
                    expand(contentLayout);
                }

            }
        });
    }
    
    public static void expand(final View v) {
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? WindowManager.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

}