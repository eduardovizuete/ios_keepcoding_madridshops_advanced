# Practica keepcoding Android Avanzado Actividades
Create a mobile application to display information of Activities in Madrid, even when the user has no Internet connection. Activities should be shown in a Google Maps
### Requirements
1. When starting the App for the first time, if there's Internet connection it will download all information from the Activities access point (see below), including all images.
2. The App will cache everything locally: images, data, etc. Even maps. See below for tips
3. Next time the App is started, if more than 7 days has passed the cache will be invalidated (deleted) and everything will be2/12 downloaded again.
4. If there's no Internet connection a message will be shown to the user.
5. The app will have a main menu screen where we'll add one button & a logo. The button takes us to the list of shops.
6. The list of activities will show in the upper 50% screen a map with one pin for each shop.
7. The list of activities will show in the lower 50% screen. Logo to the left, background image taking all the row, activity name in the front. Tapping a row takes us to the detail activity screen.
8. If you tap on a pin in the map a callout will open with the logo + activity name. Taping the callout takes us to the detail activity screen.
9. The map will be always centered in madrid, showing also the user location
10. We can filter activity using a search bar accesible in the activities screen. While filtering, activities will be removed from the list and map
11. All data is at least in Spanish & English: should cache all and display in Spanish (if that's our phone's language) or English otherwise
12. Activity detail screen should show shop name, description, address, and a map showing the activity's location without any pin (just the are around the Activity)

### Score by Tasks
| Task | Score |
| ------ | ------ |
| There is a README.md file in the repo | 2 % |
| repo has commit history | 1 % |
| App starts and runs without problems | 10 % |
| App has (new) unit tests | 5 % |
| Unit tests are passing | 5 % |
| You implemented the new functionality |  |
| - Activities | 20 % |
| - Map | 10 % |
| - Search | 7 % |
| - Cache | 30 % |
| Architecture |  |
| - Proper use of interactors | 5 % |
| - Network Managers | 5 % |
| Total | 100 % |

