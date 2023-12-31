package com.example.ventaquesos.presentation.verify_email.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ventaquesos.components.ProgressBar
import com.example.ventaquesos.core.Utils.Companion.print
import com.example.ventaquesos.domain.model.Response.*
import com.example.ventaquesos.presentation.profile.ProfileViewModel

@Composable
fun ReloadUser(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navigateToProfileScreen()
                }
            }
        }
        is Failure -> reloadUserResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}