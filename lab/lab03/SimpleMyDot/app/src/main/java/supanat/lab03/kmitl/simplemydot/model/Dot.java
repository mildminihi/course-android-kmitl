package supanat.lab03.kmitl.simplemydot.model;

public class Dot {
    private float centerX;
    private float centerY;
    private int radius;
    int red, green, blue;

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }
    private DotChangedListener dotChangedListener;

    public void setDotChangedListener(DotChangedListener dotChangedListener){
        this.dotChangedListener = dotChangedListener;
    }
    public Dot(DotChangedListener dotChangedListener, float centerX, float centerY, int radius, int red, int green, int blule) {
        this.dotChangedListener = dotChangedListener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.red = red;
        this.green = green;
        this.blue = blule;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getBlue() {
        return blue;

    }

    public void setBlue(int blue) {
        this.blue = blue;
        this.dotChangedListener.onDotChanged(this);
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;

        this.dotChangedListener.onDotChanged(this);
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        this.dotChangedListener.onDotChanged(this);
    }


}
