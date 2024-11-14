package tandera.hackerspace.midnightcafe.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert

@Entity(tableName = "RECIPES")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val score: Int,
    val ingredients: String,
    val instructions: String,
)

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RECIPES")
    suspend fun list(): List<RecipeEntity>

    @Query("SELECT * FROM RECIPES WHERE id = :id")
    suspend fun findById(id: Int): RecipeEntity?

    @Upsert
    suspend fun save(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)
}

