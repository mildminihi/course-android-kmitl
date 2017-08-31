package supanat.lab03.kmitl.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import supanat.lab03.kmitl.simplemydot.model.Dot;


public class DotView extends View {

    private Paint paint;
    private Dot dot;
    private ArrayList<Dot> listDot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.listDot != null) {
            for (Dot dot: listDot) {
                if (dot != null) {
                    paint.setColor(Color.rgb(dot.getRed(), dot.getGreen(), dot.getBlue()));
                    canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
                }
            }
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }
    public void setDot(Dot dot){
        this.dot = dot;
    }
    public void setListDot(ArrayList<Dot> listDot){
        this.listDot = listDot;
    }

}
