package com.chess.backend.controller

import com.chess.backend.Model.Player
import com.chess.backend.exception.GameException
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
class GameController {

    @Autowired
    private lateinit var gameService: GameService

    @Autowired
    private lateinit var simpMessagingTemplate: SimpMessagingTemplate

    @PostMapping("/create")
    fun create(@RequestBody player: Player?): ResponseEntity<Game?>? {
        log.info("create game request: {}", player)
        return ResponseEntity.ok(gameService.createGame(player))
    }

    @PostMapping("/connect")
    @Throws(GameException::class)
    fun connect(@RequestBody request: ConnectRequest): ResponseEntity<Game?>? {
        log.info("connect request: {}", request)
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()))
    }

    @PostMapping("/connect/random")
    @Throws(GameException::class)
    fun connectRandom(@RequestBody player: Player?): ResponseEntity<Game?>? {
        log.info("connect random {}", player)
        return ResponseEntity.ok(gameService.connectToRandomGame(player))
    }

    @PostMapping("/sow")
    @Throws(GameException::class)
    fun sow(@RequestBody sow: Sow?): ResponseEntity<Game?>? {
        log.info("sow: {}", sow)
        val game: Game = gameService.sow(sow)
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getId(), game)
        return ResponseEntity.ok<Game?>(game)
    }


}