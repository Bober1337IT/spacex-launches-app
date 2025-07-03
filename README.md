SpaceX Launches AppOverviewSpaceX Launches is an Android application that displays a list of SpaceX rocket launches, allowing users to view detailed information about each launch. The app fetches data from the SpaceX API, caches it locally using SQLDelight, and presents it in a modern UI built with Jetpack Compose. Users can view mission details, launch status, images (mission patches), and open related articles in a browser.FeaturesLaunch List: Displays a list of SpaceX launches with mission names and statuses, loaded from a local database or the SpaceX API.
Launch Details: Shows detailed information for a selected launch, including mission name, launch date, success status, details, mission patch images, and a link to an article.
Offline Support: Caches launch data in a local SQLDelight database, allowing details to be loaded without network requests.
Image Loading: Displays mission patch images (small and large) using Coil, with the large patch centered on the detail screen.
Clickable Article Links: Opens external articles in the device’s browser with a single tap.
Modern UI: Built with Jetpack Compose for a responsive and visually appealing interface.
Navigation: Uses Jetpack Navigation for seamless transitions between the launch list and detail screens.

Technologies Used
Kotlin: Primary programming language.
Jetpack Compose: For building the UI.
Koin: For dependency injection.
SQLDelight: For local database storage and caching.
Ktor: For making HTTP requests to the SpaceX API.
Coil: For loading and displaying images from URLs.
Jetpack Navigation: For handling screen navigation.
AndroidX Lifecycle: For managing ViewModel and coroutines.

Project Structure
shared/src/commonMain: Contains shared logic, including:RocketLaunch.kt: Data models for launches, links, and patches.
SpaceXSDK.kt: Handles API calls and database interactions.
AppDatabase.sq: Defines SQLDelight schema and queries.

android/src/main: Android-specific code, including:MainScreen.kt: Displays the list of launches.
DetailScreen.kt: Shows detailed launch information with centered images and clickable article links.
RocketLaunchViewModel.kt: Manages UI state and data fetching.
Navigation setup (ArgumentScreen.kt, addMainScreen.kt, addDetailsScreen.kt).

