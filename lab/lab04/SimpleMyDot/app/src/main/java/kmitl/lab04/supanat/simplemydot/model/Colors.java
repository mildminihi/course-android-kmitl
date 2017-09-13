package kmitl.lab04.supanat.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mild supanat on 13/9/2560.
 */

public class Colors {
    private List<Integer> colorList = new ArrayList<>();

    public Colors() {
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
    }
    public int getColor() {
        return colorList.get(
                new Random().nextInt(colorList.size()));
    }
}

