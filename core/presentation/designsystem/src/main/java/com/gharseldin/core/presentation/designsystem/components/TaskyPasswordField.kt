package com.gharseldin.core.presentation.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharseldin.core.presentation.designsystem.EyeIcon
import com.gharseldin.core.presentation.designsystem.EyeOpenIcon
import com.gharseldin.core.presentation.designsystem.R
import com.gharseldin.core.presentation.designsystem.TaskyDarkGray
import com.gharseldin.core.presentation.designsystem.TaskyLight2
import com.gharseldin.core.presentation.designsystem.TaskyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskyPasswordField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    BasicSecureTextField(
        state = state,
        textObfuscationMode = if (isPasswordVisible) {
            TextObfuscationMode.Visible
        } else {
            TextObfuscationMode.Hidden
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = TaskyDarkGray
        ),
        keyboardType = KeyboardType.Password,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(TaskyLight2)
//            .padding(vertical = 16.dp)
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
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            EyeOpenIcon
                        } else {
                            EyeIcon
                        },
                        contentDescription = if (isPasswordVisible) {
                            stringResource(R.string.show_password)
                        } else {
                            stringResource(R.string.hide_password)
                        },
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun TaskyPasswordFieldPreview() {
    TaskyTheme {
        TaskyPasswordField(
            state = rememberTextFieldState(),
            isPasswordVisible = false,
            hint = "email",
            onTogglePasswordVisibility = {}
        )
    }
}
