package com.fgarcia.core.coroutines

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutinesDispatchers {
  fun main(): CoroutineDispatcher = Dispatchers.Main
  fun default(): CoroutineDispatcher = Dispatchers.Default
  fun io(): CoroutineDispatcher = Dispatchers.IO
  fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class AppCoroutinesDispatchers @Inject constructor() : CoroutinesDispatchers