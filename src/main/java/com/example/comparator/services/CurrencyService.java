package com.example.comparator.services;

import com.example.comparator.models.Currency;
import com.example.comparator.repos.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/** Сервис для работы с обменами */
@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepo currencyRepo;

    /** Получение всех валют
     * @return список всех валют
     */
    @Transactional(readOnly = true)
    public List<Currency> getAllCurrencies(){
        return currencyRepo.getAllCurrencies();
    }

    /**
     * Обноваление курсов валют
     * @param name название валюты
     * @param rate курс валюты
     * @param date дата
     */
    @Transactional
    public void updateCurrencyRate(String name, double rate, Date date){
        Currency currency = currencyRepo.findCurrencyByName(name);
        if(currency != null){
            currency.setRate(rate);
            currency.setUpdate_date(date);
            currencyRepo.save(currency);
        }
    }

    /**
     * Найти валюту по названию
     * @param name нзвание валюты
     * @return валюта или null
     */
    @Transactional(readOnly = true)
    public Currency findCurrencyByName(String name){
        return currencyRepo.findCurrencyByName(name);
    }

    /**
     * Сохранить валюту
     * @param currency валюта, которую нужно сохранить
     */
    @Transactional
    public void saveCurrency(Currency currency){
        currencyRepo.save(currency);
    }
}
