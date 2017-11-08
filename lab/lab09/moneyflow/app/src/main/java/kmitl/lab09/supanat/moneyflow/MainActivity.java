package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private MessageDB messageDB;

    private MoneyTableResult moneytableresult;
    private int EIEI = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Money Flow Table");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showResult();
    }

    public void showResult(){
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MONEYTABLE").build();

        new AsyncTask<Void, Void, List<MoneyTableResult>>() {

            @Override
            protected List<MoneyTableResult> doInBackground(Void... voids) {
                List<MoneyTableResult> result = messageDB.getMessageInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyTableResult> moneytables){
                int totalIncome = 0, totalBalance = 0;
                for (MoneyTableResult table : moneytables) {

                    if (table.getType().equals("+")) {
                        totalIncome += table.getMoney();
                        totalBalance += table.getMoney();
                    } else totalBalance -= table.getMoney();
                    TextView textAmount = (TextView)findViewById(R.id.textCurrently);

                    if (totalBalance > 0.5*totalIncome) {
                        textAmount.setTextColor(Color.GREEN);

                    } else if (totalBalance >= 0.25*totalIncome) {
                        textAmount.setTextColor(Color.YELLOW);
                    } else {
                        textAmount.setTextColor(Color.RED);
                    }
                    textAmount.setText(totalBalance + "");
                }

                ArrayAdapter<MoneyTableResult> adaptor = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, moneytables);
                ListView messageInfoList = (ListView)findViewById(R.id.listStatus);

                messageInfoList.setAdapter(adaptor);
            }
        }.execute();
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
