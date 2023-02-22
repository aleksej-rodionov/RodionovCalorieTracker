package com.example.tracker_presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEffect
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.tracker_presentation.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboadrController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboadrController) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(effect.msg.asString(context))
                }
                is UiEffect.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {

        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = state.query,
            onValueChange = {
                viewModel.onEvent(SearchEvent.OnQueryChange(it))
            },
            onSearch = {
                viewModel.onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            }
        )
    }
}