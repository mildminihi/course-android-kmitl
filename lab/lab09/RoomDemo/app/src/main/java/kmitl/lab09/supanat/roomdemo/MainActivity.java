package kmitl.lab09.supanat.roomdemo;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MessageDB messageDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "USER_INFO").build();





        new AsyncTask<Void, Void, UserInfo>(){

            @Override
            protected UserInfo doInBackground(Void... voids) {
                UserInfo userInfo = new UserInfo();
                userInfo.setText("Hello");
                userInfo.setTime(new Date().toString());

                messageDB.getMessageInfoDAO().insert(userInfo);

                return null;
            }
        }.execute();
        Button showbtn = (Button)findViewById(R.id.showMessageBtn);
        showbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "USER_INFO").build();
        new AsyncTask<Void, Void, List<UserInfo>>(){

            @Override
            protected List<UserInfo> doInBackground(Void... voids) {
                List<UserInfo> result = messageDB.getMessageInfoDAO().findAll();
                return result;

            }

            @Override
            protected void onPostExecute(List<UserInfo> userInfos) {
                ArrayAdapter<UserInfo> adaptor = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, userInfos);
                ListView messageInfoList = (ListView)findViewById(R.id.messageList);
                messageInfoList.setAdapter(adaptor);
            }
        }.execute();
    }
}
