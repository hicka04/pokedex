package dev.hicka04.pokedex.core.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.ui.extension.color
import dev.hicka04.pokedex.core.ui.extension.painter

@Composable
fun TypeTag(
    modifier: Modifier = Modifier,
    type: Pokemon.Type
) {
    val cornerRadius = 16.dp
    val height = cornerRadius * 2

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(height)
            .border(
                width = 2.dp,
                color = type.color(),
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(start = 8.dp, end = cornerRadius)
    ) {
        Icon(
            painter = type.painter(),
            contentDescription = type.name,
            tint = type.color()
        )
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = type.name,
                color = type.color(),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

class TypeTagPreviewParameter: PreviewParameterProvider<Pokemon.Type> {
    override val values: Sequence<Pokemon.Type>
        get() = Pokemon.Type.entries.asSequence()
}

@Preview
@Composable
fun TypeTagPreview(
    @PreviewParameter(TypeTagPreviewParameter::class) type: Pokemon.Type
) {
    TypeTag(
        type = type,
        modifier = Modifier.fillMaxWidth()
    )
}