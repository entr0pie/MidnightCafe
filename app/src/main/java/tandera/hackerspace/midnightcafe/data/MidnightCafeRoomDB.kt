package tandera.hackerspace.midnightcafe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tandera.hackerspace.midnightcafe.data.recipe.feed.RecipeFeedDao
import tandera.hackerspace.midnightcafe.data.recipe.feed.RecipeFeedEntity

@Database(entities = [RecipeFeedEntity::class], version = 2)
abstract class MidnightCafeRoomDB : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeFeedDao
}

fun getMidnightCafeDB(ctx: Context): MidnightCafeRoomDB {
    return Room.databaseBuilder(ctx, MidnightCafeRoomDB::class.java, name = "midnight_cafe.db")
        .fallbackToDestructiveMigration()
        .build()
}