package com.ganjar.androidview.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ganjarramadhan on 7/2/16.
 */
public class CircleViewTest extends View {

    // defines paint and canvas
    private Paint drawPaint;
    private Paint drawLittlePaint;
    private Paint drawFirstLittlePaint;

    // get center point
    private float x, y;

    private float radius = 70;
    private int item = 7;

    public CircleViewTest(Context context, int radius, int item){
        super(context, null);
        this.radius = radius;
        this.item = item;

        setupPaint();
    }

    public CircleViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
        // paint for main circle
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLUE);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);

        // paint for first little circle
        drawFirstLittlePaint = new Paint();
        drawFirstLittlePaint.setColor(Color.GREEN);

        // paint for rest little circle
        drawLittlePaint = new Paint();
        drawLittlePaint.setColor(Color.CYAN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // init center point
        x = getWidth() / 2;
        y = getHeight() / 2;

        // draw main circle
        canvas.drawCircle(x, y, radius, drawPaint);

        // draw little orbit
        drawLittleOrbit(canvas, item);
    }

    private void drawLittleOrbit(Canvas canvas, int orbitSize){

        // jumlah sebaran berdasarkan derajat lingkaran
        float moveDegree = 360 / orbitSize;
        float size = Math.min(radius / orbitSize * 2, radius / 4);

        for(int i=0; i<orbitSize; i++){

            float degree = i * moveDegree;

            // Math.sin atau Math.cos menghitung berdasarkan radian, jadi harus si degree diconvert ke radian terlebih dahulu
            double currentX = Math.sin(Math.toRadians(degree)) * radius;
            double currentY = Math.cos(Math.toRadians(degree)) * radius;

            float realCurrentX = (float) (x - currentX);
            float realCurrentY = (float) (y - currentY);
            if (i == 0) {
                // lingkaran kecil pertama akan berwarna hijau
                canvas.drawCircle(realCurrentX, realCurrentY, size, drawFirstLittlePaint);
            } else {
                // sisa lingkaran lainnya berwarna biru muda
                canvas.drawCircle(realCurrentX, realCurrentY, size, drawLittlePaint);
            }

        }

    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
