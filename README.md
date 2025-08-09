# 🎙️ Live Audio Room

An Android application that allows users to **create and join live group audio rooms** using **ZEGOCLOUD UIKit**, with secure **Firebase Authentication** for login & signup. Users can connect in real-time through voice, making it perfect for discussions, events, or casual hangouts.

## 📱 Features
- **Firebase Authentication**
  - Email/Password login and signup
  - Google Sign-In support
- **Real-time Audio Rooms**
  - Create new audio rooms instantly
  - Join existing rooms with friends or public groups
- **Group Voice Chat**
  - Multiple users can talk together in a room
  - Low latency and high audio quality
- **Firestore Database**
  - Store and manage user profiles
  - Room details and participant tracking
- **Modern UI**
  - Built entirely with **Jetpack Compose**
  - Material 3 design components
- **Navigation**
  - Seamless screen transitions using Navigation Compose

## 🛠️ Tech Stack
- **Kotlin** - Primary language
- **Jetpack Compose** - UI toolkit
- **Firebase Authentication** - User login/signup
- **Firebase Firestore** - Real-time database
- **ZEGOCLOUD UIKit** - Prebuilt Live Audio Room solution
- **Coil** - Image loading in Compose
- **Navigation Compose** - In-app navigation

## 📦 Dependencies
Main libraries and versions:
- `androidx.core:core-ktx`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `androidx.activity:activity-compose`
- `androidx.compose.material3`
- `androidx.navigation:navigation-compose:2.8.6`
- `io.coil-kt:coil-compose:2.2.0`
- `com.github.ZEGOCLOUD:zego_uikit_prebuilt_live_audio_room_android:2.5.6`
- `com.google.firebase:firebase-auth`
- `com.google.firebase:firebase-firestore`

## 🚀 Getting Started

### Prerequisites
- Android Studio Koala or newer
- Minimum SDK: **26**
- Compile SDK: **35**
- A Firebase project (for Authentication & Firestore)
- A ZEGOCLOUD account and App credentials

### Setup
1. **Clone the repository**
   ```bash
   git clone https://github.com/Ravi718r/Live-Audio-Room.git
