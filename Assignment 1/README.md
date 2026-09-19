# CarBuilder (Assignment 1)

A Java implementation of the Builder design pattern that constructs vehicle
configurations — and a matching text specification for that vehicle — step
by step, without constructor overloading.

## Architecture

The project decouples the construction of a complex `Car` object from its
representation using the Builder pattern:

- **Builder** (`CarBuilder.java`): Common interface specifying the
  configuration steps (`reset`, `setType`, `setEngine`, `setTransmission`,
  `setSeats`), each returning the builder itself for fluent chaining.
- **Concrete Builders**:
  - `CarPartsBuilder`: Assembles the real `Car` product, validating the
    configuration before returning it.
  - `CarManualBuilder`: Assembles a plain-text `CarManual` describing the
    same configuration, using the exact same sequence of steps.
- **Director** (`CarDirector.java`): Defines standard build recipes
  (`makeSportCar`, `makeFamilyCar`, `makeCityCar`), reusable across either
  concrete builder.
- **Products**: `Car` and `CarManual`.
- **Components**: Supporting types used during assembly — `CarType`,
  `Transmission` (enums), `Engine` (interface implemented by `SportEngine`
  and `StandardEngine`), and `CarSeats` (named seat-count limits).

## Tech Stack

- **Language**: Java
- **Frameworks/Libraries**: None (Pure Java Standard Library)
- **Build/Run**: `javac` CLI or IntelliJ IDEA

## Project Structure

```text
Assignment 1/
└── src/
    └── carbuilder/
        ├── CarBuilder.java        # Builder interface
        ├── CarPartsBuilder.java   # Concrete builder -> Car
        ├── CarManualBuilder.java  # Concrete builder -> CarManual
        ├── CarDirector.java       # Recipes: sport / family / city car
        ├── Car.java               # Car product
        ├── CarManual.java         # Text manual product
        ├── CarType.java           # SUV / SPORT / CITY
        ├── Transmission.java      # MANUAL / SEMI_AUTOMATIC / AUTOMATIC
        ├── Engine.java            # Engine interface
        ├── SportEngine.java       # Engine implementation
        ├── StandardEngine.java    # Engine implementation
        ├── CarSeats.java          # MIN_SEATS / MAX_SEATS constants
        └── Main.java              # Entry point
```

## Quickstart

### Option 1: IntelliJ IDEA (Recommended)

1. Open the root `SDP` project in IntelliJ IDEA.
2. Navigate to `Assignment 1/src/carbuilder/Main.java`.
3. Click the green Run arrow next to `public static void main`.

### Option 2: Terminal / CLI

From the root of the repository:

```bash
# 1. Navigate to module directory
cd "Assignment 1"

# 2. Compile Java sources to an out directory
javac -d out src/carbuilder/*.java

# 3. Run the application
java -cp out carbuilder.Main
```

## Usage & Example Output

The client uses `CarDirector` with different builders to produce either an
actual `Car` object or its printed manual from the same recipe:

```java
CarDirector director = new CarDirector();

// Build a sports car
CarPartsBuilder carBuilder = new CarPartsBuilder();
director.makeSportCar(carBuilder);
Car car = carBuilder.getResult();
System.out.println(car);

// Build the manual for the same car
CarManualBuilder manualBuilder = new CarManualBuilder();
director.makeSportCar(manualBuilder);
CarManual manual = manualBuilder.getResult();
System.out.println(manual);
```

Expected output:

```text
Car -> type=SPORT, engine=Sport engine, 8.0L, 5000 km, transmission=MANUAL, seats=2

---User Manual---
- Car Type: SPORT
- Car engine: Sport engine, 8.0L
- Car transmission: MANUAL
- Seats: 2
```

`Notification.java` also demonstrates building a `SUV` and a `CITY` car through
`CarDirector`, assembling a fully custom car via direct chaining (no
Director), and a validation failure case (an out-of-range seat count throws
`IllegalStateException` instead of producing a broken `Car`).



