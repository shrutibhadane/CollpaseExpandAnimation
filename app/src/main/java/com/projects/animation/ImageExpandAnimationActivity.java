package com.projects.animation;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ImageExpandAnimationActivity extends AppCompatActivity {

    private CardView imgCardView;
    private Activity activity;
    int layoutHeightInDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_animation);

        activity = this;

        imgCardView = findViewById(R.id.imgCardView);

        layoutHeightInDP = (int) ((getResources().getDimension(R.dimen.dp200) / getResources().getDisplayMetrics().density));

        performExpandCollapseOperation(imgCardView);

    }

    private void performExpandCollapseOperation(CardView imgCardView) {

        int animationDuration;
        ValueAnimator anim1;
        animationDuration = 1500;

        imgCardView.setVisibility(View.VISIBLE);
        anim1 = ValueAnimator.ofInt(0, layoutHeightInDP);
        anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = imgCardView.getLayoutParams();
                layoutParams.height = convertDpIntoPixels(val, activity);
                imgCardView.setLayoutParams(layoutParams);

            }
        });

        anim1.setDuration(animationDuration);
        anim1.start();

    }

    public static int convertDpIntoPixels(int dp, Activity activity) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());

    }
}