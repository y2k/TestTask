Первый спасенный от клина проект 😎

Никаких итеракторов/репозиториев/di - в п_зду эту кашу, гляди как я лихо функциям е_ашу.

после<br>
[SharedViewModel.kt](app/src/main/java/com/example/testtask/view/viewmodel/SharedViewModel.kt#L19)<br>
до<br>
[SharedViewModel.kt](https://github.com/KirstenLy/TestTask/blob/master/app/src/main/java/com/example/testtask/view/viewmodel/SharedViewModel.kt#L19)

Всю логику размазанную по кучи клин слоев удалось собрать в одном месте.
А из эффектов там оказался один запрос в сеть (+ мой добавленный глобальный стор).

Теперь ViewModel это просто набор (чистых) стат. функций.
Их можно хоть в Kotlin Scratch запускать и играться на локальном компе, как в репле.
