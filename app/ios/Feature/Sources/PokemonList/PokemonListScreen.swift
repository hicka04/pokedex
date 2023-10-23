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

        private let gridItem = GridItem(
            .adaptive(minimum: 160),
            spacing: 16
        )

        var body: some View {
            ScrollView {
                LazyVGrid(columns: [gridItem], spacing: 16) {
                    ForEach(pokemonList) { pokemon in
                        NavigationLink(value: pokemon) {
                            VStack(spacing: 0) {
                                OfficialArtworkImage(urlString: pokemon.sprites.officialArtwork)

                                HStack(alignment: .top) {
                                    VStack(alignment: .leading) {
                                        Text("No.\(pokemon.id)")
                                            .font(.caption)
                                        Text(pokemon.name)
                                            .font(.title3)
                                    }
                                    .frame(
                                        maxWidth: .infinity,
                                        alignment: .leading
                                    )

                                    VStack(spacing: 0) {
                                        pokemon.types.first.icon
                                        pokemon.types.second?.icon
                                    }
                                }.padding(.horizontal, 8)
                            }
                        }
                    }
                }.padding(.horizontal, 16)
            }
            .navigationTitle("Pokedex")
            .task(onAppear)
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
