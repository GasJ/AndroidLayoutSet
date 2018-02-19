package com.example.androidemulator;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText e2 = findViewById(R.id.e2);
        EditText e3 = findViewById(R.id.e3);
        EditText e4 = findViewById(R.id.e4);

        LinearLayout bigLay = findViewById(R.id.bigLayout);
        Button setValues = new Button(this);


        setValues.setBackgroundColor(Color.LTGRAY);
        setValues.setCursorVisible(true);
        setValues.setText("Set Values");
        bigLay.addView(setValues);

        //EditText allSet = findViewById(R.id.e5);
        //allSet.setEnabled(false);


        final CustomView boo = new CustomView(this);
        boo.setBackgroundColor(Color.parseColor("#2a0d45"));
        bigLay.addView(boo);


        setValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("finally", "start");
                TextView idtv = findViewById(R.id.textID);


                //Log.d("finally, "idGetting start");
                int id = Integer.parseInt(idtv.getText().toString());


                Log.d("finally", "idGetting passed");
                EditText e2 = findViewById(R.id.e2);
                EditText e3 = findViewById(R.id.e3);
                EditText e4 = findViewById(R.id.e4);

                int newPw = (int) Float.parseFloat(e2.getText().toString());
                int newPh = (int) Float.parseFloat(e3.getText().toString());
                int newInset = Integer.parseInt(e4.getText().toString());


                //reset the root's children
                XFrame root = (XFrame) boo.widgets.get(0);
                root.resetChildren();
                Log.d("finally", "start");

                if(id >= 9){
                    XButton settingButton = (XButton) boo.widgets.get(id);
                    Log.d("finally", "finally");
                    settingButton.reSize(newPw, newPh, newInset);
                }

                boo.lock = false;
                boo.invalidate();
                Log.d("inva", "invalidate success");
            }
        });


        View.OnTouchListener mListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float xx = motionEvent.getX();
                float yy = motionEvent.getY();

/*
                EditText editText = findViewById(R.id.e5);
                String a = */
/*Float.toString(xx) + *//*
Float.toString(yy);
                char[] b = a.toCharArray();
                editText.setText(b, 0, a.length());

                EditText clickText = findViewById(R.id.e2);


                if(!((xx > 100 && xx < 400) && (yy < 500 && yy > 300))){
                    char[] carr = "Ok bye".toCharArray();
                    clickText.setText(carr, 0, carr.length);
                }else {
                    char[] carr = "Click!".toCharArray();
                    clickText.setText(carr, 0, carr.length);
                }
*/


                //determine which children of the root you are in
                //get root first
                XFrame root = (XFrame) boo.widgets.get(0);
                int rootCN = root.children.size();

                int indexOfCurChild;
                if(root.orientation == 0){
                    int eachWidth = root.trueWidth / rootCN;
                     indexOfCurChild = (int) xx/eachWidth;
                }else{
                    int eachHeight = root.trueHeight / rootCN;
                    indexOfCurChild = (int) yy/eachHeight;
                }

                XWidget curChild = findCurrentChild(xx, yy, root.children.get(indexOfCurChild));


                if(curChild.isFrameX){
                    return false;
                }

                //now curChild should be a button
                //check whether the mouse is on the rectangle of the button
                if(inTheButtonRect(((XButton)curChild).buttonRect, xx, yy)){
                    int id = curChild.id;
                    TextView tv = findViewById(R.id.textID);
                    String caonima = Integer.toString(id);
                    char[] cnm = caonima.toCharArray();
                    tv.setText(cnm, 0, cnm.length);

                    int pw = ((XButton) curChild).preWidth;
                    int ph = ((XButton) curChild).preHeight;
                    int is = curChild.insets;
                    String al = curChild.alignment;

                    EditText e2 = findViewById(R.id.e2);
                    String pws = Integer.toString(pw);
                    char[] pwc = pws.toCharArray();
                    e2.setText(pwc, 0, pws.length());


                    EditText e3 = findViewById(R.id.e3);
                    String phs = Integer.toString(ph);
                    char[] phc = phs.toCharArray();
                    e3.setText(phc, 0, phs.length());

                    EditText e4 = findViewById(R.id.e4);
                    String iss = Integer.toString(is);
                    char[] isc = pws.toCharArray();
                    e4.setText(isc, 0, iss.length());

                    EditText e5 = findViewById(R.id.e5);
                    char[] alc = al.toCharArray();
                    e5.setText(alc, 0, al.length());

                }


                return true;

            }


        };




        boo.setOnTouchListener(mListener);
/*
        boo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               *//* int action = motionEvent.getAction();
                if(action == MotionEvent.ACTION_DOWN){
                    EditText editText = findViewById(R.id.e5);
                    editText.setBackgroundColor(Color.YELLOW);
                    return true;
                }else if(action == MotionEvent.ACTION_DOWN){
                    EditText editText = findViewById(R.id.e5);
                    editText.setBackgroundColor(Color.YELLOW);
                    return true;
                }else if(action == MotionEvent.ACTION_MOVE){
                    EditText editText = findViewById(R.id.e5);
                    editText.setBackgroundColor(Color.YELLOW);
                    return true;
                }*//*
                return false;
            }
        });*/
    }


    private boolean inTheButtonRect(Rect buttonRect, float xx, float yy) {
        if(!((xx > buttonRect.left && xx < buttonRect.right) && (yy < buttonRect.bottom && yy > buttonRect.top))){
            return false;
        }
        return true;
    }

    private XWidget findCurrentChild(float xx, float yy, XWidget child) {
        Log.d("LaoSaobi", "coming in");
        if (!child.isFrameX){
            Log.d("LaoSaobi", "coming out with button");
            return child;
        }

        XFrame childFrame = (XFrame) child;

        int gcn = childFrame.children.size();

        if(gcn == 0){
            return child;
        }

        //if child has child/children
        //determine which child the current pointer is
        int indexOfCurGrandChild;
        if(childFrame.orientation == 0) {
            int eachWidth = child.trueWidth / gcn;
            indexOfCurGrandChild = (int) (xx-child.xstart) / eachWidth;
        }else{
            int eachHeight = child.trueHeight / gcn;
            indexOfCurGrandChild = (int) (yy-child.ystart) / eachHeight;
        }

        Log.d("LaoSaobi", "coming out with children " + indexOfCurGrandChild);
        Log.d("LaoSaobi", "current widget is " + child.id + " its children# is " + gcn);
        return  findCurrentChild(xx, yy, childFrame.children.get(indexOfCurGrandChild));


    }


    public void sendMessage(View view) {
        // Do something in response to button
    }



}
