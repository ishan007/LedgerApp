# LedgerApp

User will be able to see the list of his delieveries. This app has three screens, splash screen, listing screen and detail screen.
User will see the list of deliveries with minimum information on listing screen. He can then select the items on list screen to see it in detail. On detail screen user will see the detail information of the selected delivery and he can mark that delivery as favorite.

![Splash Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/splash-screen.png) ![List Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/list-screen.png) ![Detail Screen](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/detail-screen.png)

## Architecture
App is built using Clean Architecture and MVVM.

![Architecture](https://github.com/ishan007/LedgerApp/blob/master/app/sceenshots/architecture.png)


* **[DI](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/di):** This module is responsible for providing dependencies to the objects.

* **[View:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/view)** This module is responsible for populating data and error on UI. It includes activities, fragments and adapters.

* **[ViewModel:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/viewmodel)** This module is responisble for fetching data from domain layer, maintaining data state and providing data to view moudel. It includes ViewModels and custom observers.
    
* **[Repository:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/repository)** This module provides data from **single source of truth** to domain layer. It has access to local datasource(A layer which interacts with database layer) and remote datasource(A layer which interacts with webservice layer). Repository uses local datasource as **single source of truth**. 
Repository have folowing submodules -
            <p>* *[Domain:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/repository/domain)* This module is responsible for performing business logics. It has all the usecases application requires.  
            * *[Storage:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/repository/storage)* This module is resposible for providing data and performing curd operation in to local data base using [Room Library](https://developer.android.com/training/data-storage/room/index.html)
            * *[Network:](https://github.com/ishan007/LedgerApp/tree/master/app/src/main/java/com/example/deliveryledger/repository/network)* This module is responsible for fetching data from webserivices using [Retrofit 2:](https://square.github.io/retrofit/) library



## Android architecture components used in this application are - 

* **[ViewModel:](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)** Designed to store and manage UI related data in a life cycle conscious way

* **[Live Data:](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)** An observable data holder class that, unlike a regular observable, is life cycle aware, meaning it respects the lifecycle of other app components such as activities, fragments, and services

* **[Paging Library:](https://developer.android.com/topic/libraries/architecture/paging)** The Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources. In this app [PagedList.BoundaryCallback](https://developer.android.com/reference/android/arch/paging/PagedList.BoundaryCallback) is used for syncing data from server in to database.

* **[Room:](https://developer.android.com/training/data-storage/room/index.html)** Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.


## Third party libaries used in this application are -

* **[Dagger 2:](https://dagger.dev/tutorial/)** Dagger is compile-time dependency injection framework provided by google.

* **[RxJava 2:](https://github.com/ReactiveX/RxJava/wiki)**  Reactive Extensions for the Java Virtual Machine (JVM) – a library for composing asynchronous and event-based programs using observable sequences for the JVM.

* **[Retrofit 2:](https://square.github.io/retrofit/)** A type-safe HTTP client for Android and Java  

* **[Mockito:](https://javadoc.io/static/org.mockito/mockito-core/2.9.0/org/mockito/Mockito.html)** A mocking framework for unit testing. The Mockito library enables mock creation, verification and stubbing.

* **[Glide:](https://bumptech.github.io/glide/)** Glide is a fast and efficient image loading library for Android focused on smooth scrolling. Glide offers an easy to use API, a performant and extensible resource decoding pipeline and automatic resource pooling.

## Language Used - 

* **[Kotlin:](https://kotlinlang.org/docs/reference/)** Kotlin is a great fit for developing Android applications, bringing all of the advantages of a modern language to the Android platform without introducing any new restrictions.


