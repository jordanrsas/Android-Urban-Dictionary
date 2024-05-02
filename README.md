# Android Urban Dictionary

## Basic Requirement
* Include a text input for users to enter the term they wish to search.
* Show the list of resulting definitions, with number of thumbs up and thumbs down votes. Use a RecyclerView or Lazy list for this list.
* Allow users to sort by either most thumbs up or down.
* Show a progress indication while the Urban Dictionary API call is being made.
* Write at least one unit test and one instrumented test.

---

## Technologies Used

- **Kotlin**: The app is built entirely in Kotlin, a modern programming language for Android development.
- **Jetpack Compose**: The user interface is built using Jetpack Compose, Google's modern UI toolkit for Android.
- **Koin**: Dependency injection is handled with Koin, making it easy to manage dependencies and improve testability.
- **Retrofit**: Retrofit is used to handle network requests and interact with the Urban Dictionary API.
- **Room**: Room is utilized for local data storage, allowing definitions to be saved and in the future with some new features accessed offline.
- **Mockito**: Unit testing is conducted using Mockito, a powerful mocking framework for Java and Kotlin.

---

## Clean Architecture

The Urban Dictionary App follows the principles of clean architecture, a software design approach that emphasizes separation of concerns and maintainability. The app is organized into distinct layers:

- **Presentation Layer**: Contains UI components built with Jetpack Compose, as well as view models for managing UI state.
- **Domain Layer**: Contains business logic and use cases that define the app's functionality.
- **Data Layer**: Handles data retrieval and storage, including interactions with external APIs and local databases.

![Clean Architecture.](/assets/clean.png "Android Clean Architecture")

---

## App Preview

Here's a quick preview of the app in action! This GIF demonstrates the key features and functionalities of our application, giving you a glimpse of what you can expect when using it.

In this demo, you'll see:

Search Functionality: Users can easily search for definitions of words using the search input text at the top of the screen.
- **Definition Display:** The app displays detailed definitions fetched from the Urban Dictionary API, including the definition, example usage, likes, and dislikes.
- **Clean and Intuitive UI:** The app features a user-friendly interface built with Jetpack Compose, offering a seamless and engaging user experience.


![Android Urban Dictionary.](/assets/UrbanDictionary.gif "Android Urban Dictionary")