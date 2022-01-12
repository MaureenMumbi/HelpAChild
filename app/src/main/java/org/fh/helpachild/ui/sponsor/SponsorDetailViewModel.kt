package org.fh.helpachild.ui.sponsor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fh.cfct.model.Sponsor
import org.fh.cfct.model.SponsorRepository
import javax.inject.Inject

@HiltViewModel
class SponsorDetailViewModel @Inject internal constructor(
    val sponsorRepository: SponsorRepository,
) : ViewModel() {


    fun removeSponsor(sponsorid: Long) = viewModelScope.launch {
        sponsorRepository.deleteSponsor(sponsorid)
    }


    fun getSponsor(id:Long): LiveData<Sponsor> {
        val result = MutableLiveData<Sponsor>()
        viewModelScope.launch {
            val sponsor = sponsorRepository.getSponsor(id)
            result.postValue(sponsor)
        }
        return result
    }

}