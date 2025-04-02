# 🎛️ Twisk – Créateur de monde interactif en JavaFX

**Twisk** est une application Java avec interface graphique (JavaFX) permettant de créer et manipuler un monde composé d’activités, de transitions (arcs), de points de contrôle, d’entrées et de sorties.

---

## 🚀 Lancement de l'application

### Prérequis

- Java 11+ recommandé  
- JavaFX (si non inclus dans ton JDK)

### Compilation & exécution (ligne de commande)

```bash
javac -cp ".:path/to/javafx/lib/*" twisk/*.java twisk/mondeIG/*.java twisk/vues/*.java
java -cp ".:path/to/javafx/lib/*" --module-path path/to/javafx/lib --add-modules javafx.controls,javafx.fxml twisk.MainTwisk
```

💡 **Remplace `path/to/javafx/lib`** par le chemin réel vers tes libs JavaFX.

---

## 🧩 Structure du projet

```
twisk/
├── MainTwisk.java        # Point d’entrée (lance l’interface JavaFX)
├── mondeIG/              # Modèle logique du monde (activités, arcs, etc.)
│   └── MondeIG.java
├── vues/                 # Vue JavaFX (non fournie ici)
│   ├── VueMondeIG.java
│   ├── VueOutils.java
│   └── VueMenu.java
```

---

## 🛠️ Fonctionnalités principales

- Ajout d’activités dans un monde graphique
- Création d’arcs entre points de contrôle
- Définition des entrées/sorties
- Modification du nom, délai, écart, jetons, etc.
- Interface interactive avec menu et outils
