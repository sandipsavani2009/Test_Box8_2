package com.test.box8New

import android.app.Application

class TodoApplication : Application() {

    init {
        INSTANT = this
    }

    companion object {
        lateinit var INSTANT: TodoApplication
            private set
    }

}