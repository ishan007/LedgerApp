# LedgerApp

## About
User will be able to see the list of his delieveries. This app has three screens, splash screen, listing screen and detail screen.
User will see the list of deliveries with minimum information on listing screen. He can then select the items on list screen to see it in detail. On detail screen user will see the detail information of the selected delivery and he can mark that delivery as favorite.

![Splash Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/splash-screen.png) ![List Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/list-screen.png) ![Detail Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/detail-screen.png)

## Architecture
App is built using Clean Architecture and MVVM.

![Architecture](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/architecture.png)

Modules used are as described below
* **View:** This module includes the UI like activities, fragments and adapters.
* **ViewModel:** This module includes viewmodels which interacts with domain layer and provides data to view.
* **Domain:** This module contains all business models and use cases. 
    * Entities will be used by presentation layer to render data and perform actions according to business rules.
    * Use cases are interactors and stand for application-specific business rules of the software. This layer is isolated from changes to the database, common frameworks, and the UI. 
* **Repository:** This module provides data from web/local to domain layer. It contains definition of the different data sources, and how they should be used. It is independent of database and http client implementations. 
* **Storage:** This module contains implementation for providing data from local. It uses room for getting data from local db.
* **Network:** This module contains implementation for providing data from http client. It uses retrofit as a source to fetch data from the web.



Along with the Clean Architecture approach, the following Android architecture components are also used:
* **Room:** Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* **ViewModel:** Designed to store and manage UI related data in a life cycle conscious way
* **Live Data:** An observable data holder class that, unlike a regular observable, is life cycle aware, meaning it respects the lifecycle of other app components such as activities, fragments, and services
* **Paging Library:** Makes it easier to load data gradually and gracefully within RecyclerView.

Also the following technologies are used:
* **Kotlin:** A statically typed programming language for modern multi-platform applications. Google rates Kotlin a first-class language for writing Android apps.
* **Dagger 2:** A fully static, compile-time dependency injection framework for both Java and Android.
* **RxJava 2:**  Reactive Extensions for the Java Virtual Machine (JVM) – a library for composing asynchronous and event-based programs using observable sequences for the JVM.
* **Retrofit 2:** A type-safe HTTP client for Android and Java  

## Libraries Used
* Android Architecture Components -- for using MVVM 
* Android JetPack -- for androidx components
* Retrofit2 -- for Network api 
* Dagger2 -- for DI
* Glide -- for Image Loading
* Mockito -- mocking framework for testing
* Room -- for local DB
* RxJava2 -- for Reactive Programming
* Paging -- for pagination   
