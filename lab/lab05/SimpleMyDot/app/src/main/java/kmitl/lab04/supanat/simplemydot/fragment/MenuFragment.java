package kmitl.lab04.supanat.simplemydot.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



import kmitl.lab04.supanat.simplemydot.R;
import kmitl.lab04.supanat.simplemydot.model.Dot;
import kmitl.lab04.supanat.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private static final String DOT = "dot";
    private static final String DOTS = "dots";
    private static final String POSITION = "position";

    private Button Save;
    private Button Cancel;
    private Dot dot;
    private Dots dots;
    private int dotPosition;
    private EditText CenterX;
    private EditText CenterY;
    private EditText Radius;
    private View ColorBar;
    private int color;
    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(Dot dot, Dots dots, int dotPosition) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putParcelable(DOT, (Parcelable) dot);
        args.putParcelable(DOTS, (Parcelable) dots);
        args.putInt(POSITION, dotPosition);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        dot = getArguments().getParcelable(DOT);
        dots = getArguments().getParcelable(DOTS);
        dotPosition = getArguments().getInt(POSITION);

        CenterX = (EditText) rootView.findViewById(R.id.editx);
        CenterY = (EditText) rootView.findViewById(R.id.edity);
        Radius = (EditText) rootView.findViewById(R.id.editradius);

        Save = (Button) rootView.findViewById(R.id.save);
        Cancel = (Button) rootView.findViewById(R.id.cancel);


        Save.setOnClickListener(this);
        Cancel.setOnClickListener(this);

        if (dot != null) {
            CenterX.setText(String.valueOf(dot.getCenterX()));
            CenterY.setText(String.valueOf(dot.getCenterY()));
            Radius.setText(String.valueOf(dot.getRadius()));

        }
        return rootView;}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                getActivity().onBackPressed();
                break;
            case R.id.save:
                save();
                getActivity().onBackPressed();
                break;

        }
    }

    private void save() {
        dot.setCenterX(Integer.parseInt(String.valueOf(CenterX.getText())));
        dot.setCenterY(Integer.parseInt(String.valueOf(CenterY.getText())));
        dot.setRadius(Integer.parseInt(String.valueOf(Radius.getText())));
        dot.setColor(color);
        dots.editAttributeDot(dotPosition, dot);
    }
}
