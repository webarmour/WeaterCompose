package ru.webarmour.weatercompose.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.webarmour.weatercompose.data.local.model.CityDbModel


@Database(entities = [CityDbModel::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun favouriteCitiesDao():FavouriteCitiesDao

    companion object {

        private var INSTANCE: FavouriteDatabase? = null

        private val LOCK = Any()

        private const val DB_NAME = "FavouriteDatabase"

        fun getInstance(context: Context): FavouriteDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val database = Room.databaseBuilder(
                    context,
                    klass = FavouriteDatabase::class.java,
                    name = DB_NAME
                ).build()
                INSTANCE = database
                return database
            }

        }
    }

}