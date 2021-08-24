package co.cmd.cook.mappers

import co.cmd.cook.framework.dto.RecipeDetailResponse
import co.cmd.core.domain.Calories
import co.cmd.core.domain.Carbohydrate
import co.cmd.core.domain.Cholesterol
import co.cmd.core.domain.CookingTime
import co.cmd.core.domain.Description
import co.cmd.core.domain.Fat
import co.cmd.core.domain.ID
import co.cmd.core.domain.ImageUrl
import co.cmd.core.domain.Iron
import co.cmd.core.domain.Name
import co.cmd.core.domain.Nutrition
import co.cmd.core.domain.PreparationTime
import co.cmd.core.domain.Protein
import co.cmd.core.domain.Rating
import co.cmd.core.domain.RecipeDetail
import co.cmd.core.domain.Serving
import co.cmd.core.domain.Sodium
import co.cmd.core.domain.Sugar

class RecipeDetailResponseToRecipeDetailMapper : Mapper<RecipeDetailResponse, RecipeDetail> {

    override fun map(input: RecipeDetailResponse): RecipeDetail =
        RecipeDetail(
            id = ID(input.id ?: ""),
            name = Name(input.name ?: ""),
            description = Description(input.description ?: ""),
            imageUrl = ImageUrl(input.recipeImage?.imageUrl ?: ""),
            nutrition = Nutrition(
                calories = Calories(input.nutrition?.calories ?: ""),
                fat = Fat(input.nutrition?.fat ?: ""),
                carbohydrate = Carbohydrate(input.nutrition?.carbs ?: ""),
                protein = Protein(input.nutrition?.protein ?: ""),
            ),
            preparationTime = PreparationTime(input.prepTimeMin?.toFloatOrNull() ?: 0f),
            cookingTime = CookingTime(input.cookTimeMin?.toFloatOrNull() ?: 0f),
            rating = Rating(input.rating?.toFloatOrNull() ?: 0f),
            serving = Serving(
                fat = Fat(input.servings?.fat ?: ""),
                calories = Calories(input.servings?.calories ?: ""),
                iron = Iron(input.servings?.iron ?: ""),
                sodium = Sodium(input.servings?.sodium ?: ""),
                cholesterol = Cholesterol(input.servings?.cholesterol ?: ""),
                sugar = Sugar(input.servings?.sugar ?: "")
            )
        )
}