import SwiftUI
import shared

@MainActor
public struct PokemonListScreen: View {
    let viewModel = PokemonListViewModel()

    public init() {}

    public var body: some View {
        ContentView(
            state: viewModel.state,
            onAppear: viewModel.onAppear
        )
    }
}

extension PokemonListScreen {
    @MainActor
    final class State: ObservableObject {
        @Published var pokemonList: [Pokemon]

        init(pokemonList: [Pokemon] = []) {
            self.pokemonList = pokemonList
        }
    }

    struct ContentView: View {
        @StateObject var state: State
        var onAppear: @Sendable () async -> Void = {}

        var body: some View {
            List(state.pokemonList) { pokemon in
                Text(pokemon.name)
            }.task(onAppear)
        }
    }
}

struct PokemonListScreen_Previews: PreviewProvider {
    static var previews: some View {
        PokemonListScreen.ContentView(
            state: .init(
                pokemonList: [
                    .init(id: 1, name: "Bulbasaur"),
                    .init(id: 2, name: "Ivysaur"),
                    .init(id: 3, name: "Venusaur"),
                ]
            )
        )
    }
}
