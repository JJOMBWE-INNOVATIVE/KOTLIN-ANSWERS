package ICI.recyclerviews.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ActivityDao {
    @Insert
    fun insertActivity(data:ActivityModel)

    @Query("Select * from ActivityModel")
    fun getAllActivities(): List<ActivityModel>
}