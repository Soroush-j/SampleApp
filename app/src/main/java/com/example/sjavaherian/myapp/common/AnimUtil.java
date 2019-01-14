package com.example.sjavaherian.myapp.common;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.example.sjavaherian.myapp.R;

public class AnimUtil {
    private static String TAG = "tag AnimationUtil";

    interface AnimationFinishedListener {
        void onAnimationFinished();
    }

    public static void circularEnterAnimation(final Context context, final View view, final int x, final int y, final float endRadius) {
        view.addOnLayoutChangeListener((new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                view.removeOnLayoutChangeListener(this);
                Animator animator = ViewAnimationUtils.createCircularReveal(view, x, y, 0f, endRadius);
                animator.setDuration(700);
                animator.start();

                int startColor = context.getResources().getColor(R.color.secondaryColor);
                int endColor = context.getResources().getColor(R.color.white);

                startColorAnimation(context, view, startColor, endColor);
            }
        }));
    }

    public static void startColorAnimation(final Context context, final View view, int startColor, int endColor) {


        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(startColor, endColor);
        valueAnimator.setEvaluator(new ArgbEvaluatorCompat());
        valueAnimator.addUpdateListener(animation -> {
            view.setBackgroundColor((Integer) animation.getAnimatedValue());
        });
        valueAnimator.setDuration(700);
        valueAnimator.start();
    }

    public static void circularExitAnimation(final Context context, final View view, final int x, final int y,
                                             final float startRadius, final AnimationFinishedListener listener) {
        Animator animator = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, 0f);
        animator.setDuration(700);

        int end = context.getResources().getColor(R.color.red);
        int start = context.getResources().getColor(R.color.white);

        new Handler().postDelayed(() -> {
//            view.setVisibility(View.GONE);
            view.setBackgroundColor(Color.TRANSPARENT);
            listener.onAnimationFinished();
        }, animator.getDuration());

//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation, boolean isReverse) {
//                super.onAnimationEnd(animation, isReverse);
//                Log.d(TAG, "animation ended");
//                view.setVisibility(View.GONE);
//                mListener.onAnimationFinished();
//            }
//        });
        animator.start();
        startColorAnimation(context, view, start, end);
    }
}
