package org.fh.helpachild.ui.child

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fh.cfct.model.Child
import org.fh.cfct.model.ChildRepository
import org.fh.cfct.model.Sponsor
import org.fh.cfct.model.SponsorRepository
import javax.inject.Inject

@HiltViewModel
class ChildDetailViewModel @Inject internal constructor(
    val childRepository: ChildRepository,
) : ViewModel() {


    fun removeChild(childid: Long) = viewModelScope.launch {
        childRepository.deleteChild(childid)
    }


    fun getChild(id:Long): LiveData<Child> {
        val result = MutableLiveData<Child>()
        viewModelScope.launch {
            val child = childRepository.getChild(id)
            result.postValue(child)
        }
        return result
    }

}