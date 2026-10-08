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