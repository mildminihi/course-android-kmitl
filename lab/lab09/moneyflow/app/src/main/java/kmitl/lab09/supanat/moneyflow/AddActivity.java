package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private int typeCheck = 0;
    public String type = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Add Statement");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button btn_Save = (Button)findViewById(R.id.buttonSave);
        btn_Save.setOnClickListener(this);

    }


    public void onIncome(View view){
        Button btn_Income = (Button)findViewById(R.id.buttonIncome);
        Button btn_Outcome = (Button)findViewById(R.id.buttonExpence);

        if (typeCheck == 0 || typeCheck ==2) {
            btn_Income.setBackgroundColor(Color.parseColor("#771C00"));
            btn_Outcome.setBackgroundColor(Color.parseColor("#333333"));
            typeCheck = 1;

        }
        else {
            btn_Income.setBackgroundColor(Color.BLACK);
            typeCheck = 0;

        }

    }

    public void onOutcome(View view){
        Button btn_Income = (Button)findViewById(R.id.buttonIncome);
        Button btn_Outcome = (Button)findViewById(R.id.buttonExpence);
        if (typeCheck == 0 || typeCheck == 1) {
            btn_Income.setBackgroundColor(Color.parseColor("#333333"));
            btn_Outcome.setBackgroundColor(Color.parseColor("#771C00"));
            typeCheck = 2;

        }
        else {
            btn_Outcome.setBackgroundColor(Color.BLACK);
            typeCheck = 0;

        }

    }



    @Override
    public void onClick(View view) {
        EditText state = (EditText)findViewById(R.id.editText);
        EditText money = (EditText)findViewById(R.id.editText2);

        if (typeCheck == 1){
            type = "Income";
        }
        else if (typeCheck == 2){
            type = "Expense";
        }



        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "MONEYTABLE").build();



        if (state.getText().toString().equals("")|| money.getText().toString().equals("")|| type == ""){
            Toast.makeText(AddActivity.this, "You Have Empty Field", Toast.LENGTH_LONG).show();
        }
        else{
            final String list = state.getText().toString();
            final int moneyResult = Integer.parseInt(money.getText().toString());
            new AsyncTask<Void, Void, MoneyTableResult>(){

                @Override
                protected MoneyTableResult doInBackground(Void... voids) {


                    MoneyTableResult moneyTableResult = new MoneyTableResult();
                    moneyTableResult.setType(type);
                    moneyTableResult.setState(list);
                    moneyTableResult.setMoney(moneyResult);


                    messageDB.getMessageInfoDAO().insert(moneyTableResult);

                    return null;
                }
            }.execute();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
