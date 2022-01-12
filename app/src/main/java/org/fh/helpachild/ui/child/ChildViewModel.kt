package org.fh.helpachild.ui.child

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.fh.cfct.model.Child
import org.fh.cfct.model.ChildRepository
import javax.inject.Inject

//class ChildViewModel(private val repository: ChildRepository) : ViewModel() {
@HiltViewModel
class ChildViewModel @Inject internal constructor(
    val childRepository: ChildRepository,
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Child Fragment"
    }
    val text: LiveData<String> = _text
    val allChildren: LiveData<List<Child>> = childRepository.allChildren.asLiveData()
    fun insert(child: Child) = viewModelScope.launch {
        childRepository.addChild(child)
    }
}

//class ChildViewModelFactory(private val repository: ChildRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ChildViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ChildViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
