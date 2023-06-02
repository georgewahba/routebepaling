# getting started

Volg deze stappen om de routebepaling applicatie werkend te krijgen bij jou.

## Installation

Zorg dat je xampp/mamp aan staat.

Zorg dat je laravel op je pc hebt staan en dat het werkt(voor de laravel applicatie)

Zorg dat je .env bestand goed is ingesteld voor jou database connectie (database name, host, wachtwoord enz.)(voor de laravel applicatie)

Zorg dat je een java applicatie can builden en dat je een driver hebt geinstalleerd voor je database

## Usage

ga in de folder van het laravel project(genaamd laravel applicatie) en open je terminal en voer de onderstaande stappen uit
```bash
composer install
npm install
npm run dev
```
nu moeten alle composer bestanden gedownload zijn.

doe nu

```bash
php artisan migrate::fresh --seed
php artisan serve
```

nu moet de server aan staan en de database gevuld zijn met dummy data, dit is de manager applicatie waar de manager de adressen kan koppelen aan de bezorgers.

ga nu naar de java applicatie(genaamd Routebepaling_scherm.java)
op lijn 16 wordt de database connectie gecreeerd, zorg ervoor dat de insellingen juist zijn met jouw locale machine.
druk rechts boven op het hamertje om de applicatie te builden. er komt een login scherm naar voren waar u als test het volgende account kunt gebruiken userame:admin wacht:admin