package com.quimera.controller;

import com.quimera.model.*;
import com.quimera.services.BarService;
import com.quimera.services.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Manu on 24/06/2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameManagerController {

    private Map<String, Game> gameMap = new ConcurrentHashMap<>();

    @Autowired
    private TriviaService triviaService;

    @Autowired
    private BarService barService;

    @RequestMapping(value = "initialGame", method = RequestMethod.GET)
    private void initialTrivia() {

        gameMap = new ConcurrentHashMap<>();

        Bar bar = barService.authenticate("mm", "mm");
        Trivia trivia = triviaService.find("575dc4ffbcdc88e8359cb400");

        Game game = new Game();
        game.setTrivia(trivia);
        game.setBar(bar);
        gameMap.putIfAbsent(game.getBar().getIdBar(), game);

    }

    @RequestMapping(value = "currentQuestion", method = RequestMethod.GET)
    public ResponseEntity<Question> getCurrentQuestion(@RequestParam String idBar) {
        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Question question = gameMap.get(idBar).getCurrentQuestion();
        question.setCorrectAnswer(null);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "currentQuestion", method = RequestMethod.PUT)
    public ResponseEntity setCurrentQuestion(@RequestParam String idBar, @RequestBody Question question) {

        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            game.setCurrentQuestion(question);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity setStatus(@RequestParam String idBar, @RequestBody String status) {
        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            gameMap.get(idBar).setGameStatus(GameStatus.valueOf(status));
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "status", method = RequestMethod.GET)
    public ResponseEntity<String> getStatus(String idBar) {
        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameMap.get(idBar).getGameStatus().name(), HttpStatus.OK);

    }

    @RequestMapping(value = "elapsedTime", method = RequestMethod.PUT)
    public ResponseEntity getElapsedTime(@RequestParam String idBar, @RequestBody int elapsedTime) {
        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            gameMap.get(idBar).setElapsedTime(elapsedTime);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "elapsedTime", method = RequestMethod.GET)
    public ResponseEntity<Integer> setElapsedTime(String idBar) {
        Game game = gameMap.get(idBar);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameMap.get(idBar).getElapsedTime(), HttpStatus.OK);

    }

    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public List<Player> getScores(@RequestParam String idBar) {
        return gameMap.get(idBar).getScores();
    }

    @RequestMapping(value = "/pushAnswer", method = RequestMethod.POST)
    public List<Player> getScores(@RequestParam String idBar, @RequestBody Answer answer) {
        return gameMap.get(idBar).getScores();
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void startGame() {

    }

}
