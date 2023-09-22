import Foundation
import shared

@MainActor
final class PokemonListViewModel {
    let state = PokemonListScreen.State()
    let getPokemonListUseCase: GetPokemonListUseCase

    init(getPokemonListUseCase: GetPokemonListUseCase = GetPokemonListInteractor()) {
        self.getPokemonListUseCase = getPokemonListUseCase
    }

    @Sendable func onAppear() async {
        do {
            state.pokemonList = try await getPokemonListUseCase()
        } catch {
            // TODO: handle error
        }
    }
}
