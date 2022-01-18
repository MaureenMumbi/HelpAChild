package org.fh.helpachild.ui.child

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.fh.helpachild.R
import org.fh.helpachild.ui.sponsor.SPONSOR_ID
import org.fh.helpachild.ui.sponsor.SponsorDetailViewModel
import org.fh.helpachild.utils.utilFunctions

@AndroidEntryPoint
class ChildDetailActivity  : AppCompatActivity() {

    private val childDetailViewModel: ChildDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_detail)



        var currentChildId: Long? = null

        /* Connect variables to UI elements. */
        val full_name: TextView = findViewById(R.id.full_name)
//        val dob: TextView = findViewById(R.id.dob)
        val gender: TextView = findViewById(R.id.gender)
        val country: TextView = findViewById(R.id.country)
        val location: TextView = findViewById(R.id.location)
        val parent_idno: TextView = findViewById(R.id.parent_idno)
        val bio: TextView = findViewById(R.id.bio)
        val profile_photo: ImageView = findViewById(R.id.profile_photo)
        val removeChildButton: Button = findViewById(R.id.remove_button)
        val editChildButton: Button = findViewById(R.id.update_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentChildId = bundle.getLong(CHILD_ID)
        }

        val utlFunction = utilFunctions()
        /* If currentChildId is not null, get corresponding child and set details */
        currentChildId?.let {

            childDetailViewModel.getChild(it).observe(this, Observer { child ->

                full_name.text = child.full_name
                gender.text = child.gender
                country.text = child.country
                location.text = child.location
                parent_idno.text = child.parent_idno
                Glide
                    .with(this)
                    .load(child.profile_photo)
                    .override(300, 300)
                    .centerCrop()
                    .into(profile_photo)
                bio.text = child.bio

            })

            removeChildButton.setOnClickListener {
                if (currentChildId != null) {
                    childDetailViewModel.removeChild(currentChildId)
                }
                finish()
            }


        }

    }

}