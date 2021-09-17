# CityList (MVP)

> Тестовое приложение на основе MVP паттерна, с использованием fragments, navigation component,
> recyclerview, sharedanimation, coordinatorlayout, daynight themes, junit4 и прочее

<div>
    <img src="https://user-images.githubusercontent.com/61781958/133679474-d4a9e891-83bc-45e9-a99d-400e3dbfb4ff.jpg" width="400px"</img> 
</div>

## Базовый функционал
### Приложение с 2мя экранами: 
- Список городов  
- Детальная информация о городе 

### Требования 
- Шаблон: `MVP` 
- Язык: `🇷🇺, 🇬🇧`  
- Ориентация: `portrait | landscape`
- Представления: `фрагменты с navigation component`
- Репозиторий: `заранее заготовленные данные (List<City>)` 
- Прочее: `адаптивная иконка, поддержка светлой/темной темы` 

## Обновление от 16.09

**Экран "Список городов" (Главный)**
- В ActionBar добавить поиск и кнопку перехода к избранному
- RecyclerView - добавить анимации(ripple effects), переделать на ListAdapter (+DiffUtil)
- CardView с кнопкой "добавить в избранное"
- SharedAnimation при переходе к экрану с детальной информацией

**Экран "Детальная информация"**
- Изменить разметку на CoordinatorLayout + анимации
- Кнопка избранное 

**+ Новый экран "Избранное"**
- В ActionBar добавить поиск
- Анимации при удалении

**Тестирование JUnit4**
- Тестирование для presenters

**Прочие исправления**
- Переделать presenter, в конструкторе передавать City вместо args
- Удалить landscape
- Поменять linearlayout на constraintlayout
- Добавить общий style для textview
- Аннотации для ресурсов в аргументах функций
- Ночной режим - доработать стили

```mermaid
graph LR
    A(City List) --> B(Detail)
    A --> C(Favorite)
    B --> A
    C --> B
    C --> A
```
