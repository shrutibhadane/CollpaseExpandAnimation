package com.projects.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ImageExpandCollapseActivity extends AppCompatActivity {

    private TextView expandCollapseTextView;
    private CardView imgCardView;
    private Activity activity;
    int layoutHeightInDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_expand_collapse);

        activity = this;

        expandCollapseTextView = findViewById(R.id.expandCollapseTextView);
        imgCardView = findViewById(R.id.imgCardView);

        layoutHeightInDP = (int) ((getResources().getDimension(R.dimen.dp200) / getResources().getDisplayMetrics().density));

        expandCollapseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performExpandCollapseOperation(imgCardView);
                
            }
        });

    }

    private void performExpandCollapseOperation(CardView imgCardView) {

        int animationDuration;
        ValueAnimator anim1, anim2;
        animationDuration = 1500;

        if (imgCardView.getVisibility() == View.VISIBLE) {

            anim1 = ValueAnimator.ofInt(layoutHeightInDP, 0);
            anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = imgCardView.getLayoutParams();
                    layoutParams.height = convertDpIntoPixels(val, activity);
                    imgCardView.setLayoutParams(layoutParams);

                }
            });

            anim1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    expandCollapseTextView.setText("Click here to Expand Image");
                    imgCardView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim1.setDuration(animationDuration);
            anim1.start();

        } else {

            anim2 = ValueAnimator.ofInt(0, layoutHeightInDP);
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = imgCardView.getLayoutParams();
                    layoutParams.height = convertDpIntoPixels(val, activity);
                    imgCardView.setLayoutParams(layoutParams);
                }

            });

            anim2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    expandCollapseTextView.setText("Click here to Collapse Image");
                    imgCardView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            anim2.setDuration(animationDuration);
            anim2.start();


        }

    }

    public static int convertDpIntoPixels(int dp, Activity activity) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());

    }
}