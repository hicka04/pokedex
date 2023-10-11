import SwiftUI

public struct OfficialArtworkImage: View {
    let url: URL

    public init(url: URL) {
        self.url = url
    }

    public init(urlString: String) {
        self.init(url: .init(string: urlString)!)
    }

    public var body: some View {
        GeometryReader { proxy in
            AsyncImage(
                url: url,
                content: { $0.resizable() },
                placeholder: { ProgressView() }
            ).frame(
                width: min(proxy.size.width, proxy.size.height),
                height: min(proxy.size.width, proxy.size.height)
            )
        }.scaledToFit()
    }
}

#Preview("\(OfficialArtworkImage.self)") {
    OfficialArtworkImage(
        urlString: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    )
}
