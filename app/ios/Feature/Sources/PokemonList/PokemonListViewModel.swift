import Foundation
import shared

@MainActor
final class PokemonListViewModel: ObservableObject {
    @Published private(set) var pokemonList: [Pokemon] = []

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
            pokemonList = try await getPokemonListUseCase()
        } catch {
            // TODO: handle error
        }
    }
}
