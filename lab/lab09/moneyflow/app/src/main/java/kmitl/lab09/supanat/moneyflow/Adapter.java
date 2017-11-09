package kmitl.lab09.supanat.moneyflow;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mild supanat on 9/11/2560.
 */

public class Adapter extends ArrayAdapter<MoneyTableResult> {

    private List<MoneyTableResult> moneyTableResults;
    private Context context;
    private int resource;
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MoneyTableResult> objects) {
        super(context, resource, objects);
        this.context = context;
        this.moneyTableResults = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        TextView textViewType = view.findViewById(R.id.textViewType);
        TextView textViewState = view.findViewById(R.id.textViewState);
        TextView textViewMoney = view.findViewById(R.id.textViewMoney);
        if (moneyTableResults.get(position).getType().equals("Income")){
            textViewState.setBackgroundColor(Color.parseColor("#6aa84f"));
        }
        else {
            textViewState.setBackgroundColor(Color.parseColor("#cc0000"));
            textViewState.setTextColor(Color.parseColor("#ffffff"));
            textViewType.setTextColor(Color.parseColor("#ffffff"));
            textViewMoney.setTextColor(Color.parseColor("#ffffff"));
        }

        textViewType.setText(moneyTableResults.get(position).getType());
        textViewState.setText(moneyTableResults.get(position).getState());
        textViewMoney.setText((moneyTableResults.get(position).getMoney())+"");

        return view;
    }
}
