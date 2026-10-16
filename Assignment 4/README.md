# Home Theater — Facade

Assignment #4 — Facade Pattern
ShP-2216 Software Design Patterns, Astana IT University
by Abraimov Yeskendir, SE-2502

A home theater with 6 devices: amplifier, DVD player, projector, screen,
lights and popcorn popper. To start a movie by hand you need 13 calls in the
right order, and 8 more to turn everything off. `HomeTheaterFacade` hides all
of this behind `watchMovie()` and `endMovie()`.

## Why the order matters

The devices check each other, so you can't call them in any order:

- the amplifier and the projector don't accept a DVD player that is still off,
  so the DVD player has to be turned on first
- volume, popcorn and widescreen mode work only after the device is on
- if the projector lamp is burned out, the projector won't start, and the
  facade turns off everything it already turned on

## Pattern roles

| Role | Class |
|---|---|
| Facade | `HomeTheaterFacade` — `watchMovie(title)`, `endMovie()` |
| Complex Subsystem | `Amplifier`, `DvdPlayer`, `Projector`, `Screen`, `TheaterLights`, `PopcornPopper` |
| Client | `Main` |

Also:
- `HomeTheaterInstaller` creates all devices and gives `Main` a ready facade,
  so `Main` doesn't create any device by itself
- `HomeTheaterException` is the only error the facade throws
- `AdvancedClient` uses the devices directly without the facade (volume 11,
  which the facade never sets), and shows what happens when the projector is
  broken

Devices don't know that the facade exists — nothing in `Subsystem/` imports
`Facade`.

## UML

Full UML class diagram is in the report:
[`Assignment4_Report_Facade_Home_Theater.pdf`](Assignment4_Report_Facade_Home_Theater.pdf)

```
          Main (client)
               │ uses only
               ▼
       HomeTheaterFacade
               │
               ▼
 ┌────────── Subsystem ──────────┐
 │ Amplifier  DvdPlayer          │
 │ Projector  Screen             │
 │ TheaterLights  PopcornPopper  │
 └───────────────────────────────┘
```

## Project structure

```
src/
 ├── Facade/
 │    ├── HomeTheaterFacade.java     # Facade
 │    ├── HomeTheaterInstaller.java  # creates devices, gives out the facade
 │    └── HomeTheaterException.java
 ├── Subsystem/
 │    ├── Amplifier.java, DvdPlayer.java, Projector.java
 │    └── Screen.java, TheaterLights.java, PopcornPopper.java
 ├── Main.java                       # Client
 └── AdvancedClient.java             # devices without the facade
```

## Running

IntelliJ IDEA: open the root `SDP` project → `Assignment 4/src/Main.java` → Run.
`AdvancedClient.java` runs the same way.

Terminal:

```bash
cd "Assignment 4"
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out Main
java -cp out AdvancedClient
```

## Example output

`Main`:

```
Get ready to watch "Interstellar"...
Popcorn Popper on
Popcorn Popper popping popcorn!
Theater Lights dimming to 10%
Screen going down
DVD Player on
Projector on
Projector input set to DVD Player
Projector in widescreen mode (16:9)
Amplifier on
Amplifier input set to DVD Player
Amplifier volume set to 5
DVD Player playing "Interstellar"

Shutting movie theater down...
Popcorn Popper off
Theater Lights on
Screen going up
Projector off
Amplifier off
DVD Player stopped "Interstellar"
DVD Player ejected "Interstellar"
DVD Player off
```

`AdvancedClient`:

```
=== Using devices without the facade ===
DVD Player on
Amplifier on
Amplifier input set to DVD Player
Amplifier volume set to 11
Amplifier off
DVD Player off

=== Projector is broken ===
Get ready to watch "Dune"...
Popcorn Popper on
Popcorn Popper popping popcorn!
Theater Lights dimming to 10%
Screen going down
DVD Player on
Something went wrong, turning everything off...
Shutting movie theater down...
Popcorn Popper off
Theater Lights on
Screen going up
DVD Player off
Error: Could not start "Dune": Projector lamp is burned out
```

## Clean Code notes

Five principles from *Clean Code* chapters 6, 7 and 8 are explained with
before/after excerpts in the report:

- objects hide their data and expose behavior (Ch. 6)
- the Law of Demeter — the client talks only to the facade (Ch. 6)
- exceptions instead of return codes, unchecked exceptions (Ch. 7)
- the try block as a transaction: rollback, context in the message, one
  exception type for the caller (Ch. 7)
- clean boundaries — only the facade and the installer refer to the devices
  (Ch. 8)
