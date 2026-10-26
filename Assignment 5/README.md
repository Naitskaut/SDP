# Navigator — Strategy

Assignment #5 — Strategy Pattern
ShP-2216 Software Design Patterns, Astana IT University
by Abraimov Yeskendir, SE-2502

A navigator that plans the same trip in different ways: by car, on foot, by
bicycle or by public transport. Every way of travel is its own strategy behind
the `RouteStrategy` interface. `Navigator` holds one strategy, delegates to it,
and can switch it at runtime with `setStrategy()`. There is no `if`/`switch` on
the transport type anywhere.

## Strategies

| Strategy | Distance | Speed | Extra time | Cost |
|---|---|---|---|---|
| `CarStrategy` | ×1.3 (roads) | 40 km/h | +7 min parking | fuel, 0.08 l/km × 250 tg |
| `WalkStrategy` | ×1.0 (straight) | 5 km/h | — | free |
| `CycleStrategy` | ×1.1 (bike lanes) | 15 km/h | — | free |
| `PublicTransportStrategy` | ×1.2 (bus route) | 20 km/h | +6 min to stops, +10 min waiting | 120 tg |

`PublicTransportStrategy` was added last. `Navigator` and the other strategies
were not changed for it — `Main` only got one import and one `put` line.

## Pattern roles

| Role | Class |
|---|---|
| Strategy Interface | `RouteStrategy` — `buildRoute(Trip) : Route` |
| Concrete Strategies | `CarStrategy`, `WalkStrategy`, `CycleStrategy`, `PublicTransportStrategy` |
| Context | `Navigator` — `setStrategy()`, `planRoute(trip)` |
| Client | `Main` |

Also:
- `Trip` (from, to, distance) is the input of every strategy, `Route`
  (transport, distance, minutes, cost) is the result of every strategy
- `Main.availableStrategies()` is the only place that creates concrete
  strategies; the live demo picks one with `Map.get()`, not with a `switch`
- `NavigatorTest` — 8 tests without a framework, one concept per test

`Navigator.java` doesn't import any concrete strategy.

## UML

Full UML class diagram is in the report:
[`Assignment5_Report_Strategy_Navigator.md`](Assignment5_Report_Strategy_Navigator.md)
(PDF: [`Assignment5_Report_Strategy_Navigator.pdf`](Assignment5_Report_Strategy_Navigator.pdf))

```
        Main (client) ── creates ──> Car / Walk / Cycle / PublicTransport
             │ uses
             ▼
         Navigator ◇──── strategy ────> «interface» RouteStrategy
                                                 △
              ┌──────────────┬───────────────────┼──────────────────┐
         CarStrategy    WalkStrategy      CycleStrategy   PublicTransportStrategy
```

## Project structure

```
src/
 ├── Strategy/
 │    ├── RouteStrategy.java            # Strategy interface
 │    ├── CarStrategy.java              # Concrete Strategy
 │    ├── WalkStrategy.java             # Concrete Strategy
 │    ├── CycleStrategy.java            # Concrete Strategy
 │    ├── PublicTransportStrategy.java  # Concrete Strategy (added last)
 │    ├── Navigator.java                # Context
 │    ├── Trip.java                     # input of a strategy
 │    └── Route.java                    # result of a strategy
 └── Main.java                          # Client + live demo
test/
 └── NavigatorTest.java                 # 8 tests, run with main()
```

## Running

IntelliJ IDEA: open the root `SDP` project → `Assignment 5/src/Main.java` → Run.
`Assignment 5/test/NavigatorTest.java` runs the same way.

Terminal:

```bash
cd "Assignment 5"
javac --release 17 -encoding UTF-8 -d out $(find src test -name "*.java")
java -cp out Main
java -cp out NavigatorTest
```

## Example output

`Main` (the live demo input was `walk`, `bus`, `plane`, `exit`):

```
=== AITU -> Baiterek, strategy switched at runtime ===
Car                 7.8 km    19 min    156 tg
Walk                6.0 km    72 min      0 tg
Bicycle             6.6 km    26 min      0 tg
Public transport    7.2 km    38 min    120 tg

Validation works: Route strategy must not be null

=== Live demo ===
Choose transport [car, walk, cycle, bus] or exit: walk
Walk                3.5 km    42 min      0 tg
Choose transport [car, walk, cycle, bus] or exit: bus
Public transport    4.2 km    29 min    120 tg
Choose transport [car, walk, cycle, bus] or exit: plane
Unknown transport: plane
Choose transport [car, walk, cycle, bus] or exit: exit
```

`NavigatorTest`:

```
PASS  car adds road detour, parking and fuel
PASS  walking is straight and free
PASS  cycling is faster than walking
PASS  public transport adds waiting and fare
PASS  navigator uses the strategy it was switched to
PASS  navigator works with any new strategy
PASS  navigator rejects null strategy
PASS  trip rejects non-positive distance

8 passed, 0 failed
```

## Clean Code notes

Five principles from *Clean Code* chapters 9, 10 and 11 are explained with
before/after excerpts in the report:

- small classes, one responsibility — one algorithm per strategy (Ch. 10)
- organizing for change — no conditionals, a new strategy without editing
  existing classes (Ch. 10)
- isolating from change — `Navigator` depends only on the `RouteStrategy`
  interface (Ch. 10)
- separate constructing a system from using it, dependency injection (Ch. 11)
- clean tests — F.I.R.S.T., one concept per test, BUILD-OPERATE-CHECK (Ch. 9)
