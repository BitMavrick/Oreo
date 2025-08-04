# 🍪 Oreo - Jetpack Compose Demo App

Oreo is a simple Android demo app built with **Jetpack Compose** designed to help level up Android development skills — focusing especially on **design patterns**, **MVVM architecture**, **SOLID principles**, and **best practices**.

> This project is part of my learning journey to levels up Android architecture and design pattern skills. Feel free to fork, explore, or suggest improvements!

---
## Functionality

- When you pull down to refresh, the app simulates a network delay of 2-3 seconds.
- It then randomly fetches a **person’s data** or returns `null` to simulate a network error.
- The snackbar displays success or error messages.
- If an error occurs, the snackbar shows a **retry button** to fetch again.
  
The app is intentionally kept simple to focus on clean architecture, reactive UI, and modern Android development tools.

---

## Key Features

- Built with **Jetpack Compose** (Material 3) for modern UI
- Implements **MVVM architecture** with **ViewModel** and **state management**
- Uses **Dagger Hilt** for dependency injection
- Demonstrates **unidirectional data flow** and clean separation of concerns
- Shows how to handle **UI events** with snackbars including retry actions
- Implements **native pull-to-refresh** using Material 3's `PullToRefreshBox`
- Follows **SOLID principles** and best practices for maintainable code

---

## How I Built It

1. **Project Structure:**  
   - `ui` layer contains composables split into reusable components  
   - `viewmodel` holds the UI state (`MainUiState`) and event handler (`MainUiEvent`)  
   - `data` layer (repository) simulates network fetching with random person data or errors  

2. **UI State and Events:**  
   Used immutable data classes to represent UI state and sealed classes for UI events, ensuring clear and manageable state transitions.

3. **Snackbar & Pull-to-Refresh:**  
   Implemented snackbar with retry action using `SnackbarHostState.showSnackbar` with `actionLabel` and handled the user response properly.  
   Used Material 3 native `PullToRefreshBox` for swipe refresh gesture.

4. **Dependency Injection:**  
   Used Dagger Hilt to inject repository implementation into the ViewModel for clean, testable code.

---

## Why I Made Oreo

Oreo is a playground to practice and demonstrate:
- Modern Android UI with Jetpack Compose  
- Robust app architecture combining MVVM and SOLID  
- Effective state and event management in Compose  
- Practical handling of asynchronous network-like operations  
- Clean and scalable code organization for real projects

---

## How to Run

1. Clone this repository  
2. Open in Android Studio Arctic Fox or later  
3. Build and run on an emulator or device with Android 12+ for best Material 3 support  
4. Pull down to refresh and observe the snackbar messages  

---

## Future Plans

- Add localization support  
- Implement caching and offline support  
- Add more complex UI states (loading placeholders, empty/error screens)  
- Integrate real network calls  
- Add unit and UI tests  

---

## License

This project is open-source under the MIT License. Feel free to use and modify!

---

**Made with ❤️ by BitMavrick**

---

