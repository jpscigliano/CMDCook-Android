package co.cmd.core.domain

class RecipeDetail(
    id: ID,
    name: Name,
    description: Description,
    imageUrl: ImageUrl,
    nutrition: Nutrition,
    val cookingTime: CookingTime,
    val preparationTime: PreparationTime,
    val rating: Rating,
    val serving: Serving

) : Recipe(id, name, description, imageUrl, nutrition) {

}