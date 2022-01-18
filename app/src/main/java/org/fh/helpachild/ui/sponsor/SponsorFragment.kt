package org.fh.helpachild.ui.sponsor


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.fh.cfct.model.Child
import org.fh.cfct.model.Sponsor
import org.fh.helpachild.R
import org.fh.helpachild.adapters.ChildListAdapter
import org.fh.helpachild.adapters.SponsorListAdapter
import org.fh.helpachild.databinding.FragmentSponsorBinding
import org.fh.helpachild.ui.child.AddChildActivity
import org.fh.helpachild.ui.child.ChildViewModel

const val SPONSOR_ID = "sponsor_id"
@AndroidEntryPoint
class SponsorFragment : Fragment() {

    private val sponsorViewModel: SponsorViewModel by viewModels()

    private var _binding: FragmentSponsorBinding? = null
    private val newSponsorActivityRequestCode = 1
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSponsorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.sponsorList
        val sponsorlistadapter = SponsorListAdapter()
        recyclerView.adapter = sponsorlistadapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sponsorViewModel.allSponsors.observe(viewLifecycleOwner) { sponsor ->
            // Update the cached copy of the children in the adapter.
            if(sponsor.isEmpty()) {
                binding.noSponsorLayout.visibility = View.VISIBLE
            }else{
                binding.noSponsorLayout.visibility = View.GONE
            }
            sponsor.let { sponsorlistadapter.submitList(it)

            }
        }

        val fab = binding.addSponsorFab
        fab.setOnClickListener {
            val intent = Intent(context, AddSponsorActivity::class.java)
            startActivityForResult(intent, newSponsorActivityRequestCode)
        }



        return root
    }

override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
    super.onActivityResult(requestCode, resultCode, intentData)

    if (requestCode == newSponsorActivityRequestCode && resultCode == Activity.RESULT_OK) {
        intentData?.getParcelableExtra<Sponsor>(AddSponsorActivity.NEW_SPONSOR)?.let { reply ->
            val sponsor =  reply
            Log.e("Sponsor", sponsor.phone_no+"--");
            addSponsor(sponsor)

        }
    } else {
        Toast.makeText(
            context,
            R.string.empty_not_saved,
            Toast.LENGTH_LONG
        ).show()
    }
}

private fun addSponsor(sponsor: Sponsor) {
    with(sponsorViewModel) {
        insert(sponsor)
    }
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}