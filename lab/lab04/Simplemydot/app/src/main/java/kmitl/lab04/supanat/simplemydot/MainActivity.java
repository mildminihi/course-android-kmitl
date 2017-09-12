package kmitl.lab04.supanat.simplemydot;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab04.supanat.simplemydot.model.Colors;
import kmitl.lab04.supanat.simplemydot.model.Convertor;
import kmitl.lab04.supanat.simplemydot.model.Dot;
import kmitl.lab04.supanat.simplemydot.model.Dots;
import kmitl.lab04.supanat.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{

    private View main;
    private DotView dotView;
    private Dots dots;
    private int checkDelete = 0;
    private int checkEdit = 0;
    private int checkCreate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);


    }

    public void onShare(View view){
        if(requestWriteExternalStoragePermission()){
            Bitmap bitmap = Convertor.convertViewtoBitmap(dotView);
            Uri bitmapUri = getImageUri(this.getApplicationContext(), bitmap);
            share(bitmapUri);
        }
    }


    public boolean requestWriteExternalStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            return false;
        }else{
            return true;
        }
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Untitled", null);
        return Uri.parse(path);
    }

    public void share(Uri uri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Screen"));
    }

    public void onCreateDot(View view){
        if (checkCreate == 0){
            checkCreate = 1;
            checkDelete = 0;
            checkEdit = 0;

            Toast.makeText(
                    getBaseContext(),
                    "Click On Screen to Create Dot",
                    Toast.LENGTH_LONG)
                    .show();
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("CREATE");
        }
        else {
            checkCreate = 0;
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("");
        }
    }

    public void onDeleteDot(View view){
        if (checkDelete == 0){
            checkDelete = 1;
            checkEdit = 0;
            checkCreate = 0;

            Toast.makeText(
                    getBaseContext(),
                    "Click On Dot to Delete Dot",
                    Toast.LENGTH_LONG)
                    .show();
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("DELETE");
        }
        else{
            checkDelete = 0;
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("");
        }

    }
    public void onEditDot(View view){
        if (checkEdit == 0){
            checkEdit = 1;
            checkCreate = 0;
            checkDelete = 0;

            Toast.makeText(
                    getBaseContext(),
                    "Click On Dot to Edit Dot",
                    Toast.LENGTH_LONG)
                    .show();
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("EDIT");

        }
        else {
            checkEdit = 0;
            TextView settext = (TextView)findViewById(R.id.showtext);
            settext.setText("");

        }
    }
    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = random.nextInt(200);
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            if (checkCreate == 1){
                Random random = new Random();
                int radius = random.nextInt(200);
                Dot newDot = new Dot(x, y, radius, new Colors().getColor());
                dots.addDot(newDot);
            }
        } else {
            if (checkDelete == 1){
                dots.removeBy(dotPosition);

            }
            if (checkEdit == 1){

                Random random = new Random();
                int radius = random.nextInt(200);
                Dot newDot = new Dot(x, y, radius, new Colors().getColor());
                dots.addDot(newDot);
                dots.removeBy(dotPosition);

            }

        }
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 999;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission() {
        int hasWriteExtPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);

            return;
        }
    }
}