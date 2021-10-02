package com.example.accountingofsocks.service.serviceIml;

import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import com.example.accountingofsocks.repository.SocksDao;
import com.example.accountingofsocks.service.abstr.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksServiceIml implements SocksService {

    private SocksDao dao;

    @Autowired
    public SocksServiceIml(SocksDao dao) {
        this.dao = dao;
    }

    @Override
    public Socks save(Socks socks) {
        return dao.save(socks);
    }

    @Override
    public void deleteAllByColorAndCottonPart(Socks socks) {

    }

    @Override
    public List<Socks> findAllByColorAndCottonPart(String color, Operation operation, byte cottonPart) {
        return switch (operation) {
            case lessThan -> dao.findAllByColorAndCottonPartIsLessThan(color, cottonPart);
            case equal -> dao.findAllByColorAndCottonPartEquals(color, cottonPart);
            case moreThan -> dao.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
        };
    }

}
