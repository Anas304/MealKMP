package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import org.example.app.App
import org.example.project.chat.data.network.KtorRemotePostDataSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
            val postApi = remember { KtorRemotePostDataSource() }
           // PostScreen(postApi)
        }
    }
}