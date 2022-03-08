# Device Manager

This is a device management application for Android. This app shows a complete list of all the devices on the home page. Users can click on a device and visit the device details page. Users can search for a device from the top search bar on the home screen.

This application uses Kotlin, data-binding, and MMVC

## Dependencies
- HILT
- Retrofit
- Glide

## Starting the application:

1. Locally run the database found [here](https://github.com/jasonfleischer/DeviceManagerDB)

2. Open this project in Android Studio and run on the emulator

Alternatively, you can run the application with static content on the 'no-database' branch

## Associated project:
https://github.com/jasonfleischer/DeviceManagerDB

## Notes
- the project will not work on an non-emulated device with changes to the [BASE_URL](https://github.com/jasonfleischer/DeviceManager/blob/main/app/src/main/java/com/example/devicemanager/common/Constants.kt) variable
- you can run the application with static content on the 'no-database' branch
