package com.example.ventaquesos.presentation.forgot_password

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ventaquesos.core.Constants.RESET_PASSWORD_MESSAGE
import com.example.ventaquesos.core.Utils.Companion.showMessage
import com.example.ventaquesos.presentation.forgot_password.components.ForgotPassword
import com.example.ventaquesos.presentation.forgot_password.components.ForgotPasswordContent
import com.example.ventaquesos.presentation.forgot_password.components.ForgotPasswordTopBar

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ForgotPasswordTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ForgotPasswordContent(
                padding = padding,
                sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            showMessage(context, RESET_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}