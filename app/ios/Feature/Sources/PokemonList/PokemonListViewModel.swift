import Foundation
import shared
import Observation

@MainActor @Observable
final class PokemonListViewModel {
    private(set) var pokemonList: [Pokemon] = []

    private let getPokemonListUseCase: GetPokemonListUseCase

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
            pokemonList = try await getPokemonListUseCase(offset: 0)
        } catch {
            // TODO: handle error
        }
    }
}
