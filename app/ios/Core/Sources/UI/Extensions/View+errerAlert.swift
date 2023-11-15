import SwiftUI

public extension View {
    func errorAlert(
        error: Binding<NSError?>,
        onTapOk: @escaping () -> Void = {}
    ) -> some View {
        modifier(ErrorAlertModifier(error: error, onTapOk: onTapOk))
    }

    func errorAlert(
        error: Binding<Error?>,
        onTapOk: @escaping () -> Void = {}
    ) -> some View {
        modifier(ErrorAlertModifier(error: error, onTapOk: onTapOk))
    }
}

struct ErrorAlertModifier: ViewModifier {
    @Binding var error: NSError?
    var onTapOk: () -> Void = {}

    init(error: Binding<NSError?>, onTapOk: @escaping () -> Void) {
        _error = error
        self.onTapOk = onTapOk
    }

    init(error: Binding<Error?>, onTapOk: @escaping () -> Void) {
        _error = .init(get: { error.wrappedValue as? NSError }, set: { error.wrappedValue = $0 })
        self.onTapOk = onTapOk
    }

    private var title: String {
        error?.localizedDescription as? String ?? message
    }
    private var message: String {
        [error?.localizedFailureReason, error?.localizedRecoverySuggestion]
            .compactMap { $0 }
            .joined(separator: " ")
    }

    func body(content: Content) -> some View {
        content.alert(
            title,
            isPresented: .init(
                get: { error != nil },
                set: { isPresented in
                    if !isPresented {
                        error = nil
                    }
                }
            ),
            actions: {
                Button("OK", action: onTapOk)
            },
            message: {
                if title != message {
                    Text(message)
                }
            }
        )
    }
}
