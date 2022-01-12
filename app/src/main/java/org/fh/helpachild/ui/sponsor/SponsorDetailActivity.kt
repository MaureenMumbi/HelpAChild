package org.fh.helpachild.ui.sponsor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import org.fh.helpachild.R
import org.fh.helpachild.databinding.ActivitySponsorDetailBinding
import org.fh.helpachild.databinding.FragmentSponsorBinding

@AndroidEntryPoint
class SponsorDetailActivity : AppCompatActivity() {


    private val sponsorDetailViewModel: SponsorDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsor_detail)



        var currentSponsorId: Long? = null

        /* Connect variables to UI elements. */
        val full_name: TextView = findViewById(R.id.full_name)
        val contact: TextView = findViewById(R.id.contact)
        val email: TextView = findViewById(R.id.email)
        val country: TextView = findViewById(R.id.country)
        val payment_card: TextView = findViewById(R.id.payment_card)
        val bio: TextView = findViewById(R.id.bio)
        val removeSponsorButton: Button = findViewById(R.id.remove_button)
        val editSponsorButton: Button = findViewById(R.id.update_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentSponsorId = bundle.getLong(SPONSOR_ID)
        }
        Log.e("ID",currentSponsorId.toString()+"---")


        /* If currentSponserd is not null, get corresponding sponser and set details */
        currentSponsorId?.let {

          sponsorDetailViewModel.getSponsor(it).observe(this, Observer { sponsor ->

               full_name.text = sponsor.full_name
               contact.text = sponsor.phone_no
               email.text = sponsor.email
               country.text = sponsor.country
               payment_card.text = sponsor.payment_card
               bio.text = sponsor.bio

            })

            removeSponsorButton.setOnClickListener {
                if (currentSponsorId != null) {
                    sponsorDetailViewModel.removeSponsor(currentSponsorId)
                }
                finish()
            }


        }

    }

}
