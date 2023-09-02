package ICI.recyclerviews.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ActivityModel::class], version = 1.1.toInt())
abstract class ActivityDatabase: RoomDatabase() {
   abstract fun ActivityDao(): ActivityDao
}
