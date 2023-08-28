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

    private lateinit var button: Button
    private lateinit var image: ImageView
    private lateinit var myDate: TextView
    private lateinit var myDatePicker: Button
    private val Image_Request_code = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFloatActionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UI elements
        button = binding.buttonToUpload
        image = binding.UploadImage
        myDate = binding.Date
        myDatePicker = binding.DatePicker

        // Set an onClickListener for the "Upload" button
        button.setOnClickListener {
            pickImageFromGallery()
        }

        // Set an onClickListener for the date picker button
        myDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        DatePickerDialog(
            this,
            datePicker,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        myDate.text = sdf.format(myCalendar.time)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Image_Request_code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Image_Request_code && resultCode == RESULT_OK) {
            // Set the selected image to the ImageView
            image.setImageURI(data?.data)
        }
    }
}
