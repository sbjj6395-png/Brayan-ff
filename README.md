# MTH Gaming Panel — Android starter project

A standalone Android UI prototype inspired by the dark/blue gaming panel shown in the reference video. Includes interactive switches, color selection, and a practice-preview dialog.

## Important scope
This is a standalone demo app. The toggles only update the app's own UI; they do not inject into, modify, or provide an unfair advantage in third-party games. It does not include ESP, aim-lock, hitbox manipulation, memory editing, or anti-cheat bypass.

## Build an APK
1. Install Android Studio (current stable) and its Android SDK Platform 35.
2. Open this folder (`MTHGamingPanel`) in Android Studio.
3. Let Gradle sync and resolve dependencies.
4. Select **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
5. Android Studio will show the generated APK location. For a signed release, use **Build → Generate Signed Bundle / APK**.

Package: `com.example.mthgamingpanel`
Minimum Android: 6.0 (API 23)

## Build APK without a computer (using GitHub Actions)

This project includes `.github/workflows/build-apk.yml` for a cloud build. From your Android phone:
1. Create a repository on GitHub and upload the **project files and folders** (not just the ZIP file), preserving their folder structure.
2. Open the repository's **Actions** tab and run **Build Android APK** using **Run workflow**. A push to `main` also starts a build.
3. Open the completed workflow run, find **Artifacts**, and download `MTHGamingPanel-debug-apk`.
4. Extract the downloaded artifact ZIP and open `app-debug.apk` on your phone. Android may ask you to allow installs from that source.

This is an unsigned debug APK for testing, not a Play Store release. The panel is a standalone demo; its switches do not alter third-party games.
