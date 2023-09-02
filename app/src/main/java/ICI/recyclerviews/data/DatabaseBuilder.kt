import ICI.recyclerviews.data.ActivityDatabase
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseBuilder {


    fun buildDB(context: Context): ActivityDatabase {
        return Room.databaseBuilder(
            context,
            ActivityDatabase::class.java,
            "room_db_name"
        ).fallbackToDestructiveMigration().build()
    }
}
