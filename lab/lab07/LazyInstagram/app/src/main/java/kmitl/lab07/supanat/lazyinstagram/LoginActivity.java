package kmitl.lab07.supanat.lazyinstagram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onLogin(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setItems(new CharSequence[]{" Android", " Nature", " Cartoon"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case 0:
                                intent("android");
                                dialog.dismiss();
                                break;
                            case 1:
                                intent("nature");
                                dialog.dismiss();
                                break;
                            case 2:
                                intent("cartoon");
                                dialog.dismiss();
                        }
                    }
                });
        builder.show();
    }
    public void intent(String user){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }





}
