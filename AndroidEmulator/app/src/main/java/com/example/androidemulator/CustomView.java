package com.example.androidemulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monkey on 27/01/2018.
 */

public class CustomView extends View {
    public int test = 100;
    private Paint p = new Paint();
    Map<Integer, XWidget> widgets = new HashMap<>();
    Canvas bigC;
    boolean lock = false;

    public CustomView(Context context) {
        super(context);
        init();
    }

    private void  init(){
        XFrame root = new XFrame(0);
        root.setOrientation(XFrame.Orientation.HORIZONTAL);
        root.setRoot(true);
        //root.draw(canvas, p);

        XFrame vert1 = new XFrame(1);
        vert1.setOrientation(XFrame.Orientation.VERTICAL);

        XFrame vert2 = new XFrame(2);
        vert2.setOrientation(XFrame.Orientation.HORIZONTAL);

        XFrame vert3 = new XFrame(3);
        vert3.setOrientation(XFrame.Orientation.HORIZONTAL);

        XFrame vert4 = new XFrame(4);
        vert4.setOrientation(XFrame.Orientation.VERTICAL);

        XFrame vert5 = new XFrame(5);
        vert5.setOrientation(XFrame.Orientation.VERTICAL);

        XFrame vert6 = new XFrame(6);
        vert6.setOrientation(XFrame.Orientation.VERTICAL);

        XFrame vert7 = new XFrame(7);
        vert7.setOrientation(XFrame.Orientation.VERTICAL);

        XFrame vert8 = new XFrame(8);

/*        vert5.addChild(vert1);
        vert5.addChild(vert4);

        root.addChild(vert2);
        root.addChild(vert3);
        root.addChild(vert5);*/



        XButton xb1 = new XButton(9,200,100,"Button1");
        xb1.insets = 15;
        XButton xb2 = new XButton(10,100,100,"Button2");
        xb2.insets = 15;
        XButton xb3 = new XButton(11,300,100,"Button3");
        xb3.insets = 15;
        XButton xb4 = new XButton(12,300,100,"Button4");
        xb4.insets = 15;
        XButton xb5 = new XButton(13,300,100,"Button5");
        xb5.insets = 15;
        XButton xb6 = new XButton(14,300,100,"Button6");
        xb6.insets = 15;


/*
        vert1.addChild(xb1);
        vert1.addChild(xb3);
        vert1.addChild(vert2);
        vert2.addChild(xb2);
        vert2.addChild(xb6);
        root.addChild(xb4);
        //root.addChild(vert1);
        root.addChild(xb5);
*/
        widgets.put(xb1.id, xb1);
        widgets.put(xb2.id, xb2);
        widgets.put(xb3.id, xb3);
        widgets.put(xb4.id, xb4);
        widgets.put(xb5.id, xb5);
        widgets.put(xb6.id, xb6);

        widgets.put(vert1.id, vert1);
        widgets.put(vert2.id, vert2);
        widgets.put(root.id, root);




    }

    @Override
    public  void  onDraw(Canvas canvas){
        super.onDraw(canvas);
        //canvas.drawRect(100,300,400,500,p);
        if(!lock || lock) {
            bigC = canvas;
            XFrame root = (XFrame) widgets.get(0);
            root.resetChildren();
            root.layout(0, 0, getWidth(), getHeight());


            Log.d("dying", "here");

            XFrame vert1 = (XFrame) widgets.get(1);
            XFrame horiz1 = (XFrame) widgets.get(2);

            XButton xb1 = (XButton) widgets.get(9);
            XButton xb2 = (XButton) widgets.get(10);
            XButton xb3 = (XButton) widgets.get(11);
            XButton xb4 = (XButton) widgets.get(12);
            XButton xb5 = (XButton) widgets.get(13);
            XButton xb6 = (XButton) widgets.get(14);

            vert1.addChild(xb1);
            vert1.addChild(xb3);
            vert1.addChild(horiz1);
            horiz1.addChild(xb2);
            horiz1.addChild(xb6);
            root.addChild(xb4);
            root.addChild(vert1);
            root.addChild(xb5);

            root.draw(canvas, p);

            lock = true;

        }

    }

    public void onClick(View v) {
        // do something when the button is clicked
    }

    public boolean checkPosition(float x, float y) {

        return  false;
    }


}
