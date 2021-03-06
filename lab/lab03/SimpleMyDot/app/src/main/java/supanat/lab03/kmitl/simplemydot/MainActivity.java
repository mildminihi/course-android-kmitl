package supanat.lab03.kmitl.simplemydot;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import supanat.lab03.kmitl.simplemydot.model.Dot;
import supanat.lab03.kmitl.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {
    private Dot dot;
    private DotView dotView;
    private ArrayList<Dot> listDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(this, 0, 0, 20, 0, 0, 0);
        listDot = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        Button clear = (Button) findViewById(R.id.button);
        Button undo = (Button) findViewById(R.id.undoButton);
        undo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (listDot.size()!= 0) {
                    listDot.remove(listDot.size() - 1);
                    dotView.invalidate();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            //Click to clear
            @Override
            public void onClick(View view) {
                listDot.clear();
                dotView.invalidate();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //Touch to get values x y
        float x = e.getX();
        float y = e.getY() - 200;
        CreateDot(x, y);
        return true;
    }

    public void CreateDot(float x, float y) {
        //Get value to create dot
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int radius = random.nextInt(100);
        dot = new Dot(this, x, y, radius, red, blue, green);
        this.dot.setRed(red);
        this.dot.setBlue(blue);
        this.dot.setGreen(green);
        dot.setCenterX(x);
        dot.setCenterY(y);
        dot.setRadius(radius);
        listDot.add(dot);
    }

    public void onRandomDot(View view) {
        //Click button to random x y
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        CreateDot(centerX, centerY);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setListDot(listDot);
        dotView.invalidate();

    }

}
