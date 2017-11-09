package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{



    private int EIEI = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Income and Expense Accounts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showResult();
    }

    public void showResult() {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MONEYTABLE").build();

        new AsyncTask<Void, Void, List<MoneyTableResult>>() {

            @Override
            protected List<MoneyTableResult> doInBackground(Void... voids) {
                List<MoneyTableResult> result = messageDB.getMessageInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyTableResult> moneytables) {
                int totalIncome = 0, totalBalance = 0;
                for (MoneyTableResult table : moneytables) {

                    if (table.getType().equals("Income")) {
                        totalIncome += table.getMoney();
                        totalBalance += table.getMoney();
                    } else totalBalance -= table.getMoney();
                    TextView textAmount = (TextView) findViewById(R.id.textCurrently);

                    if (totalBalance > 0.5 * totalIncome) {
                        textAmount.setTextColor(Color.parseColor("#6aa84f"));

                    } else if (totalBalance >= 0.25 * totalIncome) {
                        textAmount.setTextColor(Color.parseColor("#f1c232"));
                    } else {
                        textAmount.setTextColor(Color.parseColor("#cc0000"));
                    }
                    textAmount.setText(totalBalance + "");
                }
                Adapter adapter = new Adapter(MainActivity.this, R.layout.list_item, moneytables);
                ListView listView = (ListView)findViewById(R.id.listStatus);
                listView.setAdapter(adapter);

//                ArrayAdapter<MoneyTableResult> adaptor = new ArrayAdapter<>(MainActivity.this,
//                        android.R.layout.simple_list_item_1, moneytables);
//                ListView messageInfoList = (ListView) findViewById(R.id.listStatus);
//
//                messageInfoList.setAdapter(adaptor);
            }
        }.execute();
    }

    public void onDelete(View view) {
        Toast.makeText(MainActivity.this, "Deleted All Statement!", Toast.LENGTH_LONG).show();
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MONEYTABLE").build();

        new AsyncTask<Void, Void, List<MoneyTableResult>>() {

            @Override
            protected List<MoneyTableResult> doInBackground(Void... voids) {
                messageDB.getMessageInfoDAO().deleteAll();
                return null;

            }
        }.execute();
        int totalBalance = 0;
        TextView textAmount = (TextView) findViewById(R.id.textCurrently);
        textAmount.setTextColor(Color.parseColor("#6aa84f"));
        textAmount.setText(totalBalance + "");

        showResult();
    }

    public void onAdd(View view){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);

        startActivityForResult(intent, EIEI);
        showResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EIEI && resultCode == RESULT_OK){
            showResult();
        }
    }

}
