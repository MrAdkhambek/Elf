package me.adkhambek.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import me.adkhambek.elf.container.MicroContainer
import me.adkhambek.elf.container.MicroContainerFactory
import me.adkhambek.elf.rememberMicroContainer


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    itemsIndexed((1..100).toList()) { index, _ ->
                        val microContainer = rememberMicroContainer<HiltMicroContainer, HiltMicroContainer.Factory> { factory ->
                            factory.create(index)
                        }

                        val text by microContainer.state

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(48.dp)
                                .clickable {
                                    microContainer.onClick()
                                }
                        ) {
                            Text(
                                text = text,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}


class HiltMicroContainer @AssistedInject constructor(
    @Assisted index: Int
) : MicroContainer {
    private var kIndex = index

    private val _state: MutableState<String> = mutableStateOf(index.toString())
    val state: State<String> get() = _state

    fun onClick() {
        kIndex++
        _state.value = kIndex.toString()
    }

    @AssistedFactory
    interface Factory : MicroContainerFactory {
        fun create(index: Int): HiltMicroContainer
    }
}
