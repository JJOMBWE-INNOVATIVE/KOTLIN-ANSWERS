package ICI.recyclerviews

import ICI.recyclerviews.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val List = ArrayList<ActivityModel>()
        List.add(ActivityModel(R.drawable.wakeup,"Wake up","Morning","6:00 AM"))
        List.add(ActivityModel(R.drawable.pray,"Pray","Morning","6:15-7:00 AM"))
        List.add(ActivityModel(R.drawable.breakfast,"Eat break fast","Morning","7:00-7:15 AM"))
        List.add(ActivityModel(R.drawable.jogging,"Go Jogging","Morning","7:15-8:00 AM"))
        List.add(ActivityModel(R.drawable.work,"Go to Work","Morning","8:30-2:00 PM"))
        List.add(ActivityModel(R.drawable.movie,"Watch a Movie","Afternoon","2:30-5:00 PM"))
        List.add(ActivityModel(R.drawable.cook,"Cook dinner","Evening","5:30-7:30 PM"))
        List.add(ActivityModel(R.drawable.dinner,"Eat dinner","Evening","7:40:8:40 PM"))
        List.add(ActivityModel(R.drawable.pray,"Pray","Evening","9:00-10:00 PM"))
        List.add(ActivityModel(R.drawable.sleep,"Sleep","Evening","10:00 PM"))





        val activityAdapter = ActivityAdapter(this,List)
        val recyclerViews = binding.recyclerView
        recyclerViews.adapter = activityAdapter
       recyclerViews.layoutManager = LinearLayoutManager(this)
       //recyclerViews.layoutManager = GridLayoutManager(this,2)



    }
}
