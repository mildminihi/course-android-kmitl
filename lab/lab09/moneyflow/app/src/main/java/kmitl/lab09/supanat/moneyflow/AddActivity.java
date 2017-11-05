package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private int typeCheck = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

    }


    public void onIncome(View view){
        Button btn_Income = (Button)findViewById(R.id.buttonIncome);
        Button btn_Outcome = (Button)findViewById(R.id.buttonExpence);

        if (typeCheck == 0 || typeCheck ==2) {
            btn_Income.setBackgroundColor(Color.BLUE);
            btn_Outcome.setBackgroundColor(Color.GRAY);
            typeCheck = 1;

        }
        else {
            btn_Income.setBackgroundColor(Color.GRAY);
            typeCheck = 0;

        }

    }

    public void onOutcome(View view){
        Button btn_Income = (Button)findViewById(R.id.buttonIncome);
        Button btn_Outcome = (Button)findViewById(R.id.buttonExpence);
        if (typeCheck == 0 || typeCheck == 1) {
            btn_Income.setBackgroundColor(Color.GRAY);
            btn_Outcome.setBackgroundColor(Color.BLUE);
            typeCheck = 2;

        }
        else {
            btn_Outcome.setBackgroundColor(Color.GRAY);
            typeCheck = 0;

        }

    }
    public void onSave(View view){
        EditText state = (EditText)findViewById(R.id.editText);
        EditText money = (EditText)findViewById(R.id.editText2);
        final String list = state.getText().toString();
        final int moneyResult = Integer.parseInt(money.getText().toString());
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "INCOME").build();

        new AsyncTask<Void, Void, Moneytable>(){

            @Override
            protected Moneytable doInBackground(Void... voids) {


                Moneytable moneytable = new Moneytable();
                moneytable.setType(1);
                moneytable.setState(list);
                moneytable.setMoney(moneyResult);


                messageDB.getMessageInfoDAO().insert(moneytable);

                return null;
            }
        }.execute();
        finish();
    }

}
