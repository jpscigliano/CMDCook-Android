package co.cmd.cook.mappers

import co.cmd.cook.framework.dto.RecipeResponse
import co.cmd.core.domain.Calories
import co.cmd.core.domain.Carbohydrate
import co.cmd.core.domain.Description
import co.cmd.core.domain.Fat
import co.cmd.core.domain.ID
import co.cmd.core.domain.ImageUrl
import co.cmd.core.domain.Name
import co.cmd.core.domain.Nutrition
import co.cmd.core.domain.Protein
import co.cmd.core.domain.Recipe

class RecipeResponseToRecipeMapper : Mapper<RecipeResponse, Recipe> {

    override fun map(input: RecipeResponse): Recipe =
        Recipe(
            id = ID(input.id ?: ""),
            name = Name(input.name ?: ""),
            description = Description(input.description ?: ""),
            imageUrl = ImageUrl(input.imageUrl ?: ""),
            nutrition = Nutrition(
                calories = Calories(input.nutrition?.calories ?: ""),
                fat = Fat(input.nutrition?.fat ?: ""),
                carbohydrate = Carbohydrate(input.nutrition?.carbs ?: ""),
                protein = Protein(input.nutrition?.protein ?: ""),
            ),
        )
}