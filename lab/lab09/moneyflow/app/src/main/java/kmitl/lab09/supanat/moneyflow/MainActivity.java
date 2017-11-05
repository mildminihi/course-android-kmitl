package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "INCOME").build();
        new AsyncTask<Void, Void, List<Moneytable>>(){

            @Override
            protected List<Moneytable> doInBackground(Void... voids) {
                List<Moneytable> result = messageDB.getMessageInfoDAO().findAll();
                return result;

            }

            @Override
            protected void onPostExecute(List<Moneytable> moneytables){
                ArrayAdapter<Moneytable> adapter = new ArrayAdapter<Moneytable>(MainActivity.this,
                        android.R.layout.simple_list_item_1, moneytables);
                ListView stateList = (ListView)findViewById(R.id.listStatus);
                stateList.setAdapter(adapter);
            }
        }.execute();
    }

    public void onAdd(View view){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
