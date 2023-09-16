import SwiftUI
import model
import domain

struct ContentView: View {
    @State var pokemonList: [ModelPokemon] = []
    let getPokemonListUseCase = GetPokemonListUseCase()

	var body: some View {
        List(pokemonList) { pokemon in
            Text(pokemon.name)
        }
        .task {
            do {
                pokemonList = try await getPokemonListUseCase()
            } catch {}
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
