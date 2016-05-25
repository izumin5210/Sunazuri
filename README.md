# Sunazuri
[![wercker status](https://app.wercker.com/status/91911c982144b4e89dd09c390e83a647/s/master "wercker status")](https://app.wercker.com/project/bykey/91911c982144b4e89dd09c390e83a647)

Sunazuri is an [esa.io][esa] client application for Android (unofficial)


## Architecture
Sunazuri adopted multi-layered architecture inspired by [The Clean Architecture][clean].

- Presentation Layer
    - includes Activity, Fragment, Custom View, etc.
- Infrastructure Layer
    - includes abstractions of I/O (DB, Web API, local cache, etc.)
    - "Repository pattern"
- Data Layer
    - includes 1 huge state container (using [Droidux][droidux])
    - includes some reducers that manages states
    - includes many actions that abstracts repository accesses

```
app/src/main/java/info/izumin/android/sunazuri/
├── AppComponent.java
├── AppModule.java
├── AppScope.java
├── Sunazuri.java
├── data
│   ├── DataComponent.java
│   ├── DataModule.java
│   ├── DataScope.java
│   ├── action
│   └── reducer
├── domain
│   ├── RootStore.java
│   ├── entity
│   ├── model
│   └── repository
├── infrastructure
│   ├── InfrastructureComponent.java
│   ├── InfrastructureModule.java
│   ├── api
│   ├── dao
│   ├── entity
│   ├── pref
│   ├── qualifier
│   ├── repository
│   │   └── source
│   └── util
└── presentation
    ├── activity
    ├── fragment
    └── view
```

## Libraries

- Android Support Libraries
    - Support v4
    - AppCompat v7
    - CustomTabs
    - Design
- core
    - [Dagger2](http://google.github.io/dagger/)
    - [RxJava](https://github.com/ReactiveX/RxJava) / [RxAndroid](https://github.com/ReactiveX/RxAndroid)
    - [Droidux][droidux]
- I/O
    - [Android-Orma](https://github.com/gfx/Android-Orma)
    - [Retrofit2](http://square.github.io/retrofit/)
    - [KVS Schema](https://github.com/rejasupotaro/kvs-schema/)
- UI
    - [Picasso](http://square.github.io/picasso/)
- misc
    - [Gson](https://github.com/facebook/conceal) / [StaticGson](https://github.com/gfx/StaticGson)
    - [Conceal](https://github.com/facebook/conceal)

## Inspired by

- [The Clean Architecture | 8th Light][clean]
    - [android10/Android-CleanArchitecture][clean-app]
- [googlesamples/android-architecture][googlesample]
    - [todo-mvp-clean sample][googleclean]
- [konifar/droidkaigi2016][droidkaigi2016]
- [JakeWharton/u2020][u2020]


## License

```
Copyright 2016 Masayuki Izumi (izumin5210)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[esa]: https://esa.io/
[clean]: https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
[clean-app]: https://github.com/android10/Android-CleanArchitecture
[droidkaigi2016]: https://github.com/konifar/droidkaigi2016/
[googlesample]: https://github.com/googlesamples/android-architecture
[googleclean]: https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean/
[u2020]: https://github.com/JakeWharton/u2020
[droidux]: https://github.com/izumin5210/Droidux
