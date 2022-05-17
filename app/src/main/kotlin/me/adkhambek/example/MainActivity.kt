package me.adkhambek.example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.multibindings.IntoSet
import me.adkhambek.elf.hilt.ComposableComponent
import me.adkhambek.elf.rememberComposableEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val myEntryPoint = rememberComposableEntryPoint<MyEntryPoint>()

            Scaffold(
                content = {

                },
                bottomBar = {
                    BottomAppBar {
                        myEntryPoint.features().forEach { feature ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = feature.iconRes),
                                        contentDescription = stringResource(id = feature.title)
                                    )
                                },
                                label = {
                                    Text(text = stringResource(id = feature.title))
                                },
                                selected = false,
                                onClick = {

                                },
                                alwaysShowLabel = false
                            )
                        }
                    }
                }
            )
        }
    }
}

interface Feature {

    @get:DrawableRes
    val iconRes: Int

    @get:StringRes
    val title: Int
}

@EntryPoint
@InstallIn(ComposableComponent::class)
interface MyEntryPoint {

    fun features(): Set<Feature>
}

@Module
@InstallIn(ComposableComponent::class)
object FooModule {

    @[Provides IntoSet]
    fun provideFeatureA(): Feature {
        return object : Feature {
            override val iconRes: Int = R.drawable.ic_launcher_foreground
            override val title: Int = R.string.app_name
        }
    }

    @[Provides IntoSet]
    fun provideFeatureB(): Feature {
        return object : Feature {
            override val iconRes: Int = R.drawable.ic_launcher_foreground
            override val title: Int = R.string.app_name
        }
    }
}