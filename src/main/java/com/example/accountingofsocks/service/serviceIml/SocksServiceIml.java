package com.example.accountingofsocks.service.serviceIml;

import com.example.accountingofsocks.exception.NullQuantityPointerException;
import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
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
    public Socks save(Socks socks) throws NullQuantityPointerException {
        List<Socks> socksList = dao.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());

        if (socksList.size() != 0) {
            Socks actualSocks = socksList.get(0);
            actualSocks.setQuantity(actualSocks.getQuantity() + socks.getQuantity());
            return dao.save(actualSocks);
        } else if (socks.getQuantity() < 1) {
            throw new NullQuantityPointerException("Нельзя поставить на учет, нулевое количество носков ");
        }
        return dao.save(socks);
    }

    @Override
    public void deleteAllByColorAndCottonPart(Socks socks) throws QuantitySocksOutOfBoundsException {
        List<Socks> socksList = dao.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());
        if (socksList.size() != 0) {
            Socks actualSocks = socksList.get(0);
            if (actualSocks.getQuantity() < socks.getQuantity()) {
                throw new QuantitySocksOutOfBoundsException("Нельзя списать количество пар носков, больше чем есть на складе");
            } else if (actualSocks.getQuantity() > socks.getQuantity()) {
                actualSocks.setQuantity(actualSocks.getQuantity() - socks.getQuantity());
                dao.save(actualSocks);
            } else {
                dao.delete(socks);
            }
        } else {
            throw new QuantitySocksOutOfBoundsException("Нельзя списать со склада то, чего нет");
        }
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
