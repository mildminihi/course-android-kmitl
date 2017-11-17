package kmitl.lab11.supanat.demoinclass.model;

import android.arch.lifecycle.ViewModel;

/**
 * Created by mild supanat on 17/11/2560.
 */

public class CounterViewModel extends ViewModel {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
