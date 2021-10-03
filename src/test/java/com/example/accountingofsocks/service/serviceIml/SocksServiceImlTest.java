package com.example.accountingofsocks.service.serviceIml;

import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("Списать носки")
    void deleteByColorAndCottonPart() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(i));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10 + 1);
            socksList.add(socks);
            service.save(socks);
        }
        service.deleteAllByColorAndCottonPart(socksList.get(1));
    }

    @Test
    @DisplayName("Списать носков больше, чем есть")
    void removeMoreThanIs() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(i));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10 + 1);
            socksList.add(socks);
            service.save(socks);
        }
        Socks socks = new Socks();
        socks.setColor("Синий");
        socks.setCottonPart((byte) 40);
        socks.setQuantity(100);
        assertThrows(QuantitySocksOutOfBoundsException.class, () -> {
            service.deleteAllByColorAndCottonPart(socks);
        });
    }

    @Test
    @DisplayName("Списать то, чего нет")
    void DeleteWhatIsNot() {
        Socks socks = new Socks();
        socks.setColor("Розово-зеленый");
        socks.setCottonPart((byte) 40);
        socks.setQuantity(1);

        assertThrows(QuantitySocksOutOfBoundsException.class, () -> {
            service.deleteAllByColorAndCottonPart(socks);
        });
    }

    @Test
    @DisplayName("Списать все количество носков по одной позиции с последующим удалением из бд")
    void deleteEntirePosition(){
        Socks socks = new Socks();
        socks.setColor("Красный");
        socks.setCottonPart((byte) 40);
        socks.setQuantity(100);
        service.save(socks);

        service.deleteAllByColorAndCottonPart(socks);
       List<Socks> socksList = service.findAllByColorAndCottonPart(socks.getColor(), Operation.equal, socks.getCottonPart());
        Assertions.assertEquals(0,socksList.size());
    }

    @Test
    void findAllByColorAndCottonPart() {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < colorList.size(); i++) {
            Socks socks = new Socks();
            socks.setColor(colorList.get(4));
            socks.setCottonPart((byte) ((byte) +i * 10));
            socks.setQuantity(i * 10 + 1);
            socksList.add(socks);
            service.save(socks);
        }

        List<Socks> cottonPart = service.findAllByColorAndCottonPart("Синий", Operation.lessThan, (byte) 20);
        System.out.println(cottonPart);
    }
}