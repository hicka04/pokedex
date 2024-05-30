import SwiftUI
import shared
import UI

public struct PokemonDetailScreen: View {
    let pokemon: Pokemon

    public init(pokemon: Pokemon) {
        self.pokemon = pokemon
    }

    public var body: some View {
        ScrollView {
            LazyVStack(spacing: 8) {
                OfficialArtworkImage(urlString: pokemon.sprites.officialArtwork)

                HStack(spacing: 16) {
                    TypeTag(type: pokemon.types.first)

                    if let secondType = pokemon.types.second {
                        TypeTag(type: secondType)
                    }
                }
            }.padding(.horizontal, 16)
        }.navigationTitle(Text(pokemon.name))
    }
}

#Preview("\(PokemonDetailScreen.self)") {
    NavigationStack {
        PokemonDetailScreen(
            pokemon: .init(
                id: 1,
                name: "Bulbasaur",
                types: .init(
                    first: .grass,
                    second: .poison
                ),
                sprites: .init(
                    officialArtwork: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        )
    }
}
