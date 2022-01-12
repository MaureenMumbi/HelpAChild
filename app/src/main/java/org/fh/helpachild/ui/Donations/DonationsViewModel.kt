package org.fh.helpachild.ui.Donations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DonationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "CFCT (Child Focused Community Transformation)"
    }
    val text: LiveData<String> = _text
}