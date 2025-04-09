# Structure du Projet

Le projet est organisé autour de Kotlin Multiplatform et suit les principes de la Clean Architecture combinés à une approche MVI.

## Répartition des sources

- **androidMain** : Code spécifique à Android (implémentations, ressources Android).
- **desktopMain** : Code spécifique à Desktop.
- **commonMain** : Code partagé entre toutes les plateformes.
  - **data** : Gère l’accès aux données (API via Ktor, cache, Room, DataStore, etc.).
  - **domain** : Contient les entités métiers et les interfaces des repositories.
  - **ui** : Interface utilisateur avec Jetpack Compose, ViewModels et la navigation, utilisant le pattern MVI.

## Approche MVI

Le flux MVI se structure ainsi :
- L'utilisateur déclenche une **action**.
- Le **ViewModel** capte l’action et met à jour le **state**.
- La **view** observe le state et se met à jour en conséquence.

Cela permet un flux de données unidirectionnel, facilitant la gestion et le test de l’interface.

