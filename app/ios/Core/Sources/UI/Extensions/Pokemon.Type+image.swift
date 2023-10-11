import DesignSystem
import shared
import SwiftUI

public extension Pokemon.Type_ {
    var icon: Image {
        switch self {
        case .normal: DesignSystem.Icons.Types.normal.swiftUIImage
        case .fire: DesignSystem.Icons.Types.fire.swiftUIImage
        case .water: DesignSystem.Icons.Types.water.swiftUIImage
        case .electric: DesignSystem.Icons.Types.electric.swiftUIImage
        case .grass: DesignSystem.Icons.Types.grass.swiftUIImage
        case .ice: DesignSystem.Icons.Types.ice.swiftUIImage
        case .fighting: DesignSystem.Icons.Types.fighting.swiftUIImage
        case .poison: DesignSystem.Icons.Types.poison.swiftUIImage
        case .ground: DesignSystem.Icons.Types.ground.swiftUIImage
        case .flying: DesignSystem.Icons.Types.flying.swiftUIImage
        case .psychic: DesignSystem.Icons.Types.psychic.swiftUIImage
        case .bug: DesignSystem.Icons.Types.bug.swiftUIImage
        case .rock: DesignSystem.Icons.Types.rock.swiftUIImage
        case .ghost: DesignSystem.Icons.Types.ghost.swiftUIImage
        case .dragon: DesignSystem.Icons.Types.dragon.swiftUIImage
        case .dark: DesignSystem.Icons.Types.dark.swiftUIImage
        case .steel: DesignSystem.Icons.Types.steel.swiftUIImage
        case .fairy: DesignSystem.Icons.Types.fairy.swiftUIImage
        }
    }
}
