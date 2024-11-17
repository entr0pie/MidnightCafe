package tandera.hackerspace.midnightcafe.data.recipe.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeFeedEntity::class], version = 2)
abstract class MidnightCafeDB : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeFeedDao
}

fun getMidnightCafeDB(ctx: Context): MidnightCafeDB {
    return Room.databaseBuilder(ctx, MidnightCafeDB::class.java, name = "midnight_cafe.db")
        .build()
}