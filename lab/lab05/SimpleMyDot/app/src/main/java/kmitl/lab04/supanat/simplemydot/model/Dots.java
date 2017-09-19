package kmitl.lab04.supanat.simplemydot.model;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mild supanat on 13/9/2560.
 */

public class Dots implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }




    public void changeSize(int index, int r) {
        allDot.get(index).setRadius(r);
        this.listener.onDotsChanged(this);
    }

    public int getColorDot(int index) {
        return allDot.get(index).getColor();
    }

    public int findDot(int x, int y) {
        for (int index = 0; index < allDot.size(); index++) {
            int centerX = allDot.get(index).getCenterX();
            int centerY = allDot.get(index).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= allDot.get(index).getRadius()) {
                return index;
            }
        }
        return -1;
    }

    public void editAttributeDot(int position, Dot dot) {
        allDot.set(position, dot);
        this.listener.onDotsChanged(this);
    }


}

