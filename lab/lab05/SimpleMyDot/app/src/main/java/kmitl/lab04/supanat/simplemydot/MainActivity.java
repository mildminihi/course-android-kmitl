package kmitl.lab04.supanat.simplemydot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import kmitl.lab04.supanat.simplemydot.fragment.MainFragment;
import kmitl.lab04.supanat.simplemydot.fragment.MenuFragment;
import kmitl.lab04.supanat.simplemydot.model.Dot;
import kmitl.lab04.supanat.simplemydot.model.Dots;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            initialFragment();
        }


    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment.newInstance())
                .commit();
    }



    private void Editfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.EditfragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }




    @Override
    public void DotSelected(Dot dot, Dots dots, int dotPosition) {
        Editfragment(MenuFragment.newInstance(dot, dots, dotPosition));
    }


//    public void onShare(View view){
//        View main = (View)findViewById(R.id.mainview);
//        if(requestWriteExternalStoragePermission()){
//            Bitmap bitmap = Convertor.convertViewtoBitmap(main.getRootView());
//            Uri bitmapUri = getImageUri(this.getApplicationContext(), bitmap);
//            share(bitmapUri);
//        }
//    }
//
//
//    public boolean requestWriteExternalStoragePermission(){
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    public Uri getImageUri(Context context, Bitmap bitmap){
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Untitled", null);
//        return Uri.parse(path);
//    }
//
//    public void share(Uri uri){
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.putExtra(intent.EXTRA_STREAM, uri);
//        intent.setType("image/*");
//        startActivity(Intent.createChooser(intent, "Share Screen"));
//    }
//    final private int REQUEST_CODE_ASK_PERMISSIONS = 999;
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void askPermission() {
//        int hasWriteExtPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
//
//            return;
//        }
//    }
//
//    public void onRandomDot(View view) {
//        Random random = new Random();
//        int centerX = random.nextInt(dotView.getWidth());
//        int centerY = random.nextInt(dotView.getHeight());
//        int radius = random.nextInt(200);
//        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
//        dots.addDot(newDot);
//    }

//    @Override
//    public void onDotsChanged(Dots dots) {
//        dotView.setDots(dots);
//        dotView.invalidate();
//    }
//    @Override
//    public void onDotViewPressed(int x, int y) {
//        int dotPosition = dots.findDot(x, y);
//
////        if(dotPosition == -1) {
////            if (checkCreate == 1){
////                Random random = new Random();
////                int radius = random.nextInt(200);
////                Dot newDot = new Dot(x, y, radius, new Colors().getColor());
////                dots.addDot(newDot);
////            }
////        } else {
////            if (checkDelete == 1){
////                dots.removeBy(dotPosition);
////
////            }
////            if (checkEdit == 1){
////
////                Random random = new Random();
////                int radius = random.nextInt(200);
////                Dot newDot = new Dot(x, y, radius, new Colors().getColor());
////                dots.addDot(newDot);
////                dots.removeBy(dotPosition);
////
////            }
////
////        }
//    }
}

