import SwiftUI
import shared

public struct PokemonDetailScreen: View {
    let pokemon: Pokemon

    public init(pokemon: Pokemon) {
        self.pokemon = pokemon
    }

    public var body: some View {
        Text(pokemon.name)
    }
}

#Preview("\(PokemonDetailScreen.self)") {
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
