# SpaceX Launches

**SpaceX Launches** to aplikacja na Androida wyświetlająca listę startów rakiet SpaceX. Pozwala użytkownikom przeglądać szczegóły dotyczące każdej misji, w tym jej status, obrazki i odnośniki do artykułów. Dane pobierane są z SpaceX API, cache'owane lokalnie przy użyciu SQLDelight i prezentowane w nowoczesnym interfejsie stworzonym z Jetpack Compose.

---

## Funkcje

- **Lista startów**  
  Wyświetla listę startów SpaceX z nazwą misji i statusem (sukces / porażka). Dane pobierane są z lokalnej bazy lub API.

- **Szczegóły startu**  
  Zawiera nazwę misji, datę startu, status, szczegóły, obrazki patchy (mały i duży) oraz odnośnik do artykułu.

- **Obsługa offline**  
  Dane są cache’owane lokalnie w bazie SQLDelight, co umożliwia ich przeglądanie bez połączenia z internetem.

- **Ładowanie obrazów**  
  Obrazy patchy misji ładowane i wyświetlane są za pomocą Coil. Duży patch wycentrowany na ekranie szczegółów.

- **Linki do artykułów**  
  Link do artykułu można otworzyć w zewnętrznej przeglądarce jednym tapnięciem.

- **Nowoczesny UI**  
  Interfejs użytkownika zbudowany z Jetpack Compose, zapewnia płynność i estetykę.

- **Nawigacja**  
  Nawigacja między ekranami listy i szczegółów zrealizowana z użyciem Jetpack Navigation.

---

## Technologie

- **Kotlin** – główny język programowania  
- **Jetpack Compose** – budowa interfejsu użytkownika  
- **Koin** – wstrzykiwanie zależności  
- **SQLDelight** – lokalna baza danych i cache  
- **Ktor** – żądania HTTP do API SpaceX  
- **Coil** – ładowanie obrazków z URL  
- **Jetpack Navigation** – zarządzanie nawigacją  
- **AndroidX Lifecycle** – ViewModel i coroutine

---

## Struktura projektu
hared/src/commonMain/
├── RocketLaunch.kt # Modele danych (launch, links, patch)
├── SpaceXSDK.kt # Obsługa API i bazy danych
├── AppDatabase.sq # Schemat SQLDelight i zapytania

android/src/main/
├── MainScreen.kt # Ekran listy startów
├── DetailScreen.kt # Ekran szczegółów (zdjęcia, linki)
├── RocketLaunchViewModel.kt # Zarządzanie stanem UI i pobieraniem danych
├── navigation/
│ ├── ArgumentScreen.kt
│ ├── addMainScreen.kt
│ └── addDetailsScreen.kt




