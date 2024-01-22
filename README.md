[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/V67zYIFA)
# Klausur Webtechnologien SoSe 2023

Die Klausur besteht aus zwei Teilen: einem praktischen und einem theoretischen Teil.
Sie haben insgesamt 90 Minuten Zeit für die Bearbeitung, massgeblich ist der Zeitstempel des entsprechenden Commits.
Sie müssen Ihre Abgabe auf mindestens vier Commits aufteilen (z.B. 1x zu Beginn, 1x nach 30min, 1x nach 60min, 1x nach 90min), ansonsten gibt es Punktabzug von bis zu 20 Punkten.
Es handelt sich um eine Open-Book-Klausur, Sie müssen jedoch nicht offensichtliche Quellen ausreichend kennzeichnen und dürfen keine fremde Leistung als Ihre eigene darstellen.
Füllen Sie daher bitte zuerst die Datei [ERKLAERUNG.md](ERKLAERUNG.md) aus, bevor Sie mit der Bearbeitung der Klausur beginnen.

## Theoretischer Teil (60 Punkte)

Beantworten Sie die Fragen, die Sie in der Datei [QUESTIONS.md](QUESTIONS.md) finden. 
Die Fragen beziehen sich teilweise auf den Code in diesem Repository.
Schreiben Sie Ihre Antworten direkt in diese Datei unter die jeweilige Frage.
Wenn sinnvoll, schreiben Sie Freitext, jedoch höchstens drei Sätze pro Frage (in der Regel weniger).
Bei manchen Fragen ist eine Beantwortung als Auflistung sinnvoller, aber achten Sie hier darauf, dass es unmissverständlich ist, was Sie auflisten.
Reichen Sie das Ergebnis per Git Commit & Push ein. 
Sie können die Beantwortung über mehrere Commits verteilen und Ihre Antworten bis zum Ende der Bearbeitungszeit korrigieren.

## Praktischer Teil (60 Punkte)

Im praktischen Teil geht es darum eine Admin-Seite für die Chuck-Norris App zu weiter zu entwickeln, mit der man Sprüche hinzufügen, bewerten, und sortieren kann.

### Voraussetzungen und Setup

Zur Bearbeitung dieser Aufgabe wird folgende Software benötigt:

- Java 17
- gradle 8.x
- node 18.16.x
- npm 9.8.x

#### Backend und Frontend starten

- Starten Sie die Spring Boot Anwendung, z.B. in IntelliJ durch Rechtsklick auf `ChucknorrisQuotesApplication` und dann `Run ...`

- Starten Sie die Vue App. Führen Sie dazu im Terminal, ausgehend vom Projektverzeichnis, folgende Befehle sequentiell aus:

```shell
cd frontend
npm install
npm run serve
```

- Navigieren Sie in Ihrem Browser zur Adresse [`http://localhost:3000`](http://localhost:3000) um die Startseite aufzurufen und schauen Sie sich dann die Seite "Admin Console" an.

### Aufgabe 1: Server-seitige Speicher-Funktionalität fertigstellen (16 Punkte)

1. Fügen Sie in der Test-Klasse `QuotesServiceImplTest` zwei Testfälle hinzu, welche die bereits existierende Methode `addQuote(index, quote)` in der Klasse `QuotesServiceImpl` testen:
   - Ein Testfall sollte u.a. sicherstellen, dass sich die Gesamtzahl der Sprüche sich erhöht, wenn ein neuer Spruch hinzugefügt wurde.
   - Ein Testfall sollte u.a. sicherstellen, dass man keinen Spruch an einer unerlaubten Position (z.B. negativ) einfügen kann.

2. Fügen Sie im `QuoteController` eine neue Route zum Speichern von neuen Sprüchen hinzu. 
   - Der Endpunkt soll unter `/api/v1/quote` und via `POST` erreichbar sein (so wie in `AdminTable.vue` in der Methode `save` aufgerufen).
   - Der zu speichernde Spruch soll als `RequestBody` im JSON-Format übermittelt werden, die Indexposition muss als Pfadparameter (`PathVariable`) übermittelt werden.
   - Die Struktur des übermittelten JSON-Objekts entspricht dabei der Backend-Klasse `QuoteResponse`.
   - Der Controller nutzt die zuvor getestete Service-Methode `addQuote` - dadurch sollte der Testfall `testPostRoute` im `QuoteControllerTest` grün werden.

### Aufgabe 2: Bewertungs-Funktionalität implementieren (32 Punkte)

1. Implementieren Sie in der Klasse `QuotesServiceImpl` die Methode `voteQuote` so wie im JavaDoc im dazu passenden Interface `QuotesService` beschrieben. Dadurch sollte der entsprechende Testfall im `QuotesServiceImplTest` grün werden.

2. Fügen Sie im `QuoteController` eine neue Route zum Bewerten (upvoten oder downvoten) von Sprüchen hinzu.
   - Der Endpunkt soll unter `/api/v1/vote?id={index}&vote={úpOrDown}` und via `PUT` erreichbar sein.
   - Sowohl der Index des zu bewertenden Spruchs als auch die Up- bzw. Downvote (+1 oder -1) sollen als Query Parameter (`RequestParam`) übermittelt werden.
   - Der Controller nutzt die zuvor implementierte Service-Methode `voteQuote` - dadurch sollte der Testfall `testVoteRoute` im `QuoteControllerTest` grün werden.

3. Fügen Sie in der Vue-Komponente `AdminTable.vue` eine Methode `voteQuote(index, upOrDown)` hinzu, welche die zuvor definierte PUT-Route aufruft (mit `index` und `upOrDown` als Query-Parameter) und bei Erfolg über den Aufruf von `this.loadQuotes()` die Tabelle aktualisiert. 

4. Fügen Sie in der Tabelle in der Vue-Komponente `AdminTable.vue` in jeder Zeile zwei Buttons `+` und `-` in einer vierten Spalte ein.
   - Ein Klick auf diese Buttons ruft die Methode `voteQuote` mit dem Index und der Up- bzw. Downvote (+1 oder -1) des zu bewertenden Spruchs auf.
   - Jedes generierte Button-Element sollte idealerweise eine `id` namens `upvote-button-xx` bzw. `downvote-button-xx` haben, wobei `xx` für den Index des zu bewertenden Spruchs steht - dadurch sollte der Testfall `should upvote a quote in the backend` in `AdminTable.spec.js` grün werden.

### Aufgabe 3: Client-seitige Sortierfunktionalität implementieren (12 Punkte)

1. Machen Sie in der Komponente `AdminTable.vue` die Überschriften `Index` und `Votes` zu klickbar (z.B. als Buttons), sodass ein Klick auf die jeweilige Überschrift die Tabelle anhand dieses Kriteriums sortiert.

2. Fügen Sie dafür eine Methode `sortQuotesBy(votes oder index)` hinzu, die dafür sorgt, dass die Sprüche anhand des gewählten Kriteriums sortiert und angezeigt werden. Hinweis: mit `this.$forceUpdate()` können Sie Vue dazu veranlassen, die Komponente neu zu rendern, nachdem Sie Daten geändert haben und eine Sortierfunktion ist in JavaScript bereits eingebaut: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort#sorting_array_of_objects
