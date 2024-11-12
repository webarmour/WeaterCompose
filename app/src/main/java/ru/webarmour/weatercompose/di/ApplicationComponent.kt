package ru.webarmour.weatercompose.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.webarmour.weatercompose.presentation.MainActivity


@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        PresentationModule::class
    ]
)
interface ApplicationComponent {


    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}