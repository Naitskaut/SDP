# Notification & Branding Service — Bridge

Assignment #3 — Bridge Pattern
ShP-2216 Software Design Patterns, Astana IT University
by Abraimov Yeskendir, SE-2502

My Notification & Branding Service from Assignment 2, refactored with the
Bridge pattern. Messages are styled by a brand (Corporate or Marketing/Promo)
and delivered over a channel (Email, SMS, Push). Brand and channel are now two
independent hierarchies, and the channel can be changed at runtime.

## What changed since Assignment 2

In Assignment 2 the sender was bound to its channel by **inheritance**:
`EmailNotificationSender`, `SmsNotificationSender`, `PushNotificationSender`.
The brand was applied separately in `main`, by hand, before calling
`dispatch()`. If the sender also had to know the brand, inheritance would
need a class for every pair — `CorporateEmailSender`, `PromoSmsSender`, ... —
brands × channels.

Now the two dimensions are connected by **composition**:

- `NotificationSender` (brand side) holds a `Notification` (channel side) —
  this field is the bridge
- `CorporateNotificationSender` / `PromoNotificationSender` decide how the
  message looks (using the Abstract Factory from Assignment 2)
- `EmailNotification` / `SmsNotification` / `PushNotification` decide how it
  is delivered
- `setNotification()` switches the channel at runtime

2 brands × 3 channels = 6 combinations with 2 + 3 classes instead of 6.

Other fixes:
- `dispatch()` in Assignment 2 created the `Notification` but never called
  `send()` — now delivery really goes through the channel
- channels now have their own rules: SMS is one line (max 160 chars), Push is
  a short preview (max 40 chars), Email shows everything
- the box output was moved to `MessageBox` so it is shared by all channels
- `main.java` → `Main.java` (class names start with a capital letter)

## Pattern roles

| Role | Class |
|---|---|
| Abstraction | `NotificationSender` |
| Refined Abstraction | `CorporateNotificationSender`, `PromoNotificationSender` |
| Implementor | `Notification` |
| Concrete Implementor | `EmailNotification`, `SmsNotification`, `PushNotification` |
| Client | `Main` |

**Abstract Factory** (unchanged from Assignment 2) — `BrandFactory`,
`CorporateBrandFactory`, `MarketingPromoFactory` and their products. The
refined abstractions use `AbstractFactory.Client` to build the styled text.

## UML

Full UML class diagram is in the report:
[`Assignment3_Report_Bridge_Notification_Service.pdf`](Assignment3_Report_Bridge_Notification_Service.pdf)

```
   NotificationSender  ◇──── bridge ────>  «interface» Notification
          △                                         △
   ┌──────┴───────┐                    ┌────────────┼────────────┐
Corporate…     Promo…           EmailNotification  SmsNotification  PushNotification
   └──────┬───────┘
  AbstractFactory.Client
```

## Project structure

```
src/
 ├── Bridge/
 │    ├── NotificationSender.java           # Abstraction
 │    ├── CorporateNotificationSender.java  # Refined Abstraction
 │    ├── PromoNotificationSender.java      # Refined Abstraction
 │    ├── Notification.java                 # Implementor
 │    ├── EmailNotification.java            # Concrete Implementor
 │    ├── SmsNotification.java              # Concrete Implementor
 │    ├── PushNotification.java             # Concrete Implementor
 │    ├── MessageBox.java                   # shared box output for channels
 │    └── ChannelLimits.java                # SMS / Push limits
 ├── AbstractFactory/                       # from Assignment 2, unchanged
 │    ├── BrandFactory.java, CorporateBrandFactory.java, MarketingPromoFactory.java
 │    ├── MessageHeader.java, MessageBodyFormatter.java, ActionButton.java
 │    ├── Corporate*.java, Promo*.java
 │    └── Client.java
 └── Main.java                              # Client + live demo
```

## Running

IntelliJ IDEA: open the root `SDP` project → `Assignment 3/src/Main.java` → Run.

Terminal:

```bash
cd "Assignment 3"
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out Main
```

## Example output

```
=== Promo sender, channel switched at runtime ===

┌─ SMS ──────────────────────────────
│ To: Yeska
│ Hey there! 🎉 Hello 🔥 Don't miss out! 🔥 Shop Now!(link)
└─────────────────────────────────

┌─ PUSH ──────────────────────────────
│ To: Yeska
│ Hey there! 🎉 Hello 🔥 Don't miss out...
└─────────────────────────────────

=== Corporate sender, channel switched at runtime ===

┌─ EMAIL ──────────────────────────────
│ To: Amir
│ Dear Customer,
│ Whats up?
│
│ Best regards,
│ The Team
│ Learn More
└─────────────────────────────────

┌─ SMS ──────────────────────────────
│ To: Amir
│ Dear Customer, Whats up? Best regards, The Team Learn More
└─────────────────────────────────

=== 2 brands x 3 channels, without 6 subclasses ===
...
Validation works: Notification channel must not be null

=== Live demo ===
Choose channel [email, sms, push] or exit:
```

In the live demo you type `email`, `sms` or `push` and the same sender object
re-sends the message through the chosen channel.

## Clean Code notes

Five principles are explained with before/after excerpts in the report:
separation of abstraction and implementation responsibilities, meaningful
role names, small focused classes, no duplicated logic between channels
(`MessageBox`, `ChannelLimits`), and a backward-compatible design (a new
channel needs no change in `NotificationSender` or its subclasses).
