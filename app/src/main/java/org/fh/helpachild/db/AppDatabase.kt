package org.fh.cfct.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.fh.cfct.model.*
import org.fh.cfct.utils.DATABASE_NAME
import java.util.*


@Database(

    entities = [Child::class, ChildResources::class, Donation::class, Sponsor::class, ChildSponsorCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun ChildDao(): ChildDao
    abstract fun ChildResourcesDao(): ChildResourcesDao
    abstract fun DonationDao(): DonationDao
    abstract fun SponsorDao(): SponsorDao

    companion object {


        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(
//            context: Context
//        ): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    DATABASE_NAME
//                )
//                    // Wipes and rebuilds instead of migrating if no Migration object.
//                    // Migration is not part of this codelab.
//                    .fallbackToDestructiveMigration()
////                    .addCallback(AppDatabaseCallback(scope))
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//
//
//        private class AppDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                INSTANCE?.let { database ->
//                    scope.launch {
//                        var childDao = database.ChildDao()
//
//                        // Delete all content here.
//                        childDao.deleteAll()
//
//                        // Add sample child.
//
//                        var child = Child(1,"Maureen Maina","Female",Calendar.getInstance(),
//                            "Kenya", "Kikuyu","fileuri","2394945","Bright girl", Calendar.getInstance())
//                        childDao.insert(child)
//
//                    }
//                }
//            }
//        }
//
//    }

}