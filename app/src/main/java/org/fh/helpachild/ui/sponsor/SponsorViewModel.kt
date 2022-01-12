package org.fh.helpachild.ui.sponsor

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fh.cfct.model.Child
import org.fh.cfct.model.ChildRepository
import org.fh.cfct.model.Sponsor
import org.fh.cfct.model.SponsorRepository
import javax.inject.Inject


@HiltViewModel
class SponsorViewModel @Inject internal constructor(
    val sponsorRepository: SponsorRepository,
) : ViewModel() {

    val allSponsors: LiveData<List<Sponsor>> = sponsorRepository.getAllSponsors().asLiveData()
    fun insert(sponsor: Sponsor) = viewModelScope.launch {
        sponsorRepository.addSponsor(sponsor)
    }
}