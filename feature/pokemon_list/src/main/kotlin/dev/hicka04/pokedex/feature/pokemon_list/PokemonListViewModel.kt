package dev.hicka04.pokedex.feature.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PokemonListUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false
)

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState = _uiState.asStateFlow()

    fun onAppear() {
        if (uiState.value.pokemonList.isNotEmpty()) return

        loadPokemonList()
    }

    fun onAppearPokemon(pokemon: Pokemon)  {
        if (uiState.value.pokemonList.last() != pokemon) return

        loadPokemonList()
    }

    private fun loadPokemonList() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        val offset = uiState.value.pokemonList.size
        val list = getPokemonListUseCase(offset = offset)
        _uiState.update {
            it.copy(
                pokemonList = it.pokemonList + list,
                isLoading = false
            )
        }
    }
}