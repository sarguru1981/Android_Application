# Android-Assignment

Sample application to demonstrate Multi-module Clean MVVM Architecture and usage of Dagger Hilt, Kotlin Coroutines &amp; Flow, Jetpack compose, Room, Retrofit, Paging, Navigation Graph, Unit tests

## Clean Architecture

Created the application as a Clean Architecture which makes the  project Robust, Extensible, Maintainable, Scalable, Reusable and Testable(Unit testing).

* The Principle specifies that inner circles should contain business logic, and outer circles should contain implementation details.
* The dependency rule specifies that each level can depend only on the nearest inward level — this is what makes the architecture work.

![image](https://user-images.githubusercontent.com/26491505/138539745-1be43639-a348-44da-8df6-4ac1d322591e.png)

## Clean Architecture Layers

![image](https://camo.githubusercontent.com/1ae8ec4dbeb4e88d34e8ae78d1260fba00a965af9158f4fe50c34dbfef4873b4/68747470733a2f2f75706c6f6164732e746f7074616c2e696f2f626c6f672f696d6167652f3132373630382f746f7074616c2d626c6f672d696d6167652d313534333431333637313739342d38303939336131396665613937343737353234373633633930386235306137612e706e67)

### The Domain Layer

Contains all the models and business rules of your app.

* Use cases: This layer converts and passes user actions to inner layers of the application. Each use case class has only one function that invokes the use case.
* DataSource Implementation: The class that implements the DataSource interface in the Data layer.

### The Data Layer

This layer is for accessing database or the internet using Repository pattern.

* DataSource interface: The interface that the Domain layer must implement.
* Repository class: Provides methods for accessing the data that delegate to DataSource.
* The repository pattern is a good example of the Dependency Inversion Principle, [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

### The Presentation Layer

This layer contains the User Interface-related code.

#### Using MVVM

Using MVVM pattern in this layer because it’s supported by Jetpack Compose.

* Model: This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.
* View: The purpose of this layer is to inform the ViewModel about the user’s action.
* ViewModel: It exposes those data streams which are relevant to the View. It servers as a link between the Model and the View.

![image](https://user-images.githubusercontent.com/26491505/137802023-883c2834-3b90-479a-ada8-f8ad129d4021.png)

## Rules followed here...

Followed SOLID principles to make that code more maintainable, flexible and extensible

### Single Responsibility 

Created class which has only one responsibility.

### Open-closed

It is able to extend the behavior, without breaking its usage, or modifying its extensions.

### Liskov Substitution

Created the classes of one type, and any subclasses of that class is able to represent the base class along with usage of the subclass.

### Interface Segregation

Created smaller interfaces to prevent the class from implementing the methods that it doesn’t need.

### Dependency Inversion

Classes depend on abstractions rather than concrete implementations. Also higher level modules aren’t depend on lower level modules.

## [Flow](https://developer.android.com/kotlin/flow) instead of [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)

Expose the result of a one-shot operation with a Mutable data holder. Used StateFlow when exposing UI state to a view for safe and efficient observer designed to hold UI state, which:

* always has a value.
* only has one value.
* supports multiple observers.
* always replays the latest value on subscription, independently of the number of active observers.

![image](https://miro.medium.com/max/1400/0*Hf3EmJ8gchpSy6nd)

## Libraries

* [Compose](https://developer.android.com/jetpack/compose) Created the app with declarative style API, instead of creating the layout.
* [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel) Designed to store and manage UI-related data in a lifecycle conscious way.
* [Room](https://developer.android.com/topic/libraries/architecture/room) Used for data storage persistence which provides an abstraction layer over SQLite.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) A dependency injection library to reduce the boilerplate of doing manual dependency injection in the project.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
* [Flow](https://developer.android.com/kotlin/flow) Used flow to receive live updates from a database.
* [Retrofit](https://square.github.io/retrofit/) Used a Type-safe HTTP client for Android, Java and Kotlin.
* [Gson](https://github.com/google/gson) A serialization/deserialization library to convert objects into JSON and back.
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) To Logs HTTP requests and responses.
* [Mockito](https://github.com/mockito/mockito) A framework for unit tests written in Kotlin.

## Unit Testing

Written unit test case for View model & Coroutines to enforce to have a good app architecture
Added the necessary dependencies

    testImplementation 'junit:junit:4.12'
    testImplementation "org.mockito:mockito-core:3.3.3"
    testImplementation 'androidx.arch.core:core-testing:2.1.0'
    testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4'

Created the TestRule, with the name `TestCoroutineRule` to replace the Dispatcher.Main with TestCoroutineDispatcher during the execution of a test. Because, using `viewModelScope.launch{}` runs on the main thread and it can’t use the main looper in the unit test.
* During the unit-test, it enables the main dispatcher to use `TestCoroutineDispatcher`.
* After the test, it resets and cleanup.