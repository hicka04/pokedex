import SwiftUI
import model
import domain

struct ContentView: View {
    @State var pokemonList: [ModelPokemon] = []

	var body: some View {
        List(pokemonList) { pokemon in
            Text(pokemon.name)
        }
        .task {
            do {
                pokemonList = try await GetPokemonListUseCase().invoke()
            } catch {}
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
