package kmitl.lab09.supanat.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mild supanat on 3/11/2560.
 */

@Dao
interface MessageInfoDAO {
    @Query("SELECT * FROM USER_INFO")
    List<UserInfo> findAll();

    @Insert
    void insert(UserInfo userInfo);
}
