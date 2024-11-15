package tandera.hackerspace.midnightcafe.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.data.Recipe
import tandera.hackerspace.midnightcafe.ui.components.common.Score


private val RECIPE_IMAGES =
    listOf(R.drawable.tiramisu, R.drawable.brigadeiro, R.drawable.red_velvet)

@Entity(tableName = "RECIPE_FEED")
data class RecipeFeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val score: Int,
    val ingredients: String,
    val instructions: String,
) {
    fun toRecipe() = Recipe(
        title = this.title,
        score = Score.of(this.score)!!,
        imageDescription = "A sample recipe",
        image = RECIPE_IMAGES.random(),
        ingredients = this.ingredients,
        instructions = this.instructions,
    )

    companion object {
        fun fromRecipe(recipe: Recipe) = RecipeFeedEntity(
            null,
            recipe.title,
            recipe.score.value(),
            recipe.ingredients,
            recipe.instructions
        )
    }
}

@Dao
interface RecipeFeedDao {
    @Query("SELECT * FROM RECIPE_FEED")
    fun list(): Flow<List<RecipeFeedEntity>>

    @Upsert
    fun save(recipe: RecipeFeedEntity)

    @Delete
    fun delete(recipe: RecipeFeedEntity)

    @Query("DELETE FROM RECIPE_FEED")
    fun wipe()
}

