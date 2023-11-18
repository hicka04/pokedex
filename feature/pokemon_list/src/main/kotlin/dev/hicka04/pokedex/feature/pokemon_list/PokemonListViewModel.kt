package dev.hicka04.pokedex.feature.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

data class PokemonListUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val exception: Exception? = null
)

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState = _uiState.asStateFlow()

    fun onAppear() {
        if (uiState.value.pokemonList.isNotEmpty()) return

        loadPokemonList()
    }

    fun onAppearPokemon(pokemon: Pokemon) {
        if (uiState.value.pokemonList.last() != pokemon) return

        loadPokemonList()
    }

    fun onDismissErrorAlertDialog() {
        _uiState.update { it.copy(exception = null) }
    }

    private fun loadPokemonList() {
        if (uiState.value.isLoading) return

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val offset = uiState.value.pokemonList.size
            try {
                val list = getPokemonListUseCase(offset = offset)
                _uiState.update {
                    it.copy(pokemonList = it.pokemonList + list)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(exception = e) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}