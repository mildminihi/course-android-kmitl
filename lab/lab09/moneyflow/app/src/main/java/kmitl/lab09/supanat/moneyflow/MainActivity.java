package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MONEYTABLE").build();

        new AsyncTask<Void, Void, List<MoneyTableResult>>() {

            @Override
            protected List<MoneyTableResult> doInBackground(Void... voids) {

                List<MoneyTableResult> result = messageDB.getMessageInfoDAO().findAll();
                String income = messageDB.getMessageInfoDAO().findIncome();

                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyTableResult> moneytables){
                TextView textAmount = (TextView)findViewById(R.id.textCurrently);
                ArrayAdapter<MoneyTableResult> adaptor = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, moneytables);
                ListView messageInfoList = (ListView)findViewById(R.id.listStatus);
                textAmount.setText(income);
                messageInfoList.setAdapter(adaptor);
            }
        }.execute();

    }

    public void onAdd(View view){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);

        startActivity(intent);
    }
}
