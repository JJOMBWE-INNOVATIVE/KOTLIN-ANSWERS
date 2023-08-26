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

        val image = intent.getIntExtra("activityImage",R.drawable.wakeup)
        val name = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("activityTime")
        val digit = intent.getStringExtra("activityDigit")
        val description = intent.getStringExtra("activityDescription")
        val status = intent.getStringExtra("activityStatus")
        val serializable = intent.getSerializableExtra("activityStatus") as? ActivityStatus

//        Log.d("Serializable","$serializable")
        val imageIcon = binding.DescriptionImage
        imageIcon.setImageResource(image)

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