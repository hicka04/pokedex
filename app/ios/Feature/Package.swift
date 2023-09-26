// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Feature",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "PokemonList",
            targets: ["PokemonList"]
        ),
    ],
    targets: [
        .target(
            name: "PokemonList",
            dependencies: [
                "shared"
            ]
        ),
        .testTarget(
            name: "FeatureTests",
            dependencies: ["PokemonList"]
        ),
        .binaryTarget(
            name: "shared",
            path: "../../../shared/build/XCFrameworks/release/shared.xcframework"
        ),
    ]
)
