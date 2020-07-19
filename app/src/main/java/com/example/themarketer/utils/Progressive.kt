package com.example.themarketer.utils

interface Progressive {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}