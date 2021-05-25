package com.example.comparator.services;

import com.example.comparator.models.Exchange;
import com.example.comparator.repos.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    ExchangeRepo exchangeRepo;

    /**
     * Сохранить обмен в БД
     * @param  exchange обмен
     */
    @Transactional
    public void saveExchange(Exchange exchange){
        exchangeRepo.save(exchange);
    }

    /**
     * Получить все обмены по дате
     * @param date1 дата начала
     * @param userId id пользователя
     */
    @Transactional(readOnly = true)
    public List<Exchange> getAllExchangesByDate(Date date1, Integer userId){

        return exchangeRepo.getAllByDate(date1, userId);
    }

    /** Получить все обмены по дате и валютам
     * @param date дата
     * @param currency1 первая валюта
     * @param currency2 вторая валюта
     * @param userId id пользователя
     */
    @Transactional(readOnly = true)
    public List<Exchange> getAllByDateAndCurrencies(Date date, String currency1, String currency2, Integer userId){
        List<Exchange> listDate = exchangeRepo.getAllByDate(date, userId);
        List<Exchange> listCurrencies = exchangeRepo.getAllByCurrency1AndCurrency2(currency1, currency2, userId);
        listDate.retainAll(listCurrencies);
        return listDate;
    }

    /**
     * Получить историю обменов пользователя
     * @param userId ID пользователя
     *
     */
    @Transactional(readOnly = true)
    public List<Exchange> getUsersHistory(Integer userId){
        return exchangeRepo.getAllUsersExchanges(userId);
    }
}
