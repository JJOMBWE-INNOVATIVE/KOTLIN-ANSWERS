package ICI.recyclerviews.data

import DatabaseBuilder
import ICI.recyclerviews.databinding.ActivityFloatActionBinding
import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

@Suppress("DEPRECATION")
class FloatAction : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var myImage: ImageView
    private lateinit var myDate: TextView
    private lateinit var myDatePicker: Button
    private lateinit var myTimePicker : Button
    private val Image_Request_code = 100
    private val cameraCode = 1
    private val fileCode = 2
    private lateinit var TakePicture : Button
    private lateinit var binding:ActivityFloatActionBinding
    private var imageUri: Uri?= null
    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFloatActionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image  = binding.imageCapture
        image.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA, cameraCode)
        }

        val TakePicture = binding.TakePicture
        TakePicture.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA, cameraCode)
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, fileCode)
        }


        val activityName = binding.activityName.editText.toString()
        val activityDescription = binding.activityDescription.editText.toString()


        // Initialize UI elements
        button = binding.buttonToUpload
        myImage = binding.UploadImage
        myDate = binding.Date
        myDatePicker = binding.DatePicker
        myTimePicker = binding.TimePicker
        binding.saveActivity

        binding.saveActivity.setOnClickListener {
            val dateTime = binding.Date.text.toString() +  binding.TimePicker.text.toString()
            coroutineScope.launch(Dispatchers.IO){
                DatabaseBuilder()
                    .buildDB(this@FloatAction)
                    .ActivityDao().
                    insertActivity(
                    ActivityModel(null,
                        imageUri.toString(),
                        activityName,
                        activityDescription,
                        dateTime,
                        ActivityStatus.Completed.toString()
                    )
                )
            }


        }

        // Set an onClickListener for the "Upload" button
        button.setOnClickListener {
            pickImageFromGallery()
        }


        // Set an onClickListener for the date picker button
        myDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.TimePicker.setOnClickListener {
            // Get Current Time
            // Get Current Time
            val c = Calendar.getInstance()
            val mHour = c[Calendar.HOUR_OF_DAY]
            val mMinute = c[Calendar.MINUTE]


            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(this,
                { _, hourOfDay, minute ->
                    binding.TimePicker.text = "$hourOfDay:$minute"
                },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
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


    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this,
                permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@FloatAction, arrayOf(permission), requestCode)
        } else {
            if (requestCode == 1) {

                val values = ContentValues()
                values.put(MediaStore.Images.Media.TITLE, "New Picture")
                values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

                //camera intent
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                // set filename
                val name = UUID.randomUUID()
                //val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val vFilename = "MY picture$name.jpg"
                val myDirectory = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES)

                // set direcory folder
                val file = File(myDirectory, vFilename)
                val image_uri = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileProvider",
                    file
                )


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)



                startActivityForResult(takePictureIntent, cameraCode)
            } else if(requestCode == 2) {
                val filePictureIntent = Intent(MediaStore.ACTION_PICK_IMAGES)
                startActivityForResult(filePictureIntent, fileCode)
                //Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, you can proceed to take a picture
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()

                // Place your camera capture code here

            } else {
                // Permission was denied
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == fileCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, you can proceed to pick an image
                Toast.makeText(this, "File Permission Granted", Toast.LENGTH_SHORT).show()
                pickImageFromGallery() // Call a method to pick an image
            } else {
                // Permission was denied
                Toast.makeText(this, "File Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }



    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK) {
            // If the operation was not successful, return without further action
            return
        }

        if (requestCode == Image_Request_code) {
            // This is for selecting an image from the gallery
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                // Set the selected image to the ImageView
                myImage.setImageURI(selectedImageUri)
                // You can also store this URI in your 'imageUri' variable if needed.
                imageUri = selectedImageUri
            }
        } else if (requestCode == cameraCode) {
            Log.d("data","${data?.data}")
            Log.d("extras","${data?.extras}")

            // This is for capturing an image from the camera
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                binding.imageCapture.setImageBitmap(imageBitmap)
                // You can do more with the captured image here if needed.
            } else {
                // Handle the case where imageBitmap is null
                Toast.makeText(this, "Failed to capture image from camera", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == fileCode) {
            // This is for selecting an image from the file system
            val imageBitmap = data?.data
            if (imageBitmap != null) {
                imageUri = imageBitmap
                // Set the selected image to the ImageView
                binding.imageCapture.setImageURI(imageBitmap)
                // You can also store this URI in your 'imageUri' variable if needed.
            } else {
                // Handle the case where imageBitmap is null
                Toast.makeText(this, "Failed to select image from file system", Toast.LENGTH_SHORT).show()
            }
        }
    }





    // Function to check and request permission.












}