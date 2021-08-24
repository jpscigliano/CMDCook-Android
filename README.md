# CMDCook - Under construction

Mix ingredients as a real commander.

## Description

CMDCook is a demo android app using CleanArchitecure with MVVM design. Jetpack libraries like Navigation, SageArg, Paging3 were used alongside with Coroutines, Flow, Channels, Retrofit, OkHttp and more.

## Overview

CMDCook has 2 Screens, the ListRecipeFragment where the PAging3 library is used to show list of recipes and RecipeDetailFragment showing more information about the recipe.

https://platform.fatsecret.com/api/ was used for fetching the Data. FatSecret API supports OAuth 2.0, interceptors are implemented for the setup of the API calls using the Barear Token provided by the API.

Navigation was done Using SafeArg and NavigationComponent

## Architecture 

Following Clean Architecture principles the App is split in several layers and organized in 2 modules. This provides a better abstractions of the framework impl and the business logic

![alt text](https://github.com/jpscigliano/CMDCook-Android/blob/dev/img/arch.png?raw=true)

### App Module;
    - Presentation (related to “view logic”, like  ViewModels, Activities, Fragments  group by features)
    - Framework (Implementation of Datasource, Api, DTO)

### Core Module;
    - Domain ( specific business objects)
    - Data (Data source abstractions and Repository)
    - Interactors (Use cases)

Use of Repository Pattern to handle the fetch of data data and Mappers for mapping the DTO objects to Domain objects.
Repository decides if the data should come from the Network or Locale.

## Database - Room

Not yet. At the moment DataStore was uses for storing the client Token.


## Testing
Not yet.
