package com.example.theringingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.theringingapp.data.dao.MethodDao
import com.example.theringingapp.data.dto.Method
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Method::class), version = 1, exportSchema = false)
public abstract class MethodRoomDatabase : RoomDatabase() {

    abstract fun methodDao(): MethodDao

    companion object {
        @Volatile
        private var INSTANCE: MethodRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MethodRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MethodRoomDatabase::class.java,
                    "method_table"
                )
                    .addCallback(MethodDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class MethodDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.methodDao())
                    }
                }
            }

            suspend fun populateDatabase(methodDao: MethodDao) {
                methodDao.deleteAll()

                var method = Method("X16", "Plain Hunt", 6)
                methodDao.insert(method)
                method = Method("x16x16x16,12", "Plain Bob", 6)
                methodDao.insert(method)

            }
        }
    }
}