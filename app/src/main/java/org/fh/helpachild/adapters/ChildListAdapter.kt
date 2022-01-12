package org.fh.helpachild.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import org.fh.cfct.model.Child
import org.fh.helpachild.R

class ChildListAdapter : ListAdapter<Child, ChildListAdapter.ChildViewHolder>(ChildComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.full_name, current.country, current.profile_photo)

    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fullname: TextView = itemView.findViewById(R.id.full_name)
        private val profilephoto: ImageView = itemView.findViewById(R.id.profile_photo)
        private val country: TextView = itemView.findViewById(R.id.country)

        fun bind(text: String?, country: String?, profilePhoto: String?) {
            fullname.text = text
//            profilephoto.text = profilePhoto
            this.country.text =country
        }

        companion object {
            fun create(parent: ViewGroup): ChildViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.child_rv_item, parent, false)
                return ChildViewHolder(view)
            }
        }
    }
    class ChildComparator : DiffUtil.ItemCallback<Child>() {
        override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem.full_name == newItem.full_name
        }
    }

}
