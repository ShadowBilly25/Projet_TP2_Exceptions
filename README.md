# Gestion des exceptions bancaires

Petit projet Java illustrant la gestion d'exceptions vérifiées (`checked exceptions`) lors d'un transfert entre deux comptes bancaires.

## Contenu

- `BankAccount` : représente un compte avec un numéro et un solde.
- `TransferService` : vérifie les règles métier d'un transfert, puis débite le compte source et crédite le compte destinataire.
- `BankingException` : exception bancaire générale, utilisée notamment si le montant est nul ou négatif.
- `InsufficientFundsException` : fonds insuffisants sur le compte source.
- `TransferLimitExceededException` : montant supérieur au plafond de 10 000.
- `InvalidAccountException` : compte manquant, numéro de compte invalide ou comptes source et destinataire identiques.

Les quatre exceptions sont des sous-classes directes de `Exception` :

```text
Exception
├── BankingException
├── InsufficientFundsException
├── TransferLimitExceededException
└── InvalidAccountException
```

## Prérequis

- Un JDK installé et la commande `javac` disponible dans le terminal.

Le projet ne contient pas de système de build ni de classe `main` ; il s'agit de classes Java à compiler et à utiliser depuis une application ou une classe de test.

## Compilation

Depuis le dossier du projet, compilez les fichiers avec :

```powershell
javac *.java
```

## Exemple d'utilisation

```java
BankAccount source = new BankAccount("FR001", 5000);
BankAccount destination = new BankAccount("FR002", 1000);
TransferService service = new TransferService();

try {
    service.transfer(source, destination, 250);
    System.out.println("Transfert effectué.");
} catch (BankingException
         | InsufficientFundsException
         | TransferLimitExceededException
         | InvalidAccountException e) {
    System.err.println("Transfert impossible : " + e.getMessage());
}
```

Les exceptions sont déclarées dans la signature de `transfer(...)` avec `throws`. L'appelant doit donc les gérer avec `try`/`catch` ou les déclarer à son tour.
