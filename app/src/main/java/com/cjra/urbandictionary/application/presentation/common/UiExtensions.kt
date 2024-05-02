package com.cjra.urbandictionary.application.presentation.common

import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
fun InputMethodManager.asKeyboardController(): SoftwareKeyboardController? =
    (this as? InputMethodManager)?.let { inputMethodManager ->
        object : SoftwareKeyboardController {
            override fun show() {
                inputMethodManager.showSoftInput(null, 0)
            }

            override fun hide() {
                inputMethodManager.hideSoftInputFromWindow(null, 0)
            }
        }
    }