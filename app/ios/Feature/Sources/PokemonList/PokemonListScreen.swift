import SwiftUI
import shared

@MainActor
public struct PokemonListScreen: View {
    let viewModel = PokemonListViewModel()

    public init() {}

    public var body: some View {
        ContentView(
            pokemonList: viewModel.pokemonList,
            onAppear: viewModel.onAppear
        )
    }
}

extension PokemonListScreen {
    struct ContentView: View {
        let pokemonList: [Pokemon]
        var onAppear: @Sendable () async -> Void = {}

        var body: some View {
            List(pokemonList) { pokemon in
                Text(pokemon.name)
            }.task(onAppear)
        }
    }
}

#Preview("\(PokemonListScreen.self)") {
    PokemonListScreen.ContentView(
        pokemonList: [
            .init(id: 1, name: "Bulbasaur"),
            .init(id: 2, name: "Ivysaur"),
            .init(id: 3, name: "Venusaur"),
        ]
    )
}
