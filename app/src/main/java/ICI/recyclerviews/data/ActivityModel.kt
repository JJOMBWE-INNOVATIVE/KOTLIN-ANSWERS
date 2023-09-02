package ICI.recyclerviews.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActivityModel(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val image: String?,
    val activityName:String = "",
    val activityTime: String = "",
    var activityDigit: String = "",
    val activityDescription: String = "",
    val activityStatus: ActivityStatus = ActivityStatus.Completed,
    val floatActionButton: Int = 1
)
