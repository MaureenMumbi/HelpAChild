package org.fh.cfct.model

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChildRepository @Inject constructor(private val childDao: ChildDao) {

    val allChildren: Flow<List<Child>> = childDao.getAllChildren()

//    val allChildrenWithSponsors : List<ChildWithSponsors> = childDao.getChildwithSponsor()

    suspend fun getChild(childId: Long) = childDao.getChildById(childId)

    suspend fun addChild(child: Child) {
        childDao.insert(child)
    }
    suspend fun updateChild(child: Child) {
        childDao.insert(child)
    }
    suspend fun deleteChild(childId: Long) {
        childDao.deleteByID(childId)
    }

}
