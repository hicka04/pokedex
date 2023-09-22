package dev.hicka04.pokedex.feature.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hicka04.pokedex.core.domain.GetPokemonListInteractor
import dev.hicka04.pokedex.core.domain.GetPokemonListUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PokemonListUiState(
    val pokemonList: List<Pokemon> = emptyList()
)

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase = GetPokemonListInteractor()
): ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(pokemonList = getPokemonListUseCase())
            }
        }
    }
}