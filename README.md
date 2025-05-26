# Wymagania

- Java 21
- Maven 3.6.3

# Inteligentne światła drogowe

## Opis projektu

Projekt przedstawia **symulację inteligentnych świateł drogowych** na skrzyżowaniu z czterema drogami dojazdowymi (północ, południe, wschód, zachód).
System dynamicznie dostosowuje cykle świateł w zależności od aktualnego natężenia ruchu, bazując na strategii adaptacyjnej. Zaimplementowana są również dwie strategie
zmieniająca światła co każdy krok symulacji. Zaimplementowany jest system pasów ruchu (pare pasów na jednej drodze dojazdowej do skrzyżowania).
Każdy kierunek docelowy ma własne światła, co daje możliwość implementacji ciekawych strategii zmian świateł (Np taki w którym konflikty nie mogą nastąpić, tylko to na skrzyżowaniu z więcej niż jednym pasem ruchu.
A w treści jest zapisane że ma być jeden, poza tym znacząco zmieniło by to wyniki). Zaimplementowany jest rozbudowany pakiet testów pokrywających kluczowe funkcjonalności.

## Główne założenia

System:
- Modeluje skrzyżowanie z czterema drogami dojazdowymi
- Symuluje cykle świateł (zielone, żółte, czerwone)
- Unika sytuacji konfliktowych (np. kolidujących zielonych świateł)
- Dostosowuje długość zielonego światła w oparciu o liczbę pojazdów oczekujących
- Pozwala na wczytanie sekwencji komend z pliku JSON
- Generuje plik wynikowy z informacją o pojazdach, które opuściły skrzyżowanie w ramach każdego kroku symulacji.

## Struktura projektu

```
    src/
    ├── main/
    │   ├── java/
    │   │   └── org/example/
    │   │       ├── io
    │   │       │   ├── DataLoader.java
    │   │       │   └── DataSaver.java
    │   │       ├── model
    │   │       │   ├── command
    │   │       │   │   ├── AddVehicle.java
    │   │       │   │   ├── Command.java
    │   │       │   │   └── Step.java
    │   │       │   ├── intersection
    │   │       │   │   └── Intersection.java
    │   │       │   ├── light
    │   │       │   │   ├── Signal.java
    │   │       │   │   └── TrafficLight.java
    │   │       │   ├── loader
    │   │       │   │   ├── CommandListWrapper.java
    │   │       │   │   ├── StepStatus.java
    │   │       │   │   └── StepStatuses.java
    │   │       │   ├── road
    │   │       │   │   ├── Direction.java
    │   │       │   │   ├── Lane.java
    │   │       │   │   ├── OneLaneTwoWayRoad.java
    │   │       │   │   └── Road.java
    │   │       │   ├── vehicle
    │   │       │   │   ├── Vehicle.java
    │   │       │   │   └── VehicleStatus.java
    │   │       │   ├── IntersectionState.java
    │   │       │   └── Route.java
    │   │       ├── simulation
    │   │       │   ├── strategy
    │   │       │   │   ├── AdaptiveStrategy.java
    │   │       │   │   ├── EveryTickToggleStrategy.java
    │   │       │   │   └── TrafficLightStrategy.java
    │   │       │   └── VehicleManager.java
    │   │       └── Main.java
    │   └── resources/
    │       ├── input_data.json
    │       └── output_data.json
    └── test
        └── java
            ├── model
            │   ├── intersection
            │   │   └── IntersectionTest.java
            │   ├── light
            │   │   └── TrafficLightTest.java
            │   └── RouteTest.java
            └── simulation
                ├── strategy
                │   ├── AdaptiveStrategyTest.java
                │   └── EveryTickToggleStrategyTest.java
                ├── VehicleManagerTest.java
```

## Uruchomienie

```
    mvn compile exec:java -Dexec.args="<full_input_filepath.json> <full_output_filepath.json>"
```

## Format wejścia (input.json)

```json
{
  "commands": [
    {
      "type": "addVehicle",
      "vehicleId": "vehicle1",
      "startRoad": "south",
      "endRoad": "north"
    },
    {
      "type": "step"
    }
  ]
}
```

## Format wyjścia (output.json)

```json
{
  "stepStatuses" : [ 
    {
      "leftVehicles" : [ "vehicle2", "vehicle1" ]
    },
    {
      "leftVehicles" : [ ]
    },
    {
      "leftVehicles" : [ "vehicle3" ]
    },
    {
      "leftVehicles" : [ "vehicle4" ]
    }
  ]
}
```

## Algorytm sterowania światłami

### Strategia adaptacyjna (AdaptiveStrategy)

- Analizuje liczbę pojazdów na każdym kierunku
- Wybiera kierunek z największym natężeniem ruchu
- Dostosowuje czas zielonego światła proporcjonalnie do natężenia
- Gwarantuje minimalny czas zielonego światła (minGreenTime)
- Zapobiega przesterowaniu (maxGreenTime)
- Uwzględnia czas przejściowy (transitionTime) między fazami

#### Parametry konfiguracyjne

- minGreenTime: Minimalny czas zielonego światła
- maxGreenTime: Maksymalny czas zielonego światła
- transitionTime: Czas żółtego światła
- vehicleWeight: Waga pojedynczego pojazdu

### Strategia zmiany co tick (EveryTickToggleStrategy)

- Nie analizuje liczby pojazdów
- Co tick zmienia wszystkie światła
- Na każdym zielonym może przejechać jeden samochód z jednego pasa ruchu

## Testowanie

```
    mvn clean test
```

## Możliwości rozbudowy

- Inny system przekierowywania pojazdów na liniach jazdy
- Inne strategie zmiany świateł
