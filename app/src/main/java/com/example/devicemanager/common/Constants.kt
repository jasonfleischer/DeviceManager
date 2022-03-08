package com.example.devicemanager.common

object Constants {

    object Server {
        private const val EMULATOR_LOCALHOST_BASE_URL = "http://10.0.2.2:52511/" // this is used for localhost on emulator aka "http://127.0.0.1:52511/"
        const val BASE_URL = EMULATOR_LOCALHOST_BASE_URL // ** change this for non emulated device
    }

    object SavedState {
        const val CHOSEN_DEVICE = "SAVED_STATE_CHOSEN_DEVICE"
    }
}