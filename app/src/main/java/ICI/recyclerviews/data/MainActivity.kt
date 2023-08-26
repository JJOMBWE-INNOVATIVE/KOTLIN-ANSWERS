package ICI.recyclerviews.data

import ICI.recyclerviews.R
import ICI.recyclerviews.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val List = ArrayList<ActivityModel>()


        List.add(ActivityModel(R.drawable.wakeup,"Wake up","Morning",
            "6:00 AM","Wake up and seize the day with renewed energy and purpose." +
                    "\n Rise and shine to embrace a brand new day filled with opportunities.",
            activityStatus =ActivityStatus.Completed))

        List.add(ActivityModel(R.drawable.pray,"Pray","Morning",
            "6:15-7:00 AM","Find inner peace and solace through moments of prayer.\n" +
                    "Embrace the dawn, awaken your spirit.",
            activityStatus = ActivityStatus.Completed))

        List.add(ActivityModel(R.drawable.breakfast,"Eat break fast","Morning",
            "7:00-7:15 AM","Start your day with a hearty breakfast, fuel for success.",
            activityStatus = ActivityStatus.Completed))

        List.add(ActivityModel(R.drawable.jogging,"Go Jogging","Morning",
            "7:15-8:00 AM","Jogging: an invigorating way to boost fitness and clear your mind.",
        activityStatus = ActivityStatus.Postponed))

        List.add(ActivityModel(R.drawable.work,"Go to Work","Morning",
            "8:30-2:00 PM","Work: where goals meet action, and progress takes shape.\n" +
                    "Working smart is my way", activityStatus = ActivityStatus.Completed))

        List.add(ActivityModel(R.drawable.movie,"Watch a Movie","Afternoon",
            "2:30-5:00 PM","Movies: A portal to different worlds, emotions, and adventures.\n" +
                    "Enjoy the world of vision", activityStatus = ActivityStatus.Completed))

        List.add(ActivityModel(R.drawable.cook,"Cook dinner","Evening",
            "5:30-7:30 PM","Cooking: Creating delightful flavors, one recipe at a time.\n" +
                    "Eating is healthy, please enjoy", activityStatus = ActivityStatus.Pending))
        List.add(ActivityModel(R.drawable.dinner,"Eat dinner","Evening",
            "7:40:8:40 PM","Dinner: A delicious pause to savor the day's flavors.",
            activityStatus = ActivityStatus.Failed))

        List.add(ActivityModel(R.drawable.pray,"Pray","Evening",
            "9:00-10:00 PM","Find inner peace and solace through moments of prayer.\n" +
                    "Embrace the dawn, awaken your spirit.", activityStatus = ActivityStatus.Completed))
        List.add(ActivityModel(R.drawable.sleep,"Sleep","Evening",
            "10:00 PM","Sleep: The body's nightly reset, preparing for a new day.\n" +
                    "Eat before you sleep", activityStatus = ActivityStatus.Pending))



        val activityAdapter = ActivityAdapter(this,List)
        val recyclerViews = binding.recyclerView
        recyclerViews.adapter = activityAdapter
       recyclerViews.layoutManager = LinearLayoutManager(this)
        //recyclerViews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
       //recyclerViews.layoutManager = GridLayoutManager(this,2)




    }
}
