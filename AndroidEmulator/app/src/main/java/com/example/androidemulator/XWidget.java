package com.example.androidemulator;



import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Monkey on 03/02/2018.
 */

public abstract class XWidget{
    int id;
    float xstart; //xstart from parent
    float ystart;//ystart from parent
    float xEnd; //xEnd from parent
    float yEnd; //yEnd from parent
    int trueWidth;
    int trueHeight;
    int insets;

    Boolean isFrameX;
    Canvas c;
    Paint p;
    XWidget parent;
    String alignment = "L";

    public XWidget(int id){
        this.id = id;
        p = new Paint();
    }


    public XWidget(int id, Canvas can){
        this.id = id;
        c = can;
        p = new Paint();
    }


    protected abstract void draw(Canvas can, Paint mypaint);




    public void layout(float xStart, float yStart, float widthFromP, float heightFromP) {
        xstart = xStart;
        ystart = yStart;
        xEnd = widthFromP;
        yEnd = heightFromP;
        trueWidth = (int) (xEnd - xStart);
        trueHeight = (int)  (yEnd - yStart);
    }


}
