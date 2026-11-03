# CONTRIBUTING

Her kan du læse, hvordan du kommer i gang med udviklingen.

## Kom i gang

1. Klon projektet fra GitHub.
2. Åbn projektet i IntelliJ.
3. Opret databasen `wishlist_db` i MySQL.
4. Kontrollér databaseindstillingerne.
5. Start applikationen.

## Projektstruktur

Projektet er opdelt i følgende mapper:

- **controller:** Håndterer requests fra brugeren.
- **model:** Indeholder klasserne Wish og WishList.
- **repository:** Håndterer databasen.
- **service:** Indeholder applikationens logik.
- **templates:** Indeholder HTML-sider.
- **static:** Indeholder CSS-filer.

## Git og samarbejde

Når du arbejder på en ny funktion:

1. Hent de nyeste ændringer fra `deploy`.
2. Opret en ny branch ud fra `deploy`.
3. Lav dine ændringer.
4. Commit og push din kode.
5. Opret en Pull Request til `deploy` på GitHub.
6. Få et andet gruppemedlem til at gennemgå ændringerne.

Vi bruger følgende branch-navne:

- `feature/` til nye funktioner.
  
`deploy`-branchen bruges som udgangspunkt for nye branches og samler godkendte ændringer.

## Retningslinjer

- Skriv tydelig og læsbar kode.
- Brug beskrivende navne til klasser og metoder.
- Følg projektets eksisterende struktur.
- Test dine ændringer, før du laver en Pull Request.
- Upload aldrig passwords eller andre følsomme oplysninger til GitHub.

