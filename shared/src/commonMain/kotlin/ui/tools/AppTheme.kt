package ui.tools

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFF4F56FF),
            secondary = Color(0xFF444ACB),
            tertiary = Color(0xFF161737),
            background = AppColorsPalette.current.background
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF3239D7),
            secondary = Color(0xFF252BA9),
            tertiary = Color(0xFFDADBF5),
            background = AppColorsPalette.current.background
        )
    }

    val typography = Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontSize = 16.sp
        ),
        bodySmall = TextStyle(
            fontSize = 14.sp
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    val customColorsPalette =
        if (darkTheme) ColorsPaletteProvider.dark
        else ColorsPaletteProvider.light


    CompositionLocalProvider(
        AppColorsPalette provides customColorsPalette,
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
