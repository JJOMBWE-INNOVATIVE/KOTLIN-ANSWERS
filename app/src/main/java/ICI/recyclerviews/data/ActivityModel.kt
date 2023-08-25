package ICI.recyclerviews.data

import ICI.recyclerviews.data.ActivityStatus

data class ActivityModel (
    val image: Int = 1,
    val activityName:String = "",
    val activityTime: String = "",
    var activityDigit: String = "",
    val activityDescription: String = "",
    val activityStatus: ActivityStatus = ActivityStatus.Completed
)
