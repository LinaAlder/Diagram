package com.example.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.Nullable;

public class DiagramView extends View {
    Context context;
    MainActivity mainActivity;
    Diagram diagram;
    float w, h, r = 500;

    public DiagramView(Context context) {
        super(context);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        int[] sectorWeights = {1, 2, 3, 1, 2, 5};

        diagram = new Diagram(context, sectorWeights);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Sector[] sectors;

        // координаты середины экрана
        w = canvas.getWidth();
        h = canvas.getHeight();

        sectors = diagram.createSectors(w / 2, h /2, r);

        // отображаем контуры на холсте
        for(int i = 0; i < sectors.length; i++) canvas.drawPath(sectors[i].getPath(),sectors[i].getPaint());
    }

    public void setParent(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if( event.getAction() != MotionEvent.ACTION_DOWN ) return false;

        double x, y, corner;

        x = event.getX() - w / 2;
        y = h / 2 - event.getY();

        if(Math.sqrt(x * x + y * y) > r) return false;

        corner = Math.acos((x * r) / (Math.sqrt(x * x + y * y) * r)) * 180 / Math.PI;

        if(y < 0) corner = 360 - corner;

        diagram.moveSector(corner);
        invalidate();
        mainActivity.setPercents(diagram.getPercents());

        return true;
    }


}