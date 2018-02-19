package com.example.androidemulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Monkey on 03/02/2018.
 */

public class XFrame extends XWidget{
    //private Paint p = new Paint();
    //private Canvas canvas = new Canvas();
    //for orientation, default value is 0 (horizontal)
    int orientation = 0;
    boolean root;
    List<XWidget> children;

    public void resetChildren() {
        //reset children for redrawing the canvas

        for(int i=0; i < children.size(); i++){
            XWidget child = children.get(i);
            if(child.isFrameX){
                ((XFrame)child).resetChildren();
            }
        }

        children.clear();

    }
    //int children;

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public XFrame(int id) {
        super(id);
        children = new LinkedList<>();
        isFrameX = true;
        root = false;
        //p.setColor(Color.BLACK);
        //Rect r = new Rect(100,200,300,400);
        //c.drawRect(r, p);
    }

    public XFrame(int id, Canvas can) {
        super(id, can);
        children = new LinkedList<>();
        isFrameX = true;
        root = false;
        //p.setColor(Color.BLACK);
        //Rect r = new Rect(100,200,300,400);
        //c.drawRect(r, p);
    }

    @Override
    protected void draw(Canvas can, Paint mypaint) {
        //p.setColor(Color.YELLOW);
        //canvas.drawRect();
        c = can;
        p = mypaint;
        Log.d("dying", "in");

        p.setColor(Color.YELLOW);
        Rect r = new Rect((int) xstart, (int) ystart, (int) xEnd, (int) yEnd);
        can.drawRect(r, p);

        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(r, p);
        p.setStyle(Paint.Style.FILL);


        drawHelper(this);

        Log.d("dying", "out");

    }

    private void drawHelper(XFrame draw) {
        int cn = draw.children.size();

        if(cn == 0){
            return;
        }

        for (int i = 0; i < cn; i++) {
            XWidget child = draw.children.get(i);
            child.draw(c, p);
        }

        return;

    }

    public void setOrientation(Orientation o) {
        switch (o) {
            case HORIZONTAL:
                orientation = 0;
                return;
            default:
                orientation = 1;
        }
    }

/*    @Override
    public void layout(int xStart, int yStart, int widthFromP, int heightFromP) {
        super.layout(xStart,yStart,widthFromP,heightFromP);

        //this.draw();

*//*
        //draw the picture
        p.setColor(Color.YELLOW);
        Rect r = new Rect(xStart, yStart,widthFromP, heightFromP);
        c.drawRect(r, p);
        p.setColor(Color.BLACK);

        p.setStyle(Paint.Style.STROKE);
        c.drawRect(r, p);
        p.setStyle(Paint.Style.FILL);*//*
    }*/

    public void addChild(XWidget w){
        children.add(w);
        if(root) {


            //p.setColor(Color.YELLOW);
            //Rect r = new Rect(xstart, ystart, xEnd, yEnd);
            //c.drawRect(r, p);


            int n = children.size();
            //draw lines:
            for (int i = 0; i < n; i++) {
                XWidget child = children.get(i);

                //the frame need to distribute its space according to the number of its children
                if (this.orientation == 0) {
                    //if the root is horizontal layout

                    child.layout((xEnd / n * i) + xstart, 0, (xEnd / n * (i + 1)) + xstart, yEnd);

                } else {
                    //if the root is vertical layout
                    //String y = Integer.toString((yEnd / n * i) + ystart) + "  " + Integer.toString((yEnd / n * (i + 1)) + ystart);
                    //String msg = "starty and yEnd are " + y;
                    //Log.d("shabi", msg);
                    child.layout(0, (yEnd / n * i) + ystart, xEnd, (yEnd / n * (i + 1)) + ystart);

                }

                child.parent = this;

                //if the child is button, it never has a child or children, so we do not need to reset for its child/children
                //but is the child is frame, it may have a child, especially more than one child,
                //therefore we need to do that for frame.
                if (child.isFrameX) {

                    Log.d("shabi", "child's id is: " + Integer.toString(child.id));
                    //if the child has children:
                    childrenLayoutHelper((XFrame) child);

                }
            }
        }



    }

    private void childrenLayoutHelper(XFrame child) {
        Log.d("dashabi", "setting children for parent id is: " + child.id);

        //cn for child's children number
        int cn = child.children.size();

        if(cn == 0){
            return;
        }


        for (int i = 0; i < cn; i++) {
            XWidget grandchild = child.children.get(i);

            //the child frame need to distribute its space according to the number of its children
            //(which should be grandchildren for "this frame")
            if (child.orientation == 0) {
                //int trueWidth = child.xEnd - child.xstart;
                //if the child's layout is horizontal
                float gcXStart = child.trueWidth / cn * i + child.xstart;
                float gcWidth = child.trueWidth / cn + gcXStart;
                grandchild.layout(gcXStart, child.ystart, gcWidth, child.yEnd);

            } else {
                //if the child's layout is vertical
                //int trueHeight = child.yEnd - child.ystart;
                float gcYStart = child.trueHeight / cn * i + child.ystart;
                float gcHeight = child.trueHeight / cn + gcYStart;

                /*String a = "child's yEnd is ";
                Log.d("shabi", a + Integer.toString(child.yEnd));
                Log.d("shabi", Integer.toString(gcYStart) + "  " + Integer.toString(gcHeight));
                Log.d("shabi", Integer.toString(child.xstart) + "  " + Integer.toString(child.xEnd));*/
                grandchild.layout(child.xstart, gcYStart, child.xEnd, gcHeight);
            }

            grandchild.parent = child;
            //if the grandchild is button, it never has a grand-grandchild or grand-grandchildren,
            //so we do not need to reset for its grand-grandchild/grand-grandchildren,
            //but is the grandchild is frame, it may have a grand-grandchild,
            //especially more than one grand-grandchild ,
            //therefore we need to do that for frame.
            if(grandchild.isFrameX){
                //if the child has children:
                childrenLayoutHelper((XFrame)grandchild);
            }
        }

    }

    public void setRoot(Boolean rootOrNot){
        this.root = rootOrNot;
    }


}
