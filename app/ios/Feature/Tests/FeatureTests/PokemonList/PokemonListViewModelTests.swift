import Foundation
import Testing
@testable import PokemonList
import shared

@MainActor
struct PokemonListViewModelTests {
    let viewModel: PokemonListViewModel
    let getPokemonListUseCase: GetPokemonListUseCaseMock

    init() {
        getPokemonListUseCase = .init()
        viewModel = .init(getPokemonListUseCase: getPokemonListUseCase)
    }

    @Test
    func onAppear_failure() async {
        getPokemonListUseCase.callAsFunctionResult = .failure(NSError(domain: "error", code: -1))

        await viewModel.onAppear()

        #expect(viewModel.error != nil)
        #expect(viewModel.pokemonList.isEmpty)
    }

    @Test
    func onAppear_success() async {
        let bulbassaur = Pokemon(
            id: 1,
            name: "Bulbassaur",
            types: .init(first: .grass, second: .poison),
            sprites: .init(officialArtwork: "hoge")
        )
        getPokemonListUseCase.callAsFunctionResult = .success([bulbassaur])

        await viewModel.onAppear()

        #expect(viewModel.error == nil)
        #expect(viewModel.pokemonList.count == 1)

        await viewModel.onAppear()
        #expect(getPokemonListUseCase.callAsFunctionCallCount == 1)
    }

    @Test(arguments: zip([1, 2], [1, 2]))
    func onAppearPokemon(id: Int32, callCount: Int) async {
        getPokemonListUseCase.callAsFunctionResult = .success([
            .init(id: 1, name: "", types: .init(first: .grass, second: .poison), sprites: .init(officialArtwork: "")),
            .init(id: 2, name: "", types: .init(first: .grass, second: .poison), sprites: .init(officialArtwork: ""))
        ])
        await viewModel.onAppear()

        await viewModel.onAppearPokemon(
            pokemon: .init(id: id, name: "", types: .init(first: .grass, second: .poison), sprites: .init(officialArtwork: ""))
        )
        #expect(getPokemonListUseCase.callAsFunctionCallCount == callCount)
    }
}

extension PokemonListViewModelTests {
    class GetPokemonListUseCaseMock: GetPokemonListUseCase {
        var callAsFunctionResult: Result<[Pokemon], Error>?
        var callAsFunctionCallCount = 0
        func __callAsFunction(offset: Int32) async throws -> [Pokemon] {
            callAsFunctionCallCount += 1
            return try callAsFunctionResult!.get()
        }
    }
}
