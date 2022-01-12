package org.fh.helpachild.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.fh.cfct.model.Sponsor
import org.fh.helpachild.R
import org.fh.helpachild.ui.sponsor.SPONSOR_ID
import org.fh.helpachild.ui.sponsor.SponsorDetailActivity

class SponsorListAdapter() :
    ListAdapter<Sponsor, SponsorListAdapter.SponsorViewHolder>(SponsorDiffCallback) {

    /* ViewHolder for Sponsor, takes in the inflated view and the onClick behavior. */
    class SponsorViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val fullnameTextView: TextView = itemView.findViewById(R.id.full_name)
        private val contactTextView: TextView = itemView.findViewById(R.id.phone_no)
        private val countryTextView: TextView = itemView.findViewById(R.id.country)
        private var currentSponsor: Sponsor? = null

        init {
            itemView.setOnClickListener { view ->
                currentSponsor?.let { sponsor ->
                    val intent = Intent(view.context, SponsorDetailActivity()::class.java)
                    intent.putExtra(SPONSOR_ID, sponsor.id)
                    view.context.startActivity(intent)
                }
            }
        }


        fun bind(sponsor: Sponsor) {
            currentSponsor = sponsor

            fullnameTextView.text = sponsor.full_name
            contactTextView.text = sponsor.phone_no
            countryTextView.text = sponsor.country


        }
    }

    /* Creates and inflates view and return SponsorViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sponsor_rv_item, parent, false)
        return SponsorViewHolder(view)
    }

    /* Gets current sponsor and uses it to bind view. */
    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        val sponsor = getItem(position)
        holder.bind(sponsor)

    }
}

object SponsorDiffCallback : DiffUtil.ItemCallback<Sponsor>() {
    override fun areItemsTheSame(oldItem: Sponsor, newItem: Sponsor): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Sponsor, newItem: Sponsor): Boolean {
        return oldItem.phone_no == newItem.phone_no
    }
}
