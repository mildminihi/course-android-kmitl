package kmitl.lab09.supanat.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mild supanat on 5/11/2560.
 */

@Database(entities = {Moneytable.class}, version = 1)
public abstract class MessageDB extends RoomDatabase{
    public abstract MessageInfoDAO getMessageInfoDAO();
}
