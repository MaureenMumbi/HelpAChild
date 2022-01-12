package org.fh.helpachild.ui.sponsor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SponsorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sponsor Fragment"
    }
    val text: LiveData<String> = _text
}