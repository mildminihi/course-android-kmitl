package kmitl.lab11.supanat.demoinclass.controller;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kmitl.lab11.supanat.demoinclass.R;
import kmitl.lab11.supanat.demoinclass.model.CounterViewModel;

public class MainActivity extends AppCompatActivity {
    private CounterViewModel counterViewModel;
    TextView counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        displayCounter();
    }

    private void displayCounter() {
        counter = findViewById(R.id.textCounter);
        counter.setText(String.valueOf(counterViewModel.getCounter()));
    }

    public void onMe(View view){
        counterViewModel.setCounter(counterViewModel.getCounter() + 1);
        displayCounter();


    }
    public void onClear(View view){
        counterViewModel.setCounter(0);
        displayCounter();
    }
}
