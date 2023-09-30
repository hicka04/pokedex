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
                HStack(alignment: .top) {
                    Text("No.\(pokemon.id)")

                    VStack(alignment: .leading) {
                        Text(pokemon.name)
                        HStack {
                            Text(pokemon.types.first.name)

                            if let second = pokemon.types.second {
                                Text(second.name)
                            }
                        }
                    }
                }
            }.task(onAppear)
        }
    }
}

#Preview("\(PokemonListScreen.self)") {
    PokemonListScreen.ContentView(
        pokemonList: [
            .init(
                id: 1,
                name: "Bulbasaur",
                types: .init(
                    first: .grass,
                    second: .poison
                )
            ),
            .init(
                id: 2,
                name: "Ivysaur",
                types: .init(
                    first: .grass,
                    second: .poison
                )
            ),
            .init(
                id: 3,
                name: "Venusaur",
                types: .init(
                    first: .grass,
                    second: .poison
                )
            ),
        ]
    )
}
