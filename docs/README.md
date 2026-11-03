# WishList – Digital Ønskeseddel

## Om projektet

WishList er en webbaseret applikation, hvor brugere kan oprette og administrere digitale ønskelister. Formålet er at gøre det nemmere at samle sine ønsker ét sted og holde styr på information om hvert ønske.

Applikationen giver mulighed for at oprette, redigere og slette ønskelister og ønsker. Et ønske kan blandt andet indeholde navn, beskrivelse, pris og et link til produktet.

## Teknologier

Projektet er udviklet med følgende teknologier:

- **Java 25** – Programmeringssprog.
- **Spring Boot 4.1.1** – Backend-framework.
- **Spring MVC** – Håndtering af HTTP-requests og controllers.
- **Thymeleaf** – Dynamisk generering af HTML-sider.
- **Spring JDBC / JdbcTemplate** – Kommunikation mellem applikationen og databasen.
- **MySQL** – Relationel database.
- **HTML og CSS** – Applikationens brugergrænseflade.
- **Maven** – Håndtering af dependencies og projektets build.

## Softwaremæssige forudsætninger

For at kunne køre applikationen lokalt skal følgende være installeret:

- Java Development Kit (JDK) 25.
- Maven.
- MySQL Server.
- En IDE, eksempelvis IntelliJ IDEA.
- Git til at hente projektet fra GitHub.

## Installation og opstart

1. Klon projektet fra GitHub:

   ```bash
   git clone [INDSÆT GITHUB-REPOSITORY-LINK]
   ```

2. Åbn projektet i IntelliJ IDEA.

3. Opret en MySQL-database med navnet `wishlist_db`.

4. Kontrollér databaseforbindelsen i `application-dev.properties` eller den relevante konfigurationsfil. Indstil database-URL, brugernavn og adgangskode.

5. Åbn projektet og vent på, at Maven har hentet alle dependencies.

6. Start applikationen ved at køre `WishListGruppe8Application.java`.

7. Åbn applikationen i browseren:

   http://localhost:8080

## Database

Applikationen anvender en MySQL-database med følgende centrale tabeller:

- **wishlist** – Indeholder information om ønskelister.
- **wish** – Indeholder de enkelte ønsker, som er knyttet til en ønskeliste.

Tabellen `wish` har en fremmednøgle, der refererer til `wishlist`.

## Kørende applikation

Link til den deployede applikation:

[INDSÆT LINK TIL DEN KØRENDE APPLIKATION]

## Projektgruppe

Udviklet som en del af Datamatikeruddannelsen på Erhvervsakademi København (EK).
