// swift-tools-version: 5.8
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Feature",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "PokemonList",
            targets: ["PokemonList"]
        ),
    ],
    targets: [
        .target(
            name: "PokemonList"
        ),
        .testTarget(
            name: "FeatureTests",
            dependencies: ["PokemonList"]
        ),
        .binaryTarget(
            name: "domain",
            path: "../../../core/domain/build/XCFrameworks/release/domain.xcframework"
        ),
    ]
)
