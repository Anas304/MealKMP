package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.app.App
import org.example.project.meal.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}