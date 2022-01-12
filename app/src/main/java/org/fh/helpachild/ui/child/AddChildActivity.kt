package org.fh.helpachild.ui.child

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import dagger.hilt.android.AndroidEntryPoint
import org.fh.cfct.model.Child
import org.fh.helpachild.R
import org.fh.helpachild.utils.utilFunctions
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddChildActivity : AppCompatActivity() {

    private lateinit var full_name: AppCompatEditText
    private lateinit var gender: AppCompatAutoCompleteTextView
    private lateinit var dob: AppCompatEditText
    private lateinit var country: AppCompatAutoCompleteTextView
    private lateinit var location: AppCompatEditText
    private lateinit var profile_photo: AppCompatEditText
    private lateinit var parent_idno: AppCompatEditText
    private lateinit var bio: AppCompatEditText
    private lateinit var pickadate: AppCompatImageButton

    val genders = arrayOf<String>("Female","Male")
    val countries = arrayOf<String>("Kenya","Uganda", "Tanzania")


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_child)

        val utlFunction = utilFunctions()

        full_name = findViewById(R.id.full_name)
        gender = findViewById(R.id.gender)
        dob = findViewById(R.id.dob)
        country = findViewById(R.id.country)
        location = findViewById(R.id.location)
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

        val button = findViewById<AppCompatButton>(R.id.save)

        button.setOnClickListener {
            val dateofbirth = utlFunction.convertDate(dob.text.toString());
            var child = Child(
                0,
                full_name.text.toString(),
                gender.text.toString(),
                dateofbirth,
                country.text.toString(),
                location.text.toString(),
                profile_photo.text.toString(),
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
    companion object {
        const val NEW_CHILD = "com.example.android.childlistsql.REPLY"
    }
}