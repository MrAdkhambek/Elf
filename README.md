# Elf

Sample helper library for using hilt with compose.
You can use `ComposableComponent` easily

```kotlin
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
```

```kotlin

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
```