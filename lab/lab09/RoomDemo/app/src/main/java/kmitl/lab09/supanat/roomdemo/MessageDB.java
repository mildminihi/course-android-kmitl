package kmitl.lab09.supanat.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {UserInfo.class}, version = 1)
public abstract class MessageDB extends RoomDatabase {
    public abstract MessageInfoDAO getMessageInfoDAO();


}
