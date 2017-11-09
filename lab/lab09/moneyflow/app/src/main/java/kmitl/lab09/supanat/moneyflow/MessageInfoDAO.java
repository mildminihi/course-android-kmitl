package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mild supanat on 5/11/2560.
 */

@Dao
interface MessageInfoDAO {
    @Query("SELECT * FROM MONEYTABLE")
    List<MoneyTableResult> findAll();


    @Query("DELETE FROM MONEYTABLE")
    void deleteAll();

    @Insert
    void insert(MoneyTableResult moneyTableResult);
}
