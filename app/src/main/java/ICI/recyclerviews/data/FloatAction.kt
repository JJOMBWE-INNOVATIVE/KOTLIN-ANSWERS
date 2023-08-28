package ICI.recyclerviews.data

import ICI.recyclerviews.databinding.ActivityFloatActionBinding
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FloatAction : AppCompatActivity() {

  private  lateinit var button : Button
  private  lateinit var image : ImageView

         private lateinit var myDate : TextView
          private  lateinit var myDatePicker : Button

    companion object{
        val Image_Request_code = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFloatActionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // for image upload
        button = binding.buttonToUpload
        image = binding.UploadImage

        button.setOnClickListener {
            pickImageGalery()
            /*Press Alt + Enter*/
        }
        // for date
        myDate = binding.Date
        myDatePicker = binding.DatePicker

        val myCalender = Calendar.getInstance()
        DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updatelable(myCalender)
        }

        myDatePicker.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, month)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

            DatePickerDialog(
                this,
                datePicker,
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


    }

    private fun updateLabel() {

    }

    private fun updatelable(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        myDatePicker.text =  sdf.format(myCalender.time)

    }

    private fun pickImageGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Image_Request_code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Image_Request_code && requestCode == RESULT_OK){
            image.setImageURI(data?.data)
        }

    }

}



