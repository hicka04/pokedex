import SwiftUI
import shared
import UI

@MainActor
public struct PokemonListScreen: View {
    @State var viewModel = PokemonListViewModel()

    public init() {}

    public var body: some View {
        ContentView(
            pokemonList: viewModel.pokemonList,
            isLoading: viewModel.isLoading,
            error: $viewModel.error,
            onAppear: viewModel.onAppear,
            onAppearPokemon: viewModel.onAppearPokemon(pokemon:)
        )
    }
}

extension PokemonListScreen {
    struct ContentView: View {
        let pokemonList: [Pokemon]
        let isLoading: Bool
        @Binding var error: Error?
        var onAppear: @Sendable () async -> Void = {}
        var onAppearPokemon: (Pokemon) async -> Void = { _ in }

        private let gridItem = GridItem(
            .adaptive(minimum: 160),
            spacing: 16
        )

        var body: some View {
            ScrollView {
                LazyVGrid(columns: [gridItem], spacing: 16) {
                    ForEach(pokemonList) { pokemon in
                        NavigationLink(value: pokemon) {
                            PokemonCell(pokemon: pokemon)
                                .task {
                                    await onAppearPokemon(pokemon)
                                }
                        }
                    }
                }.padding(16)

                if isLoading {
                    ProgressView()
                        .controlSize(.large)
                }
            }
            .errorAlert(error: $error)
            .navigationTitle("Pokedex")
            .task(onAppear)
        }
    }
}

#Preview("\(PokemonListScreen.self)") {
    NavigationStack {
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
            ], 
            isLoading: true,
            error: .constant(nil)
        )
    }
}
