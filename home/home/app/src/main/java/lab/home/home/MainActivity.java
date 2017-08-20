package lab.home.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home1);
    }
    public void onClickBymovie(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is Movies Button",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickByapp(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is Apps Button",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickBybook(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is Books Button",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickBygame(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is Games Button",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickBymore(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is more Button",
                Toast.LENGTH_LONG)
                .show();
    }
}
