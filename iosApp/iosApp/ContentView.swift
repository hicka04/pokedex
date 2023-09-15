import SwiftUI
import model

struct ContentView: View {
	let pokemon = Pokemon(id: 1, name: "bulbasaur")

	var body: some View {
        Text(pokemon.name)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
