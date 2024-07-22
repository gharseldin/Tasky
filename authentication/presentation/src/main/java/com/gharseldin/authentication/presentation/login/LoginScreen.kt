package com.gharseldin.authentication.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharseldin.authentication.presentation.R
import com.gharseldin.core.presentation.designsystem.CheckIcon
import com.gharseldin.core.presentation.designsystem.Inter
import com.gharseldin.core.presentation.designsystem.TaskyBlue
import com.gharseldin.core.presentation.designsystem.TaskyGray
import com.gharseldin.core.presentation.designsystem.TaskyLight
import com.gharseldin.core.presentation.designsystem.TaskyLight2
import com.gharseldin.core.presentation.designsystem.TaskyTheme
import com.gharseldin.core.presentation.designsystem.TaskylightBlue
import com.gharseldin.core.presentation.designsystem.components.TaskyActionButton
import com.gharseldin.core.presentation.designsystem.components.TaskyPasswordField
import com.gharseldin.core.presentation.designsystem.components.TaskyTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: AuthenticationViewModel = koinViewModel()
) {
    LoginScreen(
        state = viewModel.state,
        onAction = {
            when (it) {
                AuthenticationAction.onLoginClick -> TODO()
                AuthenticationAction.onSignUpClick -> onSignUpClick()
                AuthenticationAction.OnTogglePasswordVisibilityClick -> TODO()
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    state: AuthenticationState,
    onAction: (AuthenticationAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.welcome_back),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp

                    )
                )
                .background(MaterialTheme.colorScheme.onBackground)
                .weight(1f)
                .padding(top = 48.dp)
                .padding(horizontal = 16.dp),

            ) {
            TaskyTextField(
                state = state.email,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                hint = stringResource(R.string.email_address),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskyPasswordField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onPasswordFieldFocusChange = {},
                onTogglePasswordVisibility = {
                    onAction(AuthenticationAction.OnTogglePasswordVisibilityClick)
                },
                hint = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(24.dp))
            TaskyActionButton(text = stringResource(R.string.log_in), isLoading = false) {

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center,
        ) {
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Inter,
                        color = TaskyGray
                    )
                ) {
                    append(stringResource(R.string.dont_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(R.string.sign_up)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Inter,
                            color = TaskyBlue
                        )
                    ) {
                        append(stringResource(id = R.string.sign_up))
                    }
                }
            }
            ClickableText(text = annotatedString) { offset ->
                annotatedString.getStringAnnotations(
                    tag = "clickable_text",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    onAction(AuthenticationAction.onSignUpClick)
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    TaskyTheme {
        LoginScreen(AuthenticationState()) {}
    }
}