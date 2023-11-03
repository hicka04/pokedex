import SwiftUI
import shared

public struct PokemonCell: View {
    let pokemon: Pokemon

    public init(pokemon: Pokemon) {
        self.pokemon = pokemon
    }

    public var body: some View {
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

#Preview("\(PokemonCell.self)") {
    PokemonCell(
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
