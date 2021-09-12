# A Web Application that plays Rock, Paper, Scissors against a human

## Description
It is a two player game and contains three main components Rock, Paper and Scissors. Each player has these components and simultaneously chooses either Rock, Paper, or Scissors. It has some rules like:

* Rock beats Scissors but loses to Paper.
* Paper beats Rock but loses to Scissors.
* Scissors beats Paper but loses to Rock.

The project is based on a small web service which uses the following technologies:
* Java 11
* Spring Boot
* Database H2 (In-Memory)
* Maven

# Start It UP

Check out the code and execute below commands:

```
$ mvn package
$ java -jar target/rock-paper-scissors-0.0.1-SNAPSHOT.jar
```

# Check Out the Swagger UI

Open a browser and key in URL:

```
$ open http://localhost:8081
```

use below endpoint to play the game
```
/api/v1/players/{username}/games/{playerMove}
```

