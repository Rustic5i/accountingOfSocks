package com.example.accountingofsocks.repository;

import com.example.accountingofsocks.model.Socks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocksDaoTest {

    @Autowired
    private SocksDao dao;

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
    void deleteAllByColorAndCottonPartLessThan() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(4));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10);
            socksList.add(socks);
            dao.save(socks);
        }

        List<Socks> socks = dao.findAllByColorAndCottonPartGreaterThan("Синий", (byte) 20);
        System.out.println(socks);

        List<Socks> equals  = dao.findAllByColorAndCottonPartEquals("Синий", (byte) 20);
        System.out.println(equals);

        List<Socks> lessThan = dao.findAllByColorAndCottonPartIsLessThan("Синий", (byte) 20);
        System.out.println(lessThan);

    }
}