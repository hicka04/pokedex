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
        .library(
            name: "PokemonDetail",
            targets: ["PokemonDetail"]
        ),
    ],
    dependencies: [
        .package(path: "../Core"),
        .package(url: "https://github.com/apple/swift-testing.git", from: "0.1.0")
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
        .target(
            name: "PokemonDetail",
            dependencies: [
                .product(name: "shared", package: "Core"),
                .product(name: "DesignSystem", package: "Core"),
                .product(name: "UI", package: "Core")
            ]
        ),
        .testTarget(
            name: "FeatureTests",
            dependencies: [
                "PokemonList",
                "PokemonDetail",
                .product(name: "Testing", package: "swift-testing")
            ]
        ),
    ]
)
