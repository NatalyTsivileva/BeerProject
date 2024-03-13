package com.civileva.nata.beerproject.ui

sealed class States<T>()
class Success<T>(val result:T):States<T>()
class Error<T>(val text:String):States<T>()
class Loading<T>:States<T>()