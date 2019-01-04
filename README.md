#INTIVE PATRONAGE 2018/2019 BACK-END (JAVA)
##ZADANIE 1 - REST API - CRUD

1. Żeby zbudować projekt należy wpisać w wierszu poleceń ``gradle build``
2. Żeby uruchomić projekt nałeży wpisac w wierszu poleceń``gradle bootRun``
3. Żeby dodać organizację za pomocą CURL'a należy w wierszu poleceń wpisać ``curl -d {\"name\":\"pepsi\"} -H "Content-Type: application/json" -X POST http://localhost:8080/organizations``

####Spis endpoint'ów:
1. Organizacje:
    - POST - localhost:8080/organizations - Dodaje organizację.
        - JSON: `{
          	"name": "pepsi"
          }`
    - GET - localhost:8080/organizations - Zwraca listę wszystkich organizacji.
    - GET - localhost:8080/organizations/1 - Zwraca organizację po id.
    - GET - localhost:8080/organizations?name=pepsi - Zwraca organizację po nazwie.
    - DELETE - localhost:8080/organizations/1 - Kasuje organizację po id.
    - PUT - localhost:8080/organizations/1 - Uaktualnia organizację.
2. Sale konferencyjne:
    - POST - localhost:8080/organizations/1/rooms - Dodaje sale konferencyją do organizacji.
        - JSON: `{
          	"name": "blue",
              "description": "Blue Room",
              "floor": 1,
              "available": true,
              "numberOfSeats": 100,
              "numberOfStandingPlaces": 50,
              "numberOfLyingPlaces": 10,
              "numberOfHangingPlaces": 5,
              "projectorName": "projector",
              "haveTelephone": false
          }`
    - GET - localhost:8080/organizations/1/rooms - Zwraca wszystkie salę konferencyjne danej organizacji.
    - GET - localhost:8080/organizations/1/rooms?name=blue - Zwraca salę konferencyjną po nazwie.
    - GET - localhost:8080/organizations/1/rooms/2 - Zwraca salę konferencyjną po id.
    - DELETE - localhost:8080/organizations/1/rooms/2 - Kasuje salę konferencyjną po id.
    - PUT - localhost:8080/organizations/1/rooms/2 - Uaktualnia salę konferencyjną.
3. Telefony:
    - POST - localhost:8080/organizations/1/rooms/2/telephones - Dodaje telefon do sali konferencyjnej.
        - JSON: `{
          	"internalNumber": 100,
          	"externalNumber": "48 5555 100",
          	"telephoneConnectionInterface": 0
          }`
    - GET - localhost:8080/organizations/1/rooms/2/telephones - Zwraca telefon z sali konferencyjnej.
    - DELETE - localhost:8080/organizations/1/rooms/2/telephones - Kasuje telefon z sali konferencyjnej.
    - PUT - localhost:8080/organizations/1/rooms/2/telephones - Uaktualnia telefon w sali konferencyjnej.
4. Rezerwacje:
    - POST - localhost:8080/organizations/1/rooms/2/reservations - Dodaje rezerwacje sali konferencyjnej.
        - JSON:`{
          	"reservingName": "cola",
          	"beginReservation": "2019-05-01T10:10",
          	"endReservation": "2019-05-10T12:00"
          }`
    - GET - localhost:8080/organizations/1/rooms/2/reservations - Zwraca wszystkie rezerwacje sali konferencyjnej.
    - GET - localhost:8080/organizations/1/rooms/2/reservations/3 - Zwraca rezerwację po id.
    - GET - localhost:8080/organizations/1/rooms/2/reservations?name=cola - Zwraca wszystkie rezerwację po nazwie rezerwującego.
    - DELETE - localhost:8080/organizations/1/rooms/2/reservations?name=cola - Kasuje wszystkie rezerwacje rezerwującego po jego nazwie.
    - DELETE - localhost:8080/organizations/1/rooms/2/reservations/3 - Kasuje rezerwację po id.
    - PUT - localhost:8080/organizations/1/rooms/2/reservations/3 - Uaktualnia rezerwację po id.
    
