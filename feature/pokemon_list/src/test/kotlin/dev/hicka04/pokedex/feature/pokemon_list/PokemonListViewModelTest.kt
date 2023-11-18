package dev.hicka04.pokedex.feature.pokemon_list

import app.cash.turbine.test
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModelTest {
    private lateinit var viewModel: PokemonListViewModel

    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()

    @BeforeTest
    fun setUp() {
        viewModel = PokemonListViewModel(
            getPokemonListUseCase = getPokemonListUseCase
        )

        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun onAppear_failure() = runTest {
        coEvery { getPokemonListUseCase.invoke(0) } throws Exception()

        viewModel.uiState.test {
            skipItems(1)

            viewModel.onAppear()

            assertTrue(awaitItem().isLoading)
            assertNotNull(awaitItem().exception)
        }
    }

    @Test
    fun onAppear_success() = runTest {
        coEvery { getPokemonListUseCase.invoke(0) } returns listOf(
            mockk()
        )

        viewModel.uiState.test {
            skipItems(1)

            viewModel.onAppear()

            assertTrue(awaitItem().isLoading)
            assertEquals(awaitItem().pokemonList.count(), 1)
        }
    }

    @Test
    fun onAppear_isNotEmpty() = runTest {
        coEvery { getPokemonListUseCase.invoke(0) } returns listOf(
            mockk()
        )

        viewModel.onAppear()
        viewModel.onAppear()
        coVerify(exactly = 1) { getPokemonListUseCase.invoke(0) }
    }

    @Test
    fun onAppearPokemon() = runTest {
        val pokemon1: Pokemon = mockk { every { id } returns 1 }
        val pokemon2: Pokemon = mockk { every { id } returns 2 }
        coEvery { getPokemonListUseCase.invoke(0) } returns listOf(
            pokemon1,
            pokemon2
        )

        viewModel.onAppear()
        coVerify(exactly = 1) { getPokemonListUseCase.invoke(0) }

        viewModel.onAppearPokemon(pokemon1)
        coVerify(exactly = 0) { getPokemonListUseCase.invoke(2) }

        viewModel.onAppearPokemon(pokemon2)
        coVerify(exactly = 1) { getPokemonListUseCase.invoke(2) }
    }

    @Test
    fun onDismissErrorAlertDialog() = runTest {
        coEvery { getPokemonListUseCase.invoke(0) } throws Exception()

        viewModel.uiState.test {
            viewModel.onAppear()
            assertNotNull(expectMostRecentItem().exception)

            viewModel.onDismissErrorAlertDialog()
            assertNull(awaitItem().exception)
        }
    }
}