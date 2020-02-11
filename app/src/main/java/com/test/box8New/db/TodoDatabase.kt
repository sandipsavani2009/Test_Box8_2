package com.test.box8New.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


/**
 * Room Databse
 */
@Database(entities = [ToDoEntity::class, TaskEntity::class], version = 1, exportSchema = false)
public abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDataAccessObject(): TodoDao

    companion object {
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TodoDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        DbConstants.DATABASE_NAME
                    )/*.addCallback(DatabaseCallback(scope))*/.build()
                }
            }
            return INSTANCE!!
        }

        /*private class DatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            *//**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             *//*
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }*/

    }


}