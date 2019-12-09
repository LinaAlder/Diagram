package com.example.diagram;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Random;

public class Sector {
    Context context;
    Path path;
    Paint paint;
    double beginCorner, sizeCorner;

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public Sector(Context context, double beginCorner, double sizeCorner) {
        this.context = context;
        this.beginCorner = beginCorner;
        this.sizeCorner = sizeCorner;
        this.paint = new Paint();
        this.paint.setColor(getRandomColor());
    }

    public void createSector(float x, float y, float r, boolean sectorIsOffset){
        float offsetX, offsetY;
        double corner;

        if(sectorIsOffset){
            corner = ((-(beginCorner * 2 + sizeCorner)) * Math.PI) / 360;
            x += (float) (Math.cos(corner) * r * 0.2);
            y += (float) (Math.sin(corner) * r * 0.2);
        }

        path = new Path();
        path.moveTo(x,y);

        for(double i = 0; i <= sizeCorner; i++) {
            corner = (-(beginCorner + i)) * Math.PI / 180;
            offsetX = (float) (Math.cos(corner) * r) + x;
            offsetY = (float) (Math.sin(corner) * r) + y;
            path.lineTo(offsetX, offsetY);
        }

        corner = (-(beginCorner + sizeCorner)) * Math.PI /180;
        offsetX = (float) (Math.cos(corner) * r) + x;
        offsetY = (float) (Math.sin(corner) * r) + y;
        path.lineTo(offsetX, offsetY);
    }

    public Path getPath(){
        return path;
    }

    public Paint getPaint() { return paint; }

    public double getBeginCorner() { return beginCorner; }

    public double getSizeCorner() { return sizeCorner; }
}
