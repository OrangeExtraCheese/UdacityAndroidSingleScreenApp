package tomaszmarzec.udacity.android.singlescreenapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConstraintLayout rootView =  (ConstraintLayout) findViewById(R.id.rootView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //Code based on StackOverflow topic: https://stackoverflow.com/questions/33971626/set-background-image-to-relative-layout-using-glide-in-android/38025862
        // Answer by Chintan Desai
        Glide.with(this).load(R.drawable.pizzabackground2).asBitmap().into(new SimpleTarget<Bitmap>(width, height)
        {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
            {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                {
                    rootView.setBackground(drawable);
                }
            }
        });

        ImageView topFrame = (ImageView) findViewById(R.id.top_frame);
        ImageView bottomFrame = (ImageView) findViewById(R.id.bottom_frame);
        ImageView leftFrame = (ImageView) findViewById(R.id.left_frame);
        ImageView rightFrame = (ImageView) findViewById(R.id.right_frame);
        ImageView pizzeria1 = (ImageView) findViewById(R.id.imageView);
        ImageView pizzeria2 = (ImageView) findViewById(R.id.imageView2);

        Glide.with(this).load(R.drawable.vineslong).into(topFrame);
        Glide.with(this).load(R.drawable.vineslong).into(bottomFrame);
        Glide.with(this).load(R.drawable.vinesverylong_vertical).into(leftFrame);
        Glide.with(this).load(R.drawable.vinesverylong_vertical).into(rightFrame);
        Glide.with(this).load(R.drawable.primavera1).into(pizzeria1);
        Glide.with(this).load(R.drawable.primavera2).into(pizzeria2);
    }
}
