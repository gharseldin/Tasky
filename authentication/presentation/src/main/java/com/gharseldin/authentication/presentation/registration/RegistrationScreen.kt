package com.gharseldin.authentication.presentation.registration

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharseldin.authentication.domain.PasswordValidationState
import com.gharseldin.authentication.presentation.R
import com.gharseldin.core.presentation.designsystem.CheckIcon
import com.gharseldin.core.presentation.designsystem.CrossIcon
import com.gharseldin.core.presentation.designsystem.TaskyGreen
import com.gharseldin.core.presentation.designsystem.TaskyTheme
import com.gharseldin.core.presentation.designsystem.components.TaskyActionButton
import com.gharseldin.core.presentation.designsystem.components.TaskyPasswordField
import com.gharseldin.core.presentation.designsystem.components.TaskyTextField
import com.gharseldin.core.presentation.ui.UiText
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot(
    onBackClicked: () -> Unit,
    onGetStartedClicked: () -> Unit,
    registrationScreenViewModel: RegistrationScreenViewModel = koinViewModel()
) {
    RegistrationScreen(
        state = registrationScreenViewModel.state
    ) { action ->
        when (action) {
            RegistrationAction.OnBackClicked -> onBackClicked()
            RegistrationAction.OnGetStartedClicked -> TODO()
            RegistrationAction.OnTogglePasswordVisibilityClicked -> TODO()
            is RegistrationAction.onPasswordFieldFocusChange -> {
                registrationScreenViewModel.passwordFieldFocusChanged(action.isFocused)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit
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
                text = stringResource(R.string.create_your_account),
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
                state = state.name,
                endIcon = if (state.nameValidationState.isNameValid) {
                    CheckIcon
                } else {
                    null
                },
                endIconTint = TaskyGreen,
                hint = stringResource(R.string.name),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskyTextField(
                state = state.email,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                endIconTint = TaskyGreen,
                hint = stringResource(R.string.email_address),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskyPasswordField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onPasswordFieldFocusChange = {
                    onAction(
                        RegistrationAction.onPasswordFieldFocusChange(
                            it
                        )
                    )
                },
                onTogglePasswordVisibility = {
                    onAction(RegistrationAction.OnTogglePasswordVisibilityClicked)
                },
                hint = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isPasswordFieldFocused) {
                PasswordValidationBlock(state = state.passwordValidationState)
            }
            Spacer(modifier = Modifier.height(24.dp))
            TaskyActionButton(text = stringResource(R.string.get_started), isLoading = false) {
            }
        }
    }
}

@Composable
fun PasswordValidationBlock(
    modifier: Modifier = Modifier,
    state: PasswordValidationState
) {
    validationItem(
        validationCriteria = R.string.password_is_at_least_9_characters_long,
        validationState = state.hasMinLength
    )
    Spacer(modifier = Modifier.height(8.dp))
    validationItem(
        validationCriteria = R.string.password_must_contain_a_digit,
        validationState = state.hasNumber
    )
    Spacer(modifier = Modifier.height(8.dp))
    validationItem(
        validationCriteria = R.string.password_must_contain_a_lowercase_letter,
        validationState = state.hasLowerCaseCharacter
    )
    Spacer(modifier = Modifier.height(8.dp))
    validationItem(
        validationCriteria = R.string.password_must_contain_an_uppercase_letter,
        validationState = state.hasUpperCaseCharacter
    )
}

@Composable
fun validationItem(
    validationCriteria: Int,
    validationState: Boolean
) {
    Row {
        Icon(
            modifier = Modifier
                .padding(start = 20.dp),
            imageVector = if (validationState) {
                CheckIcon
            } else {
                CrossIcon
            },
            contentDescription = UiText.StringResource(validationCriteria).toString(),
            tint = if (validationState) {
                TaskyGreen
            } else {
                MaterialTheme.colorScheme.error
            }

        )
        Text(
            text = stringResource(validationCriteria),
            modifier = Modifier.padding(start = 20.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    TaskyTheme {
        RegistrationScreen(
            state = RegistrationState(
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true
                )
            )
        ) {}
    }
}
