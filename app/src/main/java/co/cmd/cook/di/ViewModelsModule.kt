package co.cmd.cook.di

import co.cmd.cook.presentation.recipeDetail.RecipeDetailViewModel
import co.cmd.cook.presentation.recipeList.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { RecipeListViewModel(get()) }
    viewModel{RecipeDetailViewModel(get())}
}