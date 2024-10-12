package ui.tools

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object ColorsPaletteProvider {
    val light = CustomColorsPalette(
        background = Color(0xFFe9f8fc),
        backgroundSecondary = Color(0xFFbcebf6),
        utility100 = Color(0xFF8fdef0),
        utility200 = Color(0xFF35c4e3),
        utility300 = Color(0xFF51caaca),
        utility400 = Color(0xFF0f5f70),
        utility500 = Color(0xFF08313A),
        surface100 = Color(0xFFd5f2f9),
        surface200 = Color(0xFFb9e9f5),
        surface300 = Color(0xFF9de0f2),
    )

    val dark = CustomColorsPalette(
        background = Color(0xFF093943),
        backgroundSecondary = Color(0xFF0f5f70),
        utility100 = Color(0xFF80daee),
        utility200 = Color(0xFF9de2f2),
        utility300 = Color(0xFFb9eaf6),
        utility400 = Color(0xFFd5f3f9),
        utility500 = Color(0xFFf1fbfd),
        surface100 = Color(0xFF0d5462),
        surface200 = Color(0xFF116c7e),
        surface300 = Color(0xFF15839b),
    )
}

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color(0xFF107869),
    val secondary: Color = Color(0xFF1A5653),
    val primaryAlpha: Color = Color(0x291A5653),
    val tint: Color = Color(0xFF5CD85A),
    val background: Color = Color.Unspecified,
    val backgroundSecondary: Color = Color.Unspecified,
    val utility100: Color = Color.Unspecified,
    val utility200: Color = Color.Unspecified,
    val utility300: Color = Color.Unspecified,
    val utility400: Color = Color.Unspecified,
    val utility500: Color = Color.Unspecified,
    val surface100: Color = Color.Unspecified,
    val surface200: Color = Color.Unspecified,
    val surface300: Color = Color.Unspecified,
    val success: Color = Color(0xFF15AB7B),
    val danger: Color = Color(0xFFDF2E29),
    val warning: Color = Color(0xFFE99037),
    val info: Color = Color(0xFF088FD3),
    val white: Color = Color(0xFFFFFFFF),
    val black: Color = Color(0xFF000000),
)

val AppColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }