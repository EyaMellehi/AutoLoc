# Atelier 3 - Spring Data JPA Repository

## 1. Choix de l'interface Repository

Pour le projet AutoLoc, nous avons choisi d'utiliser
JpaRepository<Entite, Long> pour les neuf entités.

JpaRepository fournit :

- les opérations CRUD ;
- les méthodes retournant des List ;
- le tri ;
- la pagination ;
- les fonctionnalités spécifiques à JPA comme flush() et saveAndFlush().

Exemple :

```java
public interface IVehiculeRepository
        extends JpaRepository<Vehicule, Long> {
}
## Anomalies SonarQube for IDE

### Anomalie 1
**Fichier :** Agence.java

**Problème :**
Import inutilisé `java.math.BigDecimal`.

**Règle SonarQube :**
java:S1128 - Unused imports should be removed.

**Correction :**
Suppression de l'import `java.math.BigDecimal`.

---

### Anomalie 2
**Fichier :** AutolocApiApplication.java

**Problème :**
Import inutilisé `org.springframework.boot.CommandLineRunner`.

**Règle SonarQube :**
java:S1128 - Unused imports should be removed.

**Correction :**
Suppression de l'import `CommandLineRunner`.

---

### Anomalie 3
**Fichier :** AutolocApiApplication.java

**Problème :**
Import inutilisé `org.springframework.context.annotation.Bean`.

**Règle SonarQube :**
java:S1128 - Unused imports should be removed.

**Correction :**
Suppression de l'import `Bean`.

# TP DTO - AutoLoc

## 1. Objectif

L'objectif de ce TP est de ne plus exposer directement les entités JPA dans les endpoints REST.

Les DTO sont utilisés pour transférer uniquement les données nécessaires entre le backend et le client.

Architecture utilisée :

Controller -> DTO -> Service -> Mapper -> Entity -> Repository -> Base de données

---

## 2. DTO créés

- AgenceDTO
- EmployeDTO
- VehiculeDTO
- EquipementDTO
- ClientDTO
- ReservationDTO
- ContratDTO
- PaiementDTO
- MaintenanceDTO

---

## 3. Mappers

MapStruct est utilisé pour convertir les entités en DTO et inversement.

Exemple :

Agence -> AgenceDTO

AgenceDTO -> Agence

Les mappers utilisent :

@Mapper(componentModel = "spring")

---

## 4. Validation

La dépendance Spring Boot Validation Starter a été ajoutée.

Les principales annotations utilisées sont :

- @NotBlank
- @NotNull
- @Email
- @PositiveOrZero
- @Valid

Exemples :

- Le nom d'une agence ne doit pas être vide.
- L'adresse email d'un client doit avoir un format valide.
- Le tarif journalier d'un véhicule ne doit pas être négatif.
- La date de fin d'une réservation ne doit pas être antérieure à la date de début.

---

## 5. Gestion centralisée des erreurs

Une classe GlobalExceptionHandler a été créée avec :

@RestControllerAdvice

Elle permet de gérer les exceptions de tous les contrôleurs.

Exceptions gérées :

- ResourceNotFoundException -> HTTP 404
- IllegalArgumentException -> HTTP 400
- MethodArgumentNotValidException -> HTTP 400
- HttpMessageNotReadableException -> HTTP 400

---

## 6. Résultat

Les contrôleurs ne retournent plus directement les entités JPA.

Exemple avant :

public Agence findById(Long id)

Après :

public AgenceDTO findById(Long id)

Cela permet :

- de réduire les données transférées ;
- d'éviter d'exposer les relations JPA ;
- d'éviter certaines boucles JSON ;
- de séparer le modèle de base de données du modèle exposé par l'API.