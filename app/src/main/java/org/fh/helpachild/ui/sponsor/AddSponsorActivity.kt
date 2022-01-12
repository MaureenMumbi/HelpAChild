package org.fh.helpachild.ui.sponsor

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.room.ColumnInfo
import dagger.hilt.android.AndroidEntryPoint
import org.fh.cfct.model.Child
import org.fh.cfct.model.Sponsor
import org.fh.helpachild.R
import org.fh.helpachild.utils.utilFunctions
import java.util.*

@AndroidEntryPoint
class AddSponsorActivity : AppCompatActivity() {

    private lateinit var full_name: AppCompatEditText
    private lateinit var country: AppCompatAutoCompleteTextView
    private lateinit var phone_no: AppCompatEditText
    private lateinit var email: AppCompatEditText
    private lateinit var bio: AppCompatEditText
    private lateinit var payment_card: AppCompatEditText

    val countries = arrayOf<String>("Kenya","Uganda", "Tanzania")

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_sponsor)

        val utlFunction = utilFunctions()

        full_name = findViewById(R.id.full_name)
        country = findViewById(R.id.country)
        phone_no = findViewById(R.id.phone_no)
        email = findViewById(R.id.email)
        payment_card = findViewById(R.id.payment_card)
        bio = findViewById(R.id.bio)


        val countryadapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.select_dialog_item, countries)
        country.setThreshold(1) //will start working from first character

        country.setAdapter(countryadapter)

        val button = findViewById<AppCompatButton>(R.id.save)

        button.setOnClickListener {

            var sponsor = Sponsor(
                0,
                full_name.text.toString(),
                country.text.toString(),
                phone_no.text.toString(),
                email.text.toString(),
                payment_card.text.toString(),
                bio.text.toString(),
                utlFunction.getCurrentDateTime()
            )
            val replyIntent = Intent()

            replyIntent.putExtra(NEW_SPONSOR, sponsor)
            setResult(Activity.RESULT_OK, replyIntent)
//            }
            finish()
        }
    }

    companion object {
        const val NEW_SPONSOR = "com.example.android.sponsorlistsql.REPLY"
    }
}
