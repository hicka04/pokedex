package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDetailViewModelTest {
    private lateinit var viewModel: PokemonDetailViewModel

    private val savedStateHandle: SavedStateHandle = mockk {
        every { get<String>("name") } returns "Bulbasaur"
    }
    private val getPokemonUseCase: GetPokemonUseCase = mockk()

    @BeforeTest
    fun setUp() {
        viewModel = PokemonDetailViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonUseCase = getPokemonUseCase
        )

        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun onAppear_failure() = runTest {
        val exception = Exception()
        coEvery { getPokemonUseCase.invoke(any()) } throws exception

        viewModel.uiState.map { it.exception }.test {
            assertEquals(null, awaitItem())

            viewModel.onAppear()

            assertEquals(exception, awaitItem())
        }
    }

    @Test
    fun onAppear_success() = runTest {
        val pokemon: Pokemon = mockk()
        coEvery { getPokemonUseCase.invoke(any()) } returns pokemon

        viewModel.uiState.map { it.pokemon }.test {
            assertEquals(null, awaitItem())

            viewModel.onAppear()

            assertEquals(pokemon, awaitItem())
        }
    }
}