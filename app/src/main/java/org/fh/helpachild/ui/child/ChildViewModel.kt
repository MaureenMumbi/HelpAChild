package org.fh.helpachild.ui.child

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fh.cfct.model.Child
import org.fh.cfct.model.ChildRepository
import javax.inject.Inject


@HiltViewModel
class ChildViewModel @Inject internal constructor(
    val childRepository: ChildRepository,
) : ViewModel() {


    val allChildren: LiveData<List<Child>> = childRepository.allChildren.asLiveData()
    fun insert(child: Child) = viewModelScope.launch {
        childRepository.addChild(child)
    }
}

