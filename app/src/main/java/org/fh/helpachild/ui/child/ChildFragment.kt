package org.fh.helpachild.ui.child

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.fh.cfct.model.Child
import org.fh.helpachild.R
import org.fh.helpachild.adapters.ChildListAdapter
import org.fh.helpachild.databinding.FragmentChildBinding
const val CHILD_ID = "child_id"
@AndroidEntryPoint
class ChildFragment : Fragment() {
    val newChildActivityRequestCode = 1


    private val childViewModel: ChildViewModel by viewModels()

    private var _binding: FragmentChildBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChildBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerview
        val adapter = ChildListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        childViewModel.allChildren.observe(viewLifecycleOwner) { children ->
            // Update the cached copy of the children in the adapter.
            children.let { adapter.submitList(it) }
        }

        val fab = binding.fab
        fab.setOnClickListener {
            val intent = Intent(context, AddChildActivity::class.java)


            startActivityForResult(intent, newChildActivityRequestCode)
        }

        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newChildActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getParcelableExtra<Child>(AddChildActivity.NEW_CHILD)?.let { reply ->
                val child =  reply
                addChild(child)

            }
        } else {
            Toast.makeText(
                context,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun addChild(child: Child) {
        with(childViewModel) {
           insert(child)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}