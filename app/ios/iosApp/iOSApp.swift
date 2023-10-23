import SwiftUI
import shared
import PokemonList
import PokemonDetail

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.doInitKoin()
    }

    var body: some Scene {
		WindowGroup {
            NavigationStack {
                PokemonListScreen()
                    .navigationDestination(for: Pokemon.self) { pokemon in
                        PokemonDetailScreen(pokemon: pokemon)
                    }
            }
		}
	}
}
