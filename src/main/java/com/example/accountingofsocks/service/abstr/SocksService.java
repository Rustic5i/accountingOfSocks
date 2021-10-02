package com.example.accountingofsocks.service.abstr;

import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;

import java.util.List;

public interface SocksService {
    Socks save(Socks socks);

    void deleteAllByColorAndCottonPart(Socks socks);

    List<Socks> findAllByColorAndCottonPart(String color, Operation operation, byte cottonPart);

}
