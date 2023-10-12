import SwiftUI
import shared

public struct TypeTag: View {
    let type: Pokemon.Type_

    private let cornerRadius: CGFloat = 16
    private var height: CGFloat { cornerRadius * 2 }

    public init(type: Pokemon.Type_) {
        self.type = type
    }

    public var body: some View {
        HStack {
            type.icon
                .resizable()
                .scaledToFit()
            Text(type.name)
                .font(.caption)
                .fontWeight(.medium)
                .foregroundColor(type.color)
        }
        .frame(height: height)
        .padding(.trailing, cornerRadius)
        .overlay {
            RoundedRectangle(cornerRadius: cornerRadius)
                .strokeBorder(type.color, lineWidth: 2)
        }
    }
}

#Preview("\(TypeTag.self)") {
    ForEach(Pokemon.Type_.allCases, id: \.rawValue) { type in
        TypeTag(type: type)
    }
}
