package com.example.accountingofsocks.controller;

import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import com.example.accountingofsocks.service.abstr.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api")
public class Controller {

    private SocksService service;

    @Autowired
    public Controller(SocksService service) {
        this.service = service;
    }

    @PostMapping("/socks/income")
    ResponseEntity<String> income(@Valid @RequestBody Socks newSocks) {
        service.save(newSocks);
        return new ResponseEntity<>("Удалось добавить приход", HttpStatus.OK);
    }

    @PostMapping("/socks/outcome")
    ResponseEntity<String> outcome(@Valid @RequestBody Socks socks) {
        service.deleteAllByColorAndCottonPart(socks);
        return ResponseEntity.ok("Списание " + socks.getQuantity() + " пар носков, прошла успешно ");
    }

    @GetMapping("/socks")
    ResponseEntity<OptionalInt> getSocks(@RequestParam String color,
                                         @RequestParam Operation operation,
                                         @RequestParam byte cottonPart) {

        OptionalInt socksCount = service.getNumberSocksByColorAndCottonPart(color, operation, cottonPart);
        return new ResponseEntity<>(socksCount, HttpStatus.OK);

    }

}
