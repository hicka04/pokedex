import SwiftUI
import shared
import UI

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
                VStack {
                    OfficialArtworkImage(urlString: pokemon.sprites.officialArtwork)

                    VStack(alignment: .leading) {
                        Text("No.\(pokemon.id)")
                        Text(pokemon.name)
                    }

                    HStack {
                        pokemon.types.first.icon
                        Text(pokemon.types.first.name)

                        if let second = pokemon.types.second {
                            second.icon
                            Text(second.name)
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
                ),
                sprites: .init(
                    officialArtwork: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            ),
            .init(
                id: 2,
                name: "Ivysaur",
                types: .init(
                    first: .grass,
                    second: .poison
                ),
                sprites: .init(
                    officialArtwork: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                )
            ),
            .init(
                id: 3,
                name: "Venusaur",
                types: .init(
                    first: .grass,
                    second: .poison
                ),
                sprites: .init(
                    officialArtwork: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                )
            ),
        ]
    )
}
