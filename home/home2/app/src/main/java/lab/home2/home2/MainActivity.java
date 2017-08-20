package lab.home2.home2;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);
        TextView textView = (TextView) findViewById(R.id.textView17);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView textView1 = (TextView) findViewById(R.id.textView19);
        textView1.setPaintFlags(textView1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);}

    public void onClickBysee(View view) {
        Toast.makeText(
                getBaseContext(),
                "This is See New Topics Button",
                Toast.LENGTH_LONG)
                .show();
    }
}
