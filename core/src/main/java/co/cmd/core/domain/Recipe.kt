package co.cmd.core.domain

data class Recipe(
    val id: ID,
    val name: Name,
    val description: Description,
    val imageUrl: ImageUrl,
    val nutrition: Nutrition,
) {

}



