import SwiftUI
import domain

public struct PokemonListScreen: View {
    @State var pokemonList: [Pokemon] = []
    let getPokemonListUseCase = GetPokemonListUseCase()

    public init() {}

    public var body: some View {
        List(pokemonList) { pokemon in
            Text(pokemon.name)
        }.task {
            do {
                pokemonList = try await getPokemonListUseCase()
            } catch {}
        }
    }
}

struct SwiftUIView_Previews: PreviewProvider {
    static var previews: some View {
        PokemonListScreen()
    }
}
