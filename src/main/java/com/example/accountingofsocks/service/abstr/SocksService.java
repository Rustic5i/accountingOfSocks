package com.example.accountingofsocks.service.abstr;

import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;

import java.util.List;
import java.util.OptionalInt;

public interface SocksService {
    Socks save(Socks socks);

    void deleteAllByColorAndCottonPart(Socks socks) throws QuantitySocksOutOfBoundsException;

    List<Socks> findAllByColorAndCottonPart(String color, Operation operation, byte cottonPart);

    OptionalInt getNumberSocksByColorAndCottonPart(String color, Operation operation, byte cottonPart);

}
