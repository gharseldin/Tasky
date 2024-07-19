package com.gharseldin.core.presentation.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharseldin.core.presentation.designsystem.EyeIcon
import com.gharseldin.core.presentation.designsystem.TaskyDarkGray
import com.gharseldin.core.presentation.designsystem.TaskyLight2
import com.gharseldin.core.presentation.designsystem.TaskyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskyTextField(
    state: TextFieldState,
    endIcon: ImageVector?,
    endIconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    BasicTextField2(
        state = state,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = TaskyDarkGray
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(TaskyLight2)
            .padding(vertical = 16.dp)
            .padding(start = 20.dp),
        decorator = { container ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    container()
                }
                endIcon?.let { icon ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = endIconTint,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun TaskyTextFieldPreview() {
    TaskyTheme {
        TaskyTextField(
            state = rememberTextFieldState(),
            endIcon = EyeIcon,
            hint = "email"
        )
    }
}
