import Foundation
import shared
import Observation

@MainActor @Observable
final class PokemonListViewModel {
    private(set) var pokemonList: [Pokemon] = []
    private(set) var isLoading: Bool = false
    var error: Error?

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
        guard pokemonList.isEmpty else { return }

        await loadPokemonList()
    }

    func onAppearPokemon(pokemon: Pokemon) async {
        guard pokemonList.last == pokemon else { return }

        await loadPokemonList()
    }

    private func loadPokemonList() async {
        guard !isLoading else { return }

        do {
            isLoading = true
            defer { isLoading = false }

            let offset = Int32(pokemonList.count)
            pokemonList += try await getPokemonListUseCase(offset: offset)
        } catch {
            self.error = error
        }
    }
}
