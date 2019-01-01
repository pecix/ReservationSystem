#INTIVE PATRONAGE 2018/2019 BACK-END (JAVA)
##ZADANIE 1 - REST API - CRUD

1. Żeby zbudować projekt należy wpisać w wierszu poleceń ``gradle build``
2. Żeby uruchomić projekt nałeży wpisac w wierszu poleceń``gradle bootRun``
3. Żeby dodać organizację za pomocą CURL'a należy w wierszu poleceń wpisać ``curl -d {\"name\":\"pepsi\"} -H "Content-Type: application/json" -X POST http://localhost:8080/organization``

####Spis endpiont'ów:
1. Organizacje:
    - POST - localhost:8080/organization - Dodaje organizację.
        - JSON: `{
          	"name": "pepsi"
          }`
    - GET - localhost:8080/organizations - Zwraca wszystkie organizacje.
    - GET - localhost:8080/organization/id/1 - Zwraca organizację po id.
    - GET - localhost:8080/organization/pepsi - Zwraca organizację po nazwie.
    - DELETE - localhost:8080/organization/1 - Kasuje organizację po id.
2. Sale konferencyjne:
    - POST - localhost:8080/**pepsi**/conferenceroom - Dodaje sale konferencyją do organizacji.
        - JSON: `{
          	"name": "Blue Room",
              "description": "description",
              "floor": 1,
              "available": true,
              "numberOfSeats": 100,
              "numberOfStandingPlaces": 50,
              "numberOfLyingPlaces": 10,
              "numberOfHangingPlaces": 5,
              "projectorName": "projector",
              "haveTelephone": false
          }`
    - GET - localhost:8080/**pepsi**/conferencerooms - Zwraca wszystkie sale konferencyjne danej organizacji.
    - GET - localhost:8080/**pepsi**/conferenceroom/blue room - Zwraca sale konferencyjną po nazwie.
    - GET - localhost:8080/**pepsi**/conferenceroom/id/1 - Zwraca salę konferencyjną po id.
    - DELETE - localhost:8080/**pepsi**/conferenceroom/blue room - Kasuje sale konferencyjna po nazwie.
3. Telefony:
    - POST - localhost:8080/**pepsi**/**blue room**/telephone - Dodaje telefon do sali konferencyjnej.
        - JSON: `{
          	"internalNumber": 100,
          	"externalNumber": "48 5555 100",
          	"telephoneConnectionInterface": 0
          }`
    - GET - localhost:8080/**pepsi**/**blue room**/telephone - Zwraca telefon z sali konferencyjnej.
    - DELETE - localhost:8080/**pepsi**/**blue room**/telephone - Kasuje telefon z sali konferencyjnej.
4. Rezerwacje:
    - POST - localhost:8080/**pepsi**/**blue room**/reservation - Dodaje rezerwacje sali konferencyjnej.
        - JSON:`{
          	"reservingName": "pepsi",
          	"beginReservation": "2019-05-01T10:10",
          	"endReservation": "2019-05-10T12:00"
          }`
    - GET - localhost:8080/**pepsi**/**blue room**/reservations - Zwraca wszystkie rezerwacje sali konferencyjnej.
    - GET - localhost:8080/**pepsi**/**blue room**/reservation/id/1 - Zwraca rezerwację po id.
    - GET - localhost:8080/**pepsi**/**blue room**/reservation/cola - Zwraca rezerwację po nazwie rezerwującego.
    - DELETE - localhost:8080/**pepsi**/**blue room**/reservation/cola - Kasuje wszystkie rezerwacje rezerwującego po jego nazwie.
    - DELETE - localhost:8080/**pepsi**/**blue room**/reservation/id/1 - Kasuje rezerwację po id.
    
