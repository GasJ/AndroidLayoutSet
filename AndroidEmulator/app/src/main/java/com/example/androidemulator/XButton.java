package com.example.androidemulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Monkey on 03/02/2018.
 */

public final class XButton extends XWidget {
    String buttonName;
    Rect buttonRect;
    int preWidth;
    int preHeight;

    public XButton(int id, int width, int height, String name) {
        super(id);
        this.preWidth = width;
        this.preHeight = height;
        this.buttonName = name;
        isFrameX = false;
        alignment = "C";
    }

    public XButton(int id, int width, int height, String name, Canvas can) {
        super(id, can);
        this.preWidth = width;
        this.preHeight = height;
        this.buttonName = name;
        isFrameX = false;
        alignment = "C";
    }


    @Override
    protected void draw(Canvas can, Paint mypaint) {
        c = can;
        p = mypaint;


        //draw the button

        Rect rectAfterInset = new Rect((int) xstart + insets, (int) ystart + insets, (int) xEnd-insets, (int) yEnd-insets);
        int rectWidth = rectAfterInset.width();
        int rectHeight = rectAfterInset.height();

        Log.d("dying", rectAfterInset.toString());

        if(rectWidth > preWidth){
            rectAfterInset.left = rectAfterInset.left + ((rectWidth - preWidth)/2);
            rectAfterInset.right = rectAfterInset.left + preWidth;
        }

        if(rectHeight > preHeight){
            Log.d("food", "coming in " + id);
            rectAfterInset.top = rectAfterInset.top + ((rectHeight - preHeight)/2);
            rectAfterInset.bottom = rectAfterInset.top + preHeight;
        }

        if(p == null){
            Log.d("finally", "p is null");
        }

        if(c == null){
            Log.d("finally", "c is null");
        }

        Log.d("finally", "buttonWidth = " + rectAfterInset.width());

        p.setColor(Color.parseColor("#00abe5"));
        Log.d("finally", "still alive");
        c.drawRect(rectAfterInset, p);
        buttonRect = rectAfterInset;


        Log.d("finally", "hey");

        //write down the button name
        p.setColor(Color.BLACK);
        p.setTextSize(50);

        //Rect textBound = new Rect();
        //p.getTextBounds(buttonName,0,0,textBound);
        p.setTextAlign(Paint.Align.CENTER);

        float x = xstart + trueWidth/2;
        float y = ystart + trueHeight/2 + insets;

/*        String msg ="id " + id+" xEnd " + xEnd + " x " + x + " y " + y;

        Log.d("calculating", msg);
        p.setColor(Color.BLACK);
        p.setTextSize(60);*/
        this.c.drawText(buttonName, rectAfterInset.centerX(), rectAfterInset.centerY()+20, p);

    }

    public void reSize(int npw, int nph, int nis){
        this.preWidth = npw;
        this.preHeight = nph;
        this.insets = nis;
    }

    public void toDraw(int newpw, int newph, int newis){
        Log.d("saobi", "wwwwww");
        p.setColor(Color.GREEN);
        c.drawText("DASHABI!!!!!!", 200, 200, p);

    }

/*    @Override
    public void layout(int xStart, int yStart, int widthFromP, int heightFromP) {
        super.layout(xStart, yStart, widthFromP, heightFromP);
        //this.draw();
    }*/

}
