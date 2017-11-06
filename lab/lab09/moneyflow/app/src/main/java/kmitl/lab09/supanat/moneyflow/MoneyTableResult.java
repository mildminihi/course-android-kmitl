package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mild supanat on 6/11/2560.
 */

@Entity(tableName = "MONEYTABLE")
public class MoneyTableResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TYPE")
    private String type;

    @ColumnInfo(name = "STATE")
    private String state;

    @ColumnInfo(name = "MONEY")
    private int money;

    @Override
    public String toString(){
        return String.format("%s : %s: %d", type, state, money);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
