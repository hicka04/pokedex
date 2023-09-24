import SwiftUI
import shared
import PokemonList

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.doInitKoin()
    }

    var body: some Scene {
		WindowGroup {
			PokemonListScreen()
		}
	}
}
