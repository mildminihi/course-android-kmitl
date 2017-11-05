package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mild supanat on 5/11/2560.
 */

@Dao
public interface MessageInfoDAO {
    @Query("SELECT * FROM INCOME")
    List<Moneytable> findAll();

    @Insert
    void insert(Moneytable moneytable);
}
