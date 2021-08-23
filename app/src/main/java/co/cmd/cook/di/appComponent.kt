package co.cmd.cook.di

// This property stores a list of modules that need to be injected bt koin
val appComponent = listOf(
    apiModule,
    authRepositoryModule,
    settingsModule,
    cookModule,
    viewModelsModule
)


