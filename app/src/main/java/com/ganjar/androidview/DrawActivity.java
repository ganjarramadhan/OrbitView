package com.ganjar.androidview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ganjar.androidview.custom.CircleViewTest;

/**
 * Created by ganjarramadhan on 7/3/16.
 */
public class DrawActivity extends AppCompatActivity {

    public static final String KEY_RADIUS = "radius";
    public static final String KEY_ITEM = "item";

    private CircleViewTest circleViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // set to content layout
        setContentView(R.layout.activity_draw);

        // init ui
        circleViewTest = (CircleViewTest) findViewById(R.id.activity_draw_cvt);

        // get radius
        int radius = getIntent().getIntExtra(KEY_RADIUS, 0);

        // get item
        int jumlahItem = getIntent().getIntExtra(KEY_ITEM, 0);

        // validate radius
        if (radius == 0){

            // invalid input, nothing to draw
            Toast.makeText(this, getString(R.string.error_radius_invalid), Toast.LENGTH_LONG).show();
            finish();

        }

        // update draw
        circleViewTest.setRadius(radius);
        circleViewTest.setItem(jumlahItem);
        circleViewTest.invalidate();
    }
}
