package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MessageDB messageDB;

    private MoneyTableResult moneytableresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Money Flow Table");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showResult();

        Button btn_Update = (Button)findViewById(R.id.buttonUpdate);

        btn_Update.setOnClickListener(this);


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
                for (MoneyTableResult r : moneytables) {



                    if (r.getType().equals("+")) {
                        totalIncome += r.getMoney();
                        totalBalance += r.getMoney();
                    } else totalBalance -= r.getMoney();
                    TextView textAmount = (TextView)findViewById(R.id.textCurrently);

                    float ratio;
                    try {
                        ratio = totalBalance / totalIncome;
                    } catch (ArithmeticException e) {
                        ratio = 0;
                    }
                    System.out.println(ratio);
                    if (ratio > 0.5) {
                        textAmount.setTextColor(Color.GREEN);
                        System.out.println(ratio);
                    } else if (ratio >= 0.25) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonUpdate:
                showResult();
                break;
        }
    }




    public void onAdd(View view){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);

        startActivity(intent);
        showResult();
    }
}
