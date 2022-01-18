package org.fh.helpachild.ui.child

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.fh.cfct.model.Child
import org.fh.helpachild.R
import org.fh.helpachild.utils.utilFunctions
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddChildActivity : AppCompatActivity() {

    private lateinit var full_name: AppCompatEditText
    private lateinit var gender: AppCompatAutoCompleteTextView
    private lateinit var dob: AppCompatEditText
    private lateinit var country: AppCompatAutoCompleteTextView
    private lateinit var location: AppCompatEditText
    private lateinit var take_photo: AppCompatImageButton
    private lateinit var profile_photo: ImageView
    private lateinit var parent_idno: AppCompatEditText
    private lateinit var bio: AppCompatEditText
    private lateinit var pickadate: AppCompatImageButton

    val genders = arrayOf<String>("Female", "Male")
    val countries = arrayOf<String>("Kenya", "Uganda", "Tanzania")


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_child)

        val utlFunction = utilFunctions()

        full_name = findViewById(R.id.full_name)
        gender = findViewById(R.id.gender)
        dob = findViewById(R.id.dob)
        country = findViewById(R.id.country)
        location = findViewById(R.id.location)
        take_photo = findViewById(R.id.take_photo)
        profile_photo = findViewById(R.id.profile_photo)
        parent_idno = findViewById(R.id.parent_idno)
        bio = findViewById(R.id.bio)
        pickadate = findViewById(R.id.pickadate)


        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.select_dialog_item, genders)
        gender.setThreshold(1) //will start working from first character

        gender.setAdapter(adapter)

        val countryadapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.select_dialog_item, countries)
        country.setThreshold(1) //will start working from first character

        country.setAdapter(countryadapter)

        dob.setOnClickListener { view -> openDatePickerDialog(view) }
        pickadate.setOnClickListener(View.OnClickListener { view -> openDatePickerDialog(view) })
        take_photo.setOnClickListener(View.OnClickListener {
            dispatchTakePictureIntent()
        })
        val button = findViewById<AppCompatButton>(R.id.save)



        button.setOnClickListener {
            val dateofbirth = utlFunction.convertDate(dob.text.toString());
            var profile_photo_path: String? = ""
            if (currentPhotoPath.isNullOrEmpty()) {
                profile_photo_path = ""
            } else {
                profile_photo_path = currentPhotoPath
            }
            var child = Child(
                0,
                full_name.text.toString(),
                gender.text.toString(),
                dateofbirth,
                country.text.toString(),
                location.text.toString(),
                profile_photo_path,
                parent_idno.text.toString(),
                bio.text.toString(),
                utlFunction.getCurrentDateTime()
            )
            val replyIntent = Intent()

            replyIntent.putExtra(NEW_CHILD, child)
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }

    fun openDatePickerDialog(v: View?) {
        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate =
                    dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                dob.setText(selectedDate)
            }, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.datePicker.maxDate = cal.timeInMillis
        datePickerDialog.show()
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    val REQUEST_IMAGE_CAPTURE = 12345

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    Log.e("Error", "Error occurred while creating the File");

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "org.fh.helpachild.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    private fun setPic() {
        profile_photo.visibility = View.VISIBLE
        Log.e("PhotoPath", currentPhotoPath +"----")
        Glide
            .with(this)
            .load(currentPhotoPath)
            .override(300, 300)
            .centerCrop()
            .into(profile_photo)
    }


    companion object {
        const val NEW_CHILD = "com.example.android.childlistsql.REPLY"
    }
}