package com.example.superspiel.global

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle

class MainActivityLifecycleObserver(
    private val context: Context,
    private val lifecycle: Lifecycle,
): DefaultLifecycleObserver {

    fun ccc() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {

        }
    }

}