# Factory.Notification & Branding Service

Assignment #2 — Factory Method & Abstract Factory
ShP-2216 Software Design Patterns, Astana IT University
by Abraimov Yeskendir, SE-2502

A small Java demo where notifications are sent over different channels (Email, SMS, Push)
and styled according to a brand identity (Corporate or Marketing/Promo) before sending.

## Patterns used

**Factory Method** — picks which channel delivers a message.
- Product: `Notification`
- Concrete Products: `EmailNotification`, `SmsNotification`, `PushNotification`
- Creator: `NotificationSender` (holds the shared `dispatch()` logic)
- Concrete Creators: `EmailNotificationSender`, `SmsNotificationSender`, `PushNotificationSender`

**Abstract Factory** — produces a matching set of brand-styled message parts.
- Abstract Products: `MessageHeader`, `MessageBodyFormatter`, `ActionButton`
- Concrete Products: `Corporate*` / `Promo*` implementations of each
- Abstract Factory: `BrandFactory`
- Concrete Factories: `CorporateBrandFactory`, `MarketingPromoFactory`
- AbstractFactory.Client: `Client` — builds the final message text from a `BrandFactory`, without ever
  instantiating a concrete product itself

## How it fits together

```
AbstractFactory.BrandFactory → AbstractFactory.Client.compose(message) → String (styled text)
                                              ↓
                          Factory.NotificationSender.dispatch(recipient, styledText)
```

`Client` and `NotificationSender` don't know about each other — `Client` only produces a
`String`, and `NotificationSender` only knows how to deliver one.

## Project structure

```
src/
 ├── notification/
 │    ├── Factory.Notification.java
 │    ├── Factory.EmailNotification.java
 │    ├── Factory.SmsNotification.java
 │    ├── Factory.PushNotification.java
 │    ├── Factory.NotificationSender.java
 │    ├── Factory.EmailNotificationSender.java
 │    ├── Factory.SmsNotificationSender.java
 │    └── Factory.PushNotificationSender.java
 ├── branding/
 │    ├── AbstractFactory.MessageHeader.java
 │    ├── AbstractFactory.CorporateHeader.java
 │    ├── AbstractFactory.PromoHeader.java
 │    ├── AbstractFactory.MessageBodyFormatter.java
 │    ├── AbstractFactory.CorporateBodyFormatter.java
 │    ├── AbstractFactory.PromoBodyFormatter.java
 │    ├── AbstractFactory.ActionButton.java
 │    ├── AbstractFactory.CorporateActionButton.java
 │    ├── AbstractFactory.PromoActionButton.java
 │    ├── AbstractFactory.BrandFactory.java
 │    ├── AbstractFactory.CorporateBrandFactory.java
 │    ├── AbstractFactory.MarketingPromoFactory.java
 │    └── AbstractFactory.Client.java
 └── Main.java
```

## Running

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Example output

```
┌─ SMS ──────────────────────────────
│ To: Yeska
│ 🎉 Hey there!
│ 🎉 Hello 🔥 Don't miss out!
│ 🔥 Shop Now!
└─────────────────────────────────

┌─ EMAIL ──────────────────────────────
│ To: Amir
│ Dear Customer,
│ Whats up?
│
│ Best regards,
│ The Team
│ Learn More
└─────────────────────────────────

┌─ PUSH ──────────────────────────────
│ To: AGAG
│ WEWESES
└─────────────────────────────────
```

## Clean Code notes

Five principles from *Clean Code* (Ch. 3 & 6) are applied and documented with before/after
excerpts in the report: command–query separation, meaningful names, no duplicated logic
(shared `dispatch()` in `NotificationSender`), no conditional/type-checking logic in client
code, and single responsibility (sending vs. branding kept in separate classes).

See the full report for details and UML class diagrams.