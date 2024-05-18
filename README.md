# MVVM Quote App
This native Android application is built according to best practices using MVVM architecture with modern Android development tools and libraries. This application allows users to fetch quotes from a [third-party quote API](https://github.com/lukePeavey/quotable).

## MVVM Architecture
### Architecture Diagram
![MVVM Quote App Architecture](https://github.com/XiangLun0713/mvvm-quote-app/assets/93761074/05841e25-c3c6-4658-905f-dcc1dce610c0)

### MVVM Concepts
The application is built using an MVVM architecture with three major layers/tiers, which are the presentation, domain, and data layer, each with their own responsibilities, to be exact:

#### Presentation Layer
  
The presentation layer consists of two major components, namely the UI and the View Model component. The UI component contains all the UI elements, such as the screens and UI elements (buttons, text fields, etc.), whereas the View Model component contains UI states, usually in the form of LiveData. 

The UI and View Model components work together by exposing an immutable LiveData from the view model to the UI component, which allows the UI component to render the UI based on the latest state. When there is a user interaction, the UI component will notify the View Models component for further action. You might be wondering why we can’t just place all the states within the UI components themselves; this way, we can eliminate the need to create extra View Model components. In fact, view models are essential in Android applications to make sure that you do not lose your UI states when configuration changes (i.e., when the user rotates their device). This is because UI will be destroyed and recreated when configuration changes, but view models do not. 

Whenever needed, the view model can emit global events that need to be broadcast to various parts of the app via an external event bus (not included in the presentation layer). For example, when network connectivity changes or there is an error and you want to show an error message to the user using toast messages. A properly set-up presentation layer should only be responsible for displaying the user interface (UI) to the users and capturing the input from the user. It should not be performing any business logic.

#### Domain Layer

The domain layer consists of models (Kotlin data classes) to define the data structure and potentially encapsulate relevant logic. It also contains the abstract repositories (usually implemented using interfaces) to define functionalities or data access contracts used by the domain logic. A properly set-up domain layer should only be responsible for defining the data structure, performing business logic (calculations, validation, etc.), and providing data to the presentation layers. It should not think about where the data comes from. 

#### Data Layer

The data layer consists of the implementation of the abstract repositories in the domain layer and concrete data access logic to interact with specific data sources (databases, APIs, etc.). If your application is offline-first, the responsibilities of fetching and syncing the data from both local and remote data sources lie on the data layer. A properly set-up domain layer should only be responsible for handling the persistence and retrieval of data.

### Benefits of MVVM
- **Separation of Concerns**: Minimizing the interrelation of code, make the code easier to test or to maintain.
- **Testability**: Making it easier to write unit tests by creating dummy view model and model.
- **Flexibility**: UI can be redesigned without affecting the model and the logic.
- **Faster delivery**: Allowing designers and developers to work more independently.

## Tools and Libraries Used
- Kotlin as the programming language.
- Ktor client as the HTTP client.
- Dagger & Hilt for dependency injection.
- Arrow for better error handling.
- Jetpack Compose and Material Design 3 (Material You) for building the User Interface.

## Application Preview
<img width="350" alt="image" src="https://github.com/XiangLun0713/mvvm-quote-app/assets/93761074/e9f71dce-5f3f-4e39-b379-b47be9528241">
