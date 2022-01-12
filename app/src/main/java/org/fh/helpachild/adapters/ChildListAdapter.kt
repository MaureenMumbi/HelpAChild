package org.fh.helpachild.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import org.fh.cfct.model.Child
import org.fh.cfct.model.Sponsor
import org.fh.helpachild.R
import org.fh.helpachild.ui.child.CHILD_ID
import org.fh.helpachild.ui.child.ChildDetailActivity
import org.fh.helpachild.ui.sponsor.SPONSOR_ID
import org.fh.helpachild.ui.sponsor.SponsorDetailActivity


class ChildListAdapter() :
    ListAdapter<Child, ChildListAdapter.ChildViewHolder>(ChildDiffCallback) {

    /* ViewHolder for Sponsor, takes in the inflated view and the onClick behavior. */
    class ChildViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val fullname: TextView = itemView.findViewById(R.id.full_name)
        private val profilephoto: ImageView = itemView.findViewById(R.id.profile_photo)
        private val country: TextView = itemView.findViewById(R.id.country)
        private var currentChild: Child? = null

        init {
            itemView.setOnClickListener { view ->
                currentChild?.let { child ->
                    val intent = Intent(view.context, ChildDetailActivity()::class.java)
                    intent.putExtra(CHILD_ID, child.id)
                    view.context.startActivity(intent)
                }
            }
        }


        fun bind(child: Child) {
            currentChild = child
            fullname.text = child.full_name
            country.text = child.country

        }
    }

    /* Creates and inflates view and return SponsorViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_rv_item, parent, false)
        return ChildViewHolder(view)
    }

    /* Gets current sponsor and uses it to bind view. */
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = getItem(position)
        holder.bind(child)

    }
}

object ChildDiffCallback : DiffUtil.ItemCallback<Child>() {
    override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem.id == newItem.id
    }
}

//class ChildListAdapter : ListAdapter<Child, ChildListAdapter.ChildViewHolder>(ChildComparator()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
//        return ChildViewHolder.create(parent)
//    }
//
//    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
//        val current = getItem(position)
//        holder.bind(current.full_name, current.country, current.profile_photo)
//
//    }
//
//    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val fullname: TextView = itemView.findViewById(R.id.full_name)
//        private val profilephoto: ImageView = itemView.findViewById(R.id.profile_photo)
//        private val country: TextView = itemView.findViewById(R.id.country)
//        private var currentChild: Child? = null
//        init {
//            itemView.setOnClickListener { view ->
//                currentChild?.let { child ->
//                    val intent = Intent(view.context, ChildDetailActivity()::class.java)
//                    intent.putExtra(CHILD_ID, child.id)
//                    view.context.startActivity(intent)
//                }
//            }
//        }
//
//        fun bind(text: String?, country: String?, profilePhoto: String?) {
//            fullname.text = text
////            profilephoto.text = profilePhoto
//            this.country.text =country
//
////            if (profilePhoto != null) {
////                profilephoto.setImageResource(profilePhoto.image)
////            } else {
////                profilephoto.setImageResource(R.drawable.generic_person)
////            }
//        }
//
//        companion object {
//            fun create(parent: ViewGroup): ChildViewHolder {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.child_rv_item, parent, false)
//                return ChildViewHolder(view)
//            }
//        }
//    }
//    class ChildComparator : DiffUtil.ItemCallback<Child>() {
//        override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
//            return oldItem.full_name == newItem.full_name
//        }
//    }
//
//}
//
