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
        HStack(spacing: 8) {
            type.icon
                .resizable()
                .scaledToFit()
            Spacer()
            Text(type.name)
                .font(.caption)
                .fontWeight(.medium)
                .foregroundColor(type.color)
            Spacer()
        }
        .frame(height: height)
        .padding(.leading, 8)
        .padding(.trailing, cornerRadius)
        .overlay {
            RoundedRectangle(cornerRadius: cornerRadius)
                .stroke(type.color, lineWidth: 2)
        }
    }
}

#Preview("\(TypeTag.self)") {
    ForEach(Pokemon.Type_.allCases, id: \.name) { type in
        TypeTag(type: type)
    }
}
