package com.example.accountingofsocks.controller;

import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import com.example.accountingofsocks.service.abstr.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api")
@Validated
public class Controller {

    private SocksService service;

    @Autowired
    public Controller(SocksService service) {
        this.service = service;
    }

    @PostMapping("/socks/income")
    ResponseEntity<Socks> income(@Valid @RequestBody Socks newSocks) {
        Socks socks = service.save(newSocks);
        return new ResponseEntity<>(socks, HttpStatus.OK);
    }

    @PostMapping("/socks/outcome")
    ResponseEntity<Optional<Socks>> outcome(@Valid @RequestBody Socks socks) {
        service.deleteAllByColorAndCottonPart(socks);
        List<Socks> actual = service.findAllByColorAndCottonPart(socks.getColor(), Operation.equal, socks.getCottonPart());
        return ResponseEntity.ok(actual.stream().findFirst());
    }

    @GetMapping("/socks")
    ResponseEntity<OptionalInt> getSocks(@RequestParam String color,
                                         @RequestParam Operation operation,
                                         @RequestParam @Min(0) @Max(100) byte cottonPart) {

        OptionalInt socksCount = service.getNumberSocksByColorAndCottonPart(color, operation, cottonPart);
        return new ResponseEntity<>(socksCount, HttpStatus.OK);

    }

}
