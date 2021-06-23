package io.github.haniyehkhaksar.weatherapp.utils

import dagger.hilt.android.HiltAndroidApp
import io.github.haniyehkhaksar.weatherapp.WeatherApp

/**
 * The application class used when running the app in instrumented tests.
 *
 * This is activated in [TestRunner].
 */
class TestApp : WeatherApp()
