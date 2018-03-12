package tomaszmarzec.udacity.android.singlescreenapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity {

    String phoneNumber;
    Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
    String mapQuery= "wolbrom+pizzeria+primavera";
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query="+mapQuery));

    ImageView topFrame;
    ImageView bottomFrame;
    ImageView leftFrame;
    ImageView rightFrame;
    ImageView pizzeriaPic1;
    ImageView pizzeriaPic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = getResources().getString(R.string.number);

        final ConstraintLayout rootView = findViewById(R.id.rootView);

        topFrame =  findViewById(R.id.top_frame);
        bottomFrame =  findViewById(R.id.bottom_frame);
        leftFrame =  findViewById(R.id.left_frame);
        rightFrame =  findViewById(R.id.right_frame);
        pizzeriaPic1 =  findViewById(R.id.pizzeria_pic1);
        pizzeriaPic2 =  findViewById(R.id.pizzeria_pic2);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        renderImages(rootView, height, width);
    }

    private void renderImages(final ConstraintLayout rootView, int height, int width)
    {
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

        if(getResources().getConfiguration().orientation==1)
        {
            Glide.with(this).load(R.drawable.vineslong).into(topFrame);
            Glide.with(this).load(R.drawable.vineslong).into(bottomFrame);
            Glide.with(this).load(R.drawable.vinesverylong_vertical).into(leftFrame);
            Glide.with(this).load(R.drawable.vinesverylong_vertical).into(rightFrame);
        }

        if(getResources().getConfiguration().orientation==2)
        {
            Glide.with(this).load(R.drawable.vinesverylong).into(topFrame);
            Glide.with(this).load(R.drawable.vinesverylong).into(bottomFrame);
            Glide.with(this).load(R.drawable.vineslong_vertical).into(leftFrame);
            Glide.with(this).load(R.drawable.vineslong_vertical).into(rightFrame);
        }

        Glide.with(this).load(R.drawable.primavera1).into(pizzeriaPic1);
        Glide.with(this).load(R.drawable.primavera2).into(pizzeriaPic2);
    }

    public void callPizzeria(View view)
    {
        startActivity(phoneIntent);
    }

    public void showMap(View view)
    {
        startActivity(mapIntent);
    }
}
