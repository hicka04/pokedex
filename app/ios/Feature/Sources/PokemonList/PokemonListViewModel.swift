import Foundation
import shared

@MainActor
final class PokemonListViewModel {
    let state = PokemonListScreen.State()
    let getPokemonListUseCase: GetPokemonListUseCase

    convenience init() {
        self.init(
            getPokemonListUseCase: SharedComponent.shared.getPokemonListUseCase()
        )
    }

    init(getPokemonListUseCase: GetPokemonListUseCase) {
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
