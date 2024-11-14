package tandera.hackerspace.midnightcafe.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)
abstract class MidnightCafeDB : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}

fun getMidnightCafeDB(ctx: Context): MidnightCafeDB {
    return Room.databaseBuilder(ctx, MidnightCafeDB::class.java, name = "midnight_cafe.db").build()
}