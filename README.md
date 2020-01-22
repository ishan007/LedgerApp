# LedgerApp

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
* **Repository:** This module provides data from web/local to domain layer. It contains definition of the different data sources, and how they should be used. It is independent of database and http client implementations. Repository uses local datasource as **single source of truth**.
* **Storage:** This module contains implementation for providing data from local. It uses room for getting data from local db.
* **Network:** This module contains implementation for providing data from http client. It uses retrofit as a source to fetch data from the web.



Along with the Clean Architecture approach, the following Android architecture components are also used:
* **[Room:](https://developer.android.com/training/data-storage/room/index.html)** Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* **[ViewModel:](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)** Designed to store and manage UI related data in a life cycle conscious way
* **[Live Data:](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)** An observable data holder class that, unlike a regular observable, is life cycle aware, meaning it respects the lifecycle of other app components such as activities, fragments, and services
* **[Paging Library:](https://developer.android.com/topic/libraries/architecture/paging)** The Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources. In this app [PagedList.BoundaryCallback](https://developer.android.com/reference/android/arch/paging/PagedList.BoundaryCallback) is used for syncing data from server in to database.

Also the following technologies are used:
* **[Kotlin:](https://kotlinlang.org/docs/reference/)** A statically typed programming language for modern multi-platform applications. Google rates Kotlin a first-class language for writing Android apps.
* **[Dagger 2:](https://dagger.dev/tutorial/)** A fully static, compile-time dependency injection framework for both Java and Android.
* **[RxJava 2:](https://github.com/ReactiveX/RxJava/wiki)**  Reactive Extensions for the Java Virtual Machine (JVM) – a library for composing asynchronous and event-based programs using observable sequences for the JVM.
* **[Retrofit 2:]()** A type-safe HTTP client for Android and Java  
* **[Mockito:](https://javadoc.io/static/org.mockito/mockito-core/2.9.0/org/mockito/Mockito.html)** A mocking framework for unit testing. The Mockito library enables mock creation, verification and stubbing.

## Libraries Used
* Android Architecture Components -- for using MVVM 
* Android JetPack -- for androidx components
* Retrofit2 -- for Network api 
* Dagger2 -- for DI
* Glide -- for Image Loading
* Mockito -- mocking framework for unit testing
* Room -- for local DB
* RxJava2 -- for Reactive Programming
* Paging -- for pagination   
