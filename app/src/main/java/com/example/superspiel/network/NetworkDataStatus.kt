package com.example.superspiel.network

enum class NetworkDataStatus() {

    NULL() {
        override fun isLoading(): Boolean {
            return false
        }
    },
    LOADING() {
        override fun isLoading(): Boolean {
            return true
        }
    },
    DONE() {
        override fun isLoading(): Boolean {
            return false
        }
    },
    ERROR() {
        override fun isLoading(): Boolean {
            return false
        }
    };

    abstract fun isLoading() : Boolean

}