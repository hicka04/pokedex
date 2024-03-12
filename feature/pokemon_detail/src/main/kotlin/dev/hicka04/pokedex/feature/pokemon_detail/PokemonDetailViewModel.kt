package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.lang.Exception

data class PokemonDetailUiState(
    val pokemon: Pokemon? = null
)

@KoinViewModel
class PokemonDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun onAppear()  {
        val name: String = savedStateHandle["name"] ?: return
        viewModelScope.launch {
            try {
                val pokemon = getPokemonUseCase(name = name)
                _uiState.update { it.copy(pokemon = pokemon) }
            } catch (e: Exception) {
                // TODO: handle error
            }
        }
    }
}