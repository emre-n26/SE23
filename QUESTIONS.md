# Klausurfragen

### Name und Matrikelnummer 
^-- Bitte eintragen und 1. Commit pushen, 2. Commit spätestens nach 30 Minuten pushen, sonst Punktabzug!
Alan Hussein, s0583689
## Fragen zum Code (je 6 Punkte)

[1] Listen Sie alle Vue-Komponenten im Projekt auf, die Props definieren und/oder Vue-Lifecycle-Funktionen (lifecycle hooks) implementieren.
    Welche Komponente definiert welche Props und welchen Lifecycle Hook?

- AdminTable.vue
 1. props: title
- lifecyclehook: created
  1. ChuckNorrisQuote.vue
  2. lifecyclehook: mounte

[2] Wo kommen die Daten her, mit denen die Tabelle in `AdminTable.vue` mittels `v-for` befüllt wird (Zeile 22) und wie kommen sie dort hin?
    Nennen Sie Stellen in mindestens drei unterschiedlichen Dateien, vom Ursprung der Daten bis hin zur Darstellung im Frontend.

- Daten kommen aus der variable quotes[], welche durch die methode loadQuoates() durch ine Abfrage an das Backend befüllt werden
  1. ChuckNorrisQuote.vue sendet Abfrage an Backend, Zeile 46
  2. QuoteController.java nimmt Anfrage entgegen, Zeile 25
  3. QuoteController.java ruft QuoteServiceImpl.java auf, Zeile 28
  4. QuoteServiceImlp.java nimmt Quote aus Liste, Zeile 26

[3] Wenn man auf der Hauptseite (die mit Chuck Norris) auf den Link `Admin Console` klickt, wird die Seite gewechselt und oben der Titel "Hier kannst Du Sprüche hinzufügen, bewerten ..." angezeigt.
    Nennen Sie Stellen in mindestens drei Dateien, die diesen Seitenwechsel und dann die Darstellung dieses Titels implementieren.

- App.vue, Zeile 4, router-link
- router/index.js, Zeile 11 folgende, route zu der Seite
- AdminTable.vue, Zeile 2, überschrift Element

[4] Wo im Projekt wird definiert, auf welchem Port das Frontend im Entwicklungsmodus geserved wird?
    Wo wird definiert, dass das Frontend auf das Backend zugreifen darf, obwohl es von einem anderen 'Ort' ausgeliefert wurde als das Backend?
    Wie nennt man die Technik für eine solche Erlaubnis?

- Das Port wird in vue.config.js definiert
- Die Regeln von cors werden in config/WebConfig.java gesetzt, Zeile 17
- Die Technik nennt man CORS


[5] Was müssten Sie in diesem Projekt hinzufügen, um eine Datenbank-Verbindung mittels JPA herzustellen und Objekte der Klasse `QuoteResponse` darin speichern zu können?
    Nennen Sie Änderungen an mindestens drei Dateien.

- application.properties sollte alle notwendigen Konfigurationen enthalten, um den richtigen Driver usw. zu verwenden
- Klasse VotableQuote.java müsste jpa Annotation bekommen
- Es muss ein QuotesRepository.java angelegt werden, welches ein CRUDRepository oder ein JPARepository implementiert.

## Theoretische Fragen (je 3 Punkte) - Bitte maximal drei Sätze pro Antwort, sonst Punktabzug!

[1] Wie lautet der Name der Datei, die üblicherweise den Kern der Projektdefinition eines mit NPM gemanagten Projekts enthält?
    Was wird in dieser Datei vor allem definiert?

- package.json
- Name und Autor des Projekts
- abhängigkeiten des Projekts und scripte die benutzt werden können

[2] Was ist der Unterschied zwischen den HTTP Error Codes der 4xx-Kategorie und den Error Codes der 5xx-Kategorie?

-  Die HTTP-Fehlercodes der 4xx-Fehler sind Client-Fehler, die auf Probleme in der Anfrage oder
   Aktion des Clients hinweisen, während 5xx-Fehler Server-Fehler sind, die auf Probleme auf
   der Serverseite hinweisen.

[3] Wofür benutzt man CSS-Selectors? Nennen Sie mindestens 3 Beispiele unterschiedlicher CSS-Selektoren.

- CSS-Selektoren werden verwendet, um die HTML-Elemente zu finden oder auszuwählen, die Sie formatieren möchten.
- Beispiel:
  1.Klassenselektor: Beispiel: .button { background-color: #FF5733; }
  2.Elementselektor: Beispiel: p { color: blue; }
  3.ID-Selektor: Beispiel: #header { font-size: 20px; }

[4] Definieren Sie in JavaScript ein Objekt, welches ein Attribut `attrA` mit dem Wert `a` und ein Attribut `attrB` mit dem Wert `42` beinhaltet.
```js
    const obj = {
    a: 'a',
    b: 42
    }
- ```

[5] Illustrieren Sie den Unterschied zwischen Path-Parameter und Query-Parameter (auch Request-Parameter genannt): 
    Wie sieht jeweils (also einmal als Path-Parameter und einmal als Query-Parameter) die Request-URL aus, wenn an 
    die Route `shop.com/products` eine Anfrage mit einem Attribut `id` und einem dazugehörigen Wert `42` gesendet werden soll?

- Der Path-Parameter: Bei der Verwendung von Path-Parametern werden diese direkt in die URL-Pfadstruktur /products/42 eingebettet,
  häufig gekennzeichnet durch Doppelpunkte oder geschweifte Klammern.
- Der Query-Parameter: Bei der Verwendung von Query-Parametern werden diese an die URL
  ?id=42 angehängt und durch ein Fragezeichen von der Basiskomponente (dem Pfad)
  getrennt.
- 
- Path-Parameter: shop.com/products/42
- Query-Parameter: shop.com/products?id=42

[6] Wofür wird das JSON-Format im Kontext von Web-Entwicklung häufig verwendet?

- Datenübertragung: Die Daten werden zwischen einem Server und einem Client im Browser auszutauschen.
- Konfiguration und Speicherung: Es wird oft für die Konfiguration von Anwendungen verwendet.
- Frontend- und Backend-Kommunikation: für die Kommunikation zwischen dem Frontend und backend in einer Webanwendung wird es auch verwendet.

[7] Java-Programme werden getypechecked und kompiliert. Was sind die äquivalenten Schritte bzw. Tools im Build-Prozess eines JavaScript-Programms?

- Transpilation: JavaScript-Code wird in eine ältere Version von JavaScript transpiriert, die von
  den älteren Browsern verstanden wird.
- Testen: JavaScript-Anwendungen verwenden häufig Unit-Tests und Integrationstests, um
  sicherzustellen, dass der Code wie erwartet funktioniert.
- Abhängigkeitsverwaltung: Ein Paketmanager wie npm wird verwendet, um Abhängigkeiten zu verwalten.
- Minifizierung: Der JavaScript-Code wird oft minifiziert, um die Dateigröße zu reduzieren und
  die Ladezeiten zu verbessern.

[8] Nennen Sie mind. drei Schritte, die erfolgen müssen, um ein Spring-Boot-Programm automatisiert zu deployen und somit öffentlich im Web verfügbar zu machen? 

- automatisiertes buildern über eine workflow
- Auf einem Server ein Script mit einer hook der auf ein erfolgreiches build reagiert, oder einen
  cron job, der automatisiert nach x Zeit den letzten build holt
- Ein Hook, der anschließend auch das Programm startet
- oder einen Reverse Proxy oder ähnliches, um den Dienst verfügbar zu machen.

    [9] Was ist Client-side Rendering und wie unterscheidet es sich vom Server-side Redering? 
    Was wird beim Client-side Rendering beim ersten Request vom Server an den Client (Browser) zurückgeliefert?

- CLient side rendering
  1. Daten aus dem compilierten js-Code erhalten, um selbst HTML-Code zu erzeugen
  2. Der Client führt die Anfragen an das Backend und alle Berechnungen selbst aus
- server side rendering
  1. der Server behält den js-Code für sich
  2. sendet nur fertigen HTML-Code an den Client
  
[10] Auf welcher Laufzeitumgebung (Runtime Environment) läuft eine Spring Boot Anwendung und auf welcher Laufzeitumgebung läuft eine Vue.js Anwendung.

- Spring Boot, läuft in einem Java virtual Machine
- Vue.js: verwendet Javascript
