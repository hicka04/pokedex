// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Core",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "DesignSystem",
            targets: ["DesignSystem"]
        ),
        .library(
            name: "UI",
            targets: ["UI"]
        ),
        .library(
            name: "shared",
            targets: ["shared"]
        ),
    ],
    dependencies: [
        .package(url: "https://github.com/SwiftGen/SwiftGenPlugin", from: "6.6.0")
    ],
    targets: [
        .target(
            name: "DesignSystem",
            plugins: [
                .plugin(name: "SwiftGenPlugin", package: "SwiftGenPlugin")
            ]
        ),
        .target(
            name: "UI",
            dependencies: [
                "DesignSystem",
                "shared"
            ]
        ),
        .testTarget(
            name: "CoreTests"
        ),
        .binaryTarget(
            name: "shared",
            path: "../../../shared/build/XCFrameworks/release/shared.xcframework"
        ),
    ]
)
