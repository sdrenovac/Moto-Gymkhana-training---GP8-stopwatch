# GP8 Stopwatch for Moto Gymkhana

A lightweight Android stopwatch for measuring a rider's time through the Moto Gymkhana GP8 exercise. The application uses the phone's accelerometer to detect the motorcycle starting and stopping, so the rider does not need to operate the screen during a run.

## Features

- automatic start and stop detection using the accelerometer
- large, high-contrast timer display
- adjustable motion and stationary thresholds
- portrait and landscape orientation support
- responsive layout with scrolling on small or low-resolution screens
- minimum 15-second running period before finish detection
- no internet connection, account, GPS, or external sensor required

## How it works

1. Secure the phone firmly on the motorcycle.
2. Open the app and check the sensor status.
3. Press **START** while the motorcycle is stationary.
4. Begin riding when ready; motion starts the timer automatically.
5. After at least 15 seconds, stop and remain still; confirmed stationary status stops the timer.

The recorded finish time corresponds to the beginning of the confirmed stationary period, not to the later moment when stationary status is confirmed.

## Sensor settings

The default values are intended as a starting point. Motorcycle vibration, phone model, mounting position, surface, and riding style can require adjustment. Use the in-app sensor settings and test several runs before relying on recorded times.

If the app detects the start but does not detect the finish, first make the stationary detection slightly less strict and ensure the phone mount is rigid enough to avoid continued vibration after stopping.

## Project structure

- `app/` – Android application source
- `tools/MotionDetectorSelfTest.java` – standalone detector logic tests
- `build_apk.sh` – Gradle build helper
- `docs/` – bilingual Croatian/English user manual

## Build

Open the project in Android Studio and build the debug APK:

```text
Build > Build APK(s)
```

The resulting file is normally located at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Alternatively, with a compatible JDK and Android SDK configured, run:

```bash
./build_apk.sh
```

## Important release note

This repository contains the updated responsive source code. A newly compiled APK is not included yet. Do not treat an older GP8 Stopwatch APK as a build of this source revision.

## Documentation

See [`docs/GP8_User_Manual_HR_EN.md`](docs/GP8_User_Manual_HR_EN.md) for detailed Croatian and English installation, operation, calibration, troubleshooting, and safety instructions.

## Safety

Configure and operate the phone only while safely stopped. Mount it securely and keep attention on the course and surroundings while riding.
