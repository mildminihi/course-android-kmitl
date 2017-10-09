package kmitl.lab04.supanat.simplemydot.fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab04.supanat.simplemydot.R;
import kmitl.lab04.supanat.simplemydot.model.Colors;
import kmitl.lab04.supanat.simplemydot.model.Dot;
import kmitl.lab04.supanat.simplemydot.model.Dots;
import kmitl.lab04.supanat.simplemydot.model.Screenshot;
import kmitl.lab04.supanat.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Dots.OnDotsChangeListener,DotView.OnDotViewPressListener, View.OnClickListener {

    private View main;
    private DotView dotView;
    private static Dots dots;
    private ImageView imageView;
    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;


    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);
        Button random = (Button)rootView.findViewById(R.id.button);
        Button clear = (Button)rootView.findViewById(R.id.removeAll);
        Button share = (Button)rootView.findViewById(R.id.share);
        random.setOnClickListener(this);
        clear.setOnClickListener(this);
        share.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }
    @Override
    public void onDotViewPressed(final int x, final int y) {
        final int dotPosition = dots.findDot(x, y);  //get index in List
        final int r = ((int) (Math.random() * 60)) + 20;
        //Don't have dot
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, r, new Colors().getColor());
            dots.addDot(newDot);

            //Have dot
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setItems(new CharSequence[]{" Edit Dot", " Delete"},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:

                                    MainFragmentListener listener = getDotFragmentListener();
                                    Dot dot = dots.getAllDot().get(dotPosition);
                                    listener.DotSelected(dot, dots, dotPosition);
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    dots.removeBy(dotPosition);
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    });
            builder.show();
        }
    }

    public interface MainFragmentListener {
        void DotSelected(Dot dot, Dots dots, int dotPosition);
    }

    private MainFragmentListener getDotFragmentListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (MainFragmentListener) fragment;
            } else {
                return (MainFragmentListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                RandomDot();
                break;
            case R.id.removeAll:
                dots.clearAll();
                break;
            case R.id.share:
            ClickShare();
                break;
        }
    }
    private void createShareIntent(Uri uriImage) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
        try {
            startActivity(Intent.createChooser(shareIntent, " How do you want to share? "));
        } catch (ActivityNotFoundException e) {
        }
    }
    public void ClickShare() {

        if (askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_REQUEST_CODE)) {
            Bitmap image = Screenshot.takescreenshotOfRootView(imageView);
            Uri screenshotUri = Screenshot.getImageUri(this.getContext(), image);

            createShareIntent(screenshotUri);
        }
    }
    private boolean askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return false;
        } else {
            Toast.makeText(getActivity(), "Permission is Already Granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
    public void RandomDot() {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int r = ((int) (Math.random() * 60)) + 20;
        Dot newDot = new Dot(centerX, centerY, r, new Colors().getColor());
        dots.addDot(newDot);
    }


}

