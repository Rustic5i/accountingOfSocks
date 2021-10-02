package com.example.accountingofsocks.service.serviceIml;

import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SocksServiceImlTest {

    @Autowired
    private SocksServiceIml service;

    private static List<String> colorList;

    @BeforeEach
    void setUp() {
        colorList = new ArrayList<>();
        colorList.add("Зеленый");
        colorList.add("Красный");
        colorList.add("Черный");
        colorList.add("Белый");
        colorList.add("Синий");
    }

    @Test
    void save() {
        Socks socks = new Socks();
        socks.setColor("Зеленый");
        socks.setCottonPart((byte) 80);
        socks.setQuantity(10);
        Socks actual = service.save(socks);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(socks, actual);
    }

    @Test
    void deleteByColorAndCottonPart() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(i));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10);
            socksList.add(socks);
            service.save(socks);
        }
        service.deleteAllByColorAndCottonPart(socksList.get(1));
    }

    @Test
    void findAllByColorAndCottonPart() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(4));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10);
            socksList.add(socks);
            service.save(socks);
        }

        List<Socks> cottonPart = service.findAllByColorAndCottonPart("Синий", Operation.lessThan, (byte) 20);
        System.out.println(cottonPart);
    }
}