import Foundation
import shared
import Observation

@MainActor @Observable
final class PokemonListViewModel {
    private(set) var pokemonList: [Pokemon] = []
    private(set) var isLoading: Bool = false

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

        do {
            isLoading = true
            defer { isLoading = false }

            pokemonList = try await getPokemonListUseCase(offset: 0)
        } catch {
            // TODO: handle error
        }
    }

    func onAppearPokemon(pokemon: Pokemon) async {
        guard pokemonList.last == pokemon else { return }

        do {
            isLoading = true
            defer { isLoading = false }

            pokemonList += try await getPokemonListUseCase(offset: Int32(pokemonList.count))
        } catch {
            // TODO: handle error
        }
    }
}
