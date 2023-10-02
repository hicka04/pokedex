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
    dependencies: [
        .package(path: "../Core")
    ],
    targets: [
        .target(
            name: "PokemonList",
            dependencies: [
                .product(name: "shared", package: "Core"),
                .product(name: "DesignSystem", package: "Core"),
                .product(name: "UI", package: "Core")
            ]
        ),
        .testTarget(
            name: "FeatureTests",
            dependencies: ["PokemonList"]
        ),
    ]
)
