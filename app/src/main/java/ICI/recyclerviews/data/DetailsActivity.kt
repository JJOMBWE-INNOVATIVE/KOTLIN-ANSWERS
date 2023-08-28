package ICI.recyclerviews.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ICI.recyclerviews.R
import ICI.recyclerviews.databinding.ActivityDetailsBinding
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val one = intent.getIntExtra("activityImage",R.drawable.wakeup)
        val two = intent.getIntExtra("activityImage",R.drawable.pray)
        val three = intent.getIntExtra("activityImage",R.drawable.breakfast)
        val four = intent.getIntExtra("activityImage",R.drawable.jogging)
        val five = intent.getIntExtra("activityImage",R.drawable.work)
        val six = intent.getIntExtra("activityImage",R.drawable.movie)
        val seven = intent.getIntExtra("activityImage",R.drawable.cook)
        val eight = intent.getIntExtra("activityImage",R.drawable.dinner)
        val nine = intent.getIntExtra("activityImage",R.drawable.pray)
        val ten = intent.getIntExtra("activityImage",R.drawable.sleep)

        val name = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("activityTime")
        val digit = intent.getStringExtra("activityDigit")
        val description = intent.getStringExtra("activityDescription")
        val status = intent.getStringExtra("activityStatus")
        val serializable = intent.getSerializableExtra("activityStatus") as? ActivityStatus

//        Log.d("Serializable","$serializable")
        val imageIcon = binding.DescriptionImage
        imageIcon.setImageResource(one)

        val myActivity = binding.Activity
        myActivity.text = name

        val myTime = binding.Time
        myTime.text = time

        val myDigit = binding.DigitTime
        myDigit.text = digit

        val myDescription = binding.Description
        myDescription.text = description

        val myStatus = binding.Status
        myStatus.text = status

        val mySerializable = binding.Status
        mySerializable.text = serializable?.toString()



    }
}