import DesignSystem
import shared
import SwiftUI

extension Pokemon.Type_ {
    var color: Color {
        switch self {
        case .normal: DesignSystem.Colors.Types.normal.swiftUIColor
        case .fire: DesignSystem.Colors.Types.fire.swiftUIColor
        case .water: DesignSystem.Colors.Types.water.swiftUIColor
        case .electric: DesignSystem.Colors.Types.electric.swiftUIColor
        case .grass: DesignSystem.Colors.Types.grass.swiftUIColor
        case .ice: DesignSystem.Colors.Types.ice.swiftUIColor
        case .fighting: DesignSystem.Colors.Types.fighting.swiftUIColor
        case .poison: DesignSystem.Colors.Types.poison.swiftUIColor
        case .ground: DesignSystem.Colors.Types.ground.swiftUIColor
        case .flying: DesignSystem.Colors.Types.flying.swiftUIColor
        case .psychic: DesignSystem.Colors.Types.psychic.swiftUIColor
        case .bug: DesignSystem.Colors.Types.bug.swiftUIColor
        case .rock: DesignSystem.Colors.Types.rock.swiftUIColor
        case .ghost: DesignSystem.Colors.Types.ghost.swiftUIColor
        case .dragon: DesignSystem.Colors.Types.dragon.swiftUIColor
        case .dark: DesignSystem.Colors.Types.dark.swiftUIColor
        case .steel: DesignSystem.Colors.Types.steel.swiftUIColor
        case .fairy: DesignSystem.Colors.Types.fairy.swiftUIColor
        }
    }
}
